package com.demo;
/**
 * @date: 2023/6/30
 * @author: LiHaoHan
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.*")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
