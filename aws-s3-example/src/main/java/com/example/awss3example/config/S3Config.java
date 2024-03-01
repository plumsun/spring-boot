package com.example.awss3example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author LiHaoHan Created on 2024/2/19
 */
// @RefreshScope
@Data
@Component
@ConfigurationProperties(prefix = "s3")
public class S3Config {

    private String awsAccessKeyId;

    private String awsSecretAccessKey;

    private String region;

    private String bucket;

    private String uploadDirectory;

    private Integer capacity;

    public URI getFileUrl(String key) {
        try {
            URI uri = new URI("https://" + bucket + ".s3." + region + ".amazonaws.com" + "/" + key);
            System.out.println("uri = " + uri);
            return uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
