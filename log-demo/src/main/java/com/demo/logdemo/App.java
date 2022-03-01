package com.demo.logdemo;

import com.migu.gvpcore.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description:
 * @date: 2021/11/19 15:55
 * @author: LiHaoHan
 * @program: com.demo.logdemo
 */
@EnableDiscoveryClient
@EnableFeignClients(defaultConfiguration = FeignClientConfig.class)
@EnableHystrix
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
