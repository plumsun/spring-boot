package com.study;

/**
 * @description:
 * @date: 2021/11/4 9:41
 * @author: LiHaoHan
 * @program: com.study
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
