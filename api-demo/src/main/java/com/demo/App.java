package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author LiHaoHan
 * @date 2022/12/9
 */
@SpringBootApplication
@MapperScan("com.demo.mapper")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
