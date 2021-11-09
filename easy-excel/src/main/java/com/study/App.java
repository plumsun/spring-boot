package com.study;

/**
 * @description:
 * @date: 2021/11/4 9:41
 * @author: LiHaoHan
 * @program: com.study
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// @MapperScan(basePackages = "com.study.*")
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
