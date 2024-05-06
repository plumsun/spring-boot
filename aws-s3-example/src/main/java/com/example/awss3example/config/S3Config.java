package com.example.awss3example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author LiHaoHan Created on 2024/2/19
 */
@RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "s3")
public class S3Config {

    private String awsAccessKeyId;

    private String awsSecretAccessKey;

    private String region;

    private String bucket;

    private String uploadDirectory;

    private Integer multipartCapacity;

    public String getFileDir(String fileName) {
        return this.uploadDirectory + "/" + fileName;
    }
}
