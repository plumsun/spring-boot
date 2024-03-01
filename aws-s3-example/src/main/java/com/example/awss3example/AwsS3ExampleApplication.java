package com.example.awss3example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AwsS3ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AwsS3ExampleApplication.class, args);
    }
}
