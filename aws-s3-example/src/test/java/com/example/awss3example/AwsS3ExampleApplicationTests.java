package com.example.awss3example;

import com.example.awss3example.config.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

import javax.annotation.Resource;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@SpringBootTest
class AwsS3ExampleApplicationTests {

    @Resource
    private S3Client s3Client;

    @Resource
    private S3Presigner s3Presigner;

    @Resource
    private S3AsyncClient s3AsyncClient;

    @Resource
    private S3Config s3Config;

    private String bucket = "plum-test-bucket";

    @Test
    void listBuckets() {
        ListBucketsResponse response = s3Client.listBuckets();
        List<Bucket> bucketList = response.buckets();
        Assert.notNull(bucketList, "不为空");
        for (Bucket bucket : bucketList) {
            System.out.println("Bucket name " + bucket.name());
        }
    }

    @Test
    void listObjects() {
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket("plum-test-bucket").build();
        ListObjectsV2Response response = s3Client.listObjectsV2(request);
        List<S3Object> objects = response.contents();
        for (S3Object object : objects) {
            System.out.println("Bucket url " + s3Config.getFileUrl(object.key()));
        }
    }

    @Test
    void getObject() {
        GetObjectRequest request = GetObjectRequest.builder().bucket("plum-test-bucket").key("3.jpeg").build();
        ResponseInputStream<GetObjectResponse> s3ClientObject = s3Client.getObject(request);
        Long aLong = s3ClientObject.response().contentLength();
        System.out.println("Bucket name " + aLong);
    }


    @Test
    void testUrl() {
        // 默认生成的预览url最大时效时间为：7天，aws s3能允许的最大时限也是7天
        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(builder -> builder.bucket("plum-test-bucket").key("3.jpeg").build())
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);
        String theUrl = presignedGetObjectRequest.url().toString();
        System.out.println("Presigned URL: " + theUrl);
    }

    @Test
    void multiPartUpload() {
        S3TransferManager transferManager = S3TransferManager.create();
        UploadFileRequest uploadFileRequest = UploadFileRequest.builder()
                .putObjectRequest(b -> b
                        .bucket("plum-test-bucket")
                        .key("1"))
                .source(Paths.get("test"))
                .build();
        s3AsyncClient.createMultipartUpload(CreateMultipartUploadRequest.builder()
                .build()
        );
        // FileUpload fileUpload = s3AsyncClient.uploadFile(uploadFileRequest);
        // fileUpload.completionFuture().join();
        transferManager.close();
    }

    @Test
    void uploadObject() {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket("plum-test-bucket")
                .key("upload-test/4989test.jpeg")
                .build();
        File file = new File("C:\\Users\\plums\\Pictures\\4989.jpg");
        PutObjectResponse objectResponse = s3Client.putObject(request, RequestBody.fromFile(file));
        System.out.println("objectResponse = " + objectResponse);
    }

    @Test
    void multiPartUploadAsync() {
        String[] nameList = "20240301-148629425815553--9b5cf88c5baa4026800c75ef2aa52a45".split("-");
        String[] subarray = ArrayUtils.subarray(nameList, 0, nameList.length - 1);
        System.out.println("subarray = " + Arrays.toString(subarray));
        String groupPath = StringUtils.join(subarray, "/");
        System.out.println("groupPath = " + groupPath);

        // multipartUploadWithS3Client(bucket, "C:\\Users\\plums\\Downloads\\03.mp4", "test.mp4");
    }

    public void multipartUploadWithS3Client(String bucketName, String filePath, String fileName) {

        // Initiate the multipart upload.
        CompletableFuture<CreateMultipartUploadResponse> multipartUpload = s3AsyncClient.createMultipartUpload(b -> b
                .bucket(bucketName)
                .key(fileName));
        CreateMultipartUploadResponse multipartUploadResponse = multipartUpload.join();
        String uploadId = multipartUploadResponse.uploadId();
        log.info("file uploadId:{}", uploadId);

        long fileUploadSize = 0;
        // Upload the parts of the file.
        int partNumber = 1;
        List<CompletedPart> completedParts = new ArrayList<>();
        ByteBuffer bb = ByteBuffer.allocate(1024 * 1024 * s3Config.getCapacity()); // 5 MB byte buffer

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
                        .bucket(bucketName)
                        .key(fileName)
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
            log.info("upload success file size:{}", fileUploadSize);
            // Complete the multipart upload.
            s3AsyncClient.completeMultipartUpload(b -> b
                    .bucket(bucketName)
                    .key(fileName)
                    .uploadId(uploadId)
                    .multipartUpload(CompletedMultipartUpload.builder().parts(completedParts).build()));
            throw new RuntimeException(e);
        }

        log.info("sync upload list:{}", completedParts);

        // Complete the multipart upload.
        s3AsyncClient.completeMultipartUpload(b -> b
                .bucket(bucketName)
                .key(fileName)
                .uploadId(uploadId)
                .multipartUpload(CompletedMultipartUpload.builder().parts(completedParts).build()));
    }

    @Test
    public void listUploads() {
        try {
            ListMultipartUploadsRequest listMultipartUploadsRequest = ListMultipartUploadsRequest.builder()
                    .bucket(bucket)
                    .build();

            ListMultipartUploadsResponse response = s3Client.listMultipartUploads(listMultipartUploadsRequest);
            List<MultipartUpload> uploads = response.uploads();
            for (MultipartUpload upload : uploads) {
                System.out.println("Upload in progress: Key = \"" + upload.key() + "\", id = " + upload.uploadId());
            }

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }


    @Test
    public void abortMultipartUpload() {
        try {
            AbortMultipartUploadRequest build = AbortMultipartUploadRequest.builder()
                    .uploadId("aUe6dxj.tqEGCsN0js0Yn8yv9V1EzjTpUSjME3HxcN9JMhQiFY95_matoGpTAPzunOfuGXI9LjAPPXkK2KS_MqfCb7T1WxCKU8tOyA7R9MhowSK8rqSqi3zZNJ_1r1YBRgo09GJpNVaAbJKSaY0E4g--")
                    .bucket(bucket)
                    .key("test.mp4")
                    .build();

            AbortMultipartUploadResponse response = s3Client.abortMultipartUpload(build);
            String uploads = response.requestChargedAsString();
            System.err.println(uploads);
        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
