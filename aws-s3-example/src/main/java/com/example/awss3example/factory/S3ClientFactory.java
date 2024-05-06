package com.example.awss3example.factory;

import com.example.awss3example.config.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import javax.annotation.Resource;

/**
 * @author LiHaoHan Created on 2024/2/19
 */
@Slf4j
@Component
public class S3ClientFactory {

    @Resource
    private S3Config s3Config;

    @RefreshScope
    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(() -> AwsBasicCredentials.create(s3Config.getAwsAccessKeyId(), s3Config.getAwsSecretAccessKey()))
                .build();
    }

    @Bean
    public S3AsyncClient s3AsyncClient() {
        return S3AsyncClient.builder()
                .region(Region.AP_SOUTHEAST_1)
                .multipartEnabled(true)
                .credentialsProvider(() -> AwsBasicCredentials.create(s3Config.getAwsAccessKeyId(), s3Config.getAwsSecretAccessKey()))
                .build();
    }

    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.builder()
                .region(Region.AP_SOUTHEAST_1)
                .credentialsProvider(() -> AwsBasicCredentials.create(s3Config.getAwsAccessKeyId(), s3Config.getAwsSecretAccessKey()))
                .build();
    }
}
