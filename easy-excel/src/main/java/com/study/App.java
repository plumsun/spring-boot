package com.study;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @date: 2021/11/4 9:41
 * @author: LiHaoHan
 * @program: com.study
 */
@MapperScan(basePackages = {"com.study.*"})
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
