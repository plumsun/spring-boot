package com.study.config.events;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2022/5/24 22:44
 * @author: LiHaoHan
 * @program: com.study.com.demo.config
 */
@Component
public class MyRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(" 初始化完毕");
    }
}
