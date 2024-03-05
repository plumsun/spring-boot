package com.example.awss3example.utils;

import com.example.awss3example.config.S3Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The type File utils.
 *
 * @author LiHaoHan Created on 2024/3/1
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class FileUtils {

    /**
     * The S 3 client.
     */
    private final S3Client s3Client;

    /**
     * The S 3 config.
     */
    private final S3Config s3Config;

    /**
     * The S 3 async client.
     */
    private final S3AsyncClient s3AsyncClient;

    /**
     * The S 3 presigner.
     */
    private final S3Presigner s3Presigner;

    /**
     * List buckets.
     */
    public void listBuckets() {
        ListBucketsResponse response = s3Client.listBuckets();
        List<Bucket> bucketList = response.buckets();
        Assert.notNull(bucketList, "不为空");
        log.info("bucket list:{}", bucketList);
    }

    /**
     * 列出当前 bucket 下所有文件
     */
    public void listObjects() {
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(s3Config.getBucket()).build();
        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        List<S3Object> objects = response.contents();
        log.info("bucket:{} contain file object list:{}", s3Config.getBucket(), objects);
    }

    /**
     * Gets url.
     *
     * @param fileName the file name
     * @return the url
     */
    public URL getURL(String fileName) {
        GetUrlRequest request = GetUrlRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        URL url = s3AsyncClient.utilities().getUrl(request);
        log.info("The URL for :{} is :{}", fileName, url);
        return url;
    }

    /**
     * 获取文件信息
     *
     * @param fileName the file name
     */
    public void getObject(String fileName) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        ResponseInputStream<GetObjectResponse> s3ClientObject = s3Client.getObject(request);
        GetObjectResponse response = s3ClientObject.response();
        Long length = response.contentLength();
        log.info("get file success,response:{},file size:{}", response, length);
    }

    /**
     * 获取远程文件，并保存到本地
     *
     * @param fileName the file name
     */
    public ResponseBytes<GetObjectResponse> getObjectToBytes(String fileName) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObject(request, ResponseTransformer.toBytes());
        log.info("get file success,response:{},file size:{}", responseBytes, responseBytes.asByteArray().length);
        return responseBytes;
    }

    /**
     * 获取远程文件，并保存到本地
     *
     * @param fileName the file name
     * @param filePath the file path
     */
    public void getObject(String fileName, String filePath) {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        GetObjectResponse object = s3Client.getObject(request, ResponseTransformer.toFile(Paths.get(filePath)));
        String string = object.responseMetadata().toString();
        log.info("get file success,response:{},file size:{}", object, object.contentLength());
    }

    /**
     * 上传文件（nio）
     *
     * @param fileName   the file name
     * @param byteBuffer the byte buffer
     */
    public void uploadObject(String fileName, ByteBuffer byteBuffer) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        PutObjectResponse response = s3Client.putObject(request, RequestBody.fromByteBuffer(byteBuffer));
        log.info("aws file dispose,response :{}", response);
    }

    /**
     * 上传文件
     *
     * @param fileName the file name
     * @param bytes    the bytes
     */
    public void uploadObject(String fileName, byte[] bytes) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(s3Config.getBucket())
                .key(s3Config.getFileDir(fileName))
                .build();
        PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(bytes));
        log.info("aws file dispose,response :{}", response);
    }

    /**
     * 为S3文件生成 预签名 Url
     *
     * @param fileName the file name
     */
    public void testUrl(String fileName) {
        // 默认生成的预览url最大时效时间为：7天，aws s3能允许的最大时限也是7天
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(builder -> builder
                        .bucket(s3Config.getBucket())
                        .key(s3Config.getFileDir(fileName))
                        .build())
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        String pre_signedUrl = presignedGetObjectRequest.url().toString();
        log.info("file generated Pre-signature expiration date:{} day,url:{}", 7, pre_signedUrl);
    }

    /**
     * Multi part upload.
     */
    public void multiPartUpload() {
        S3TransferManager transferManager = S3TransferManager.create();
        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(b -> b
                        .bucket(s3Config.getBucket())
                        .key("1"))
                .source(Paths.get("test"))
                .build();
        s3AsyncClient.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .build()
        );
        transferManager.close();
    }


    /**
     * 分段上传文件，分段文件大小默认 5M
     *
     * @param filePath the file path
     * @param fileName the file name
     */
    public void multipartUploadWithS3Client(String filePath, String fileName) {

        String fileKey = s3Config.getFileDir(fileName);
        String bucket = s3Config.getBucket();
        // Initiate the multipart upload.
        CompletableFuture<CreateMultipartUploadResponse> multipartUpload = s3AsyncClient.createMultipartUpload(b -> b
                .bucket(bucket)
                .key(fileKey));
        CreateMultipartUploadResponse multipartUploadResponse = multipartUpload.join();
        String uploadId = multipartUploadResponse.uploadId();
        log.info("file:{} Start sharding processing, uploadId:{}", fileName, uploadId);

        long fileUploadSize = 0;
        // Upload the parts of the file.
        int partNumber = 1;
        List<CompletedPart> completedParts = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.allocate(1024 * 1024 * s3Config.getMultipartCapacity()); // 5 MB byte buffer

        try (RandomAccessFile file = new RandomAccessFile(filePath, "r")) {
            long fileSize = file.length();
            log.info("file length:{}", fileSize);
            int position = 0;
            while (position < fileSize) {
                if (position > 1024 * 1024 * 20) {
                    throw new IllegalArgumentException();
                }
                file.seek(position);
                int read = file.getChannel().read(bb);
                log.info("read file length now:{}", read);
                bb.flip(); // Swap position and limit before reading from the buffer.
                UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                        .bucket(bucket)
                        .key(fileKey)
                        .uploadId(uploadId)
                        .partNumber(partNumber)
                        .build();

                CompletableFuture<UploadPartResponse> uploadPart = s3AsyncClient.uploadPart(
                        uploadPartRequest,
                        AsyncRequestBody.fromByteBuffer(bb));
                UploadPartResponse response = uploadPart.join();
                CompletedPart part = CompletedPart.builder()
                        .partNumber(partNumber)
                        .eTag(response.eTag())
                        .build();
                completedParts.add(part);

                bb.clear();
                position += read;
                fileUploadSize += read;
                partNumber++;
            }
        } catch (Exception e) {
            log.info("upload file:{} error,The size that has been uploaded：{}", fileName, fileUploadSize);
            // Complete the multipart upload.
            completeMultipartFile(fileName, bucket, uploadId, completedParts);
            throw new RuntimeException(e);
        }
        log.info("The file sharding is complete:{}", completedParts);
        // Complete the multipart upload.
        completeMultipartFile(fileName, bucket, uploadId, completedParts);
    }

    /**
     * 合并已经上传成功的分段文件
     *
     * @param fileName       the file name
     * @param bucket         the bucket
     * @param uploadId       the upload id
     * @param completedParts the completed parts
     */
    private void completeMultipartFile(String fileName, String bucket, String uploadId, List<CompletedPart> completedParts) {
        s3AsyncClient.completeMultipartUpload(b -> b
                .bucket(bucket)
                .key(fileName)
                .uploadId(uploadId)
                .multipartUpload(CompletedMultipartUpload.builder().parts(completedParts).build()));
        log.info("The file sharding merge success,file name:{},file uploadId:{}", fileName, uploadId);
    }

    /**
     * 停止当前分段上传，并删除已经上传的分段文件
     *
     * @param fileName the file name
     * @param bucket   the bucket
     * @param uploadId the upload id
     */
    private void abortMultipartFile(String fileName, String bucket, String uploadId) {
        s3AsyncClient.abortMultipartUpload(b -> b
                .bucket(bucket)
                .key(fileName)
                .uploadId(uploadId).build());
        log.info("The file sharding delete success,file name:{},file uploadId:{}", fileName, uploadId);
    }

    /**
     * 列出正在分段上传的文件
     */
    public void listUploads() {
        try {
            ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder()
                    .bucket(s3Config.getBucket())
                    .build();

            ListMultipartUploadsResponse response = s3Client.listMultipartUploads(listMultipartUploadsRequest);
            List<MultipartUpload> uploads = response.uploads();
            for (MultipartUpload upload : uploads) {
                log.info("Upload in progress: Key :{}, upload id:{} ", upload.key(), upload.uploadId());
            }
        } catch (S3Exception e) {
            log.error("check upload in progress error", e);
            throw new RuntimeException(e);
        }
    }
}
