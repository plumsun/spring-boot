package com.demo.service;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2022/5/24 23:23
 * @author: LiHaoHan
 * @program: demo.config
 */
@Component
public class MyListen implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        // 保证只执行一次
        System.out.println("初始化完毕");
    }
}
