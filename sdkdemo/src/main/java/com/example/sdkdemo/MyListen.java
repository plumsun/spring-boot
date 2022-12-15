package com.example.sdkdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @date: 2022/5/24 23:23
 * @author: LiHaoHan
 * @program: demo.config
 */
@Component
public class MyListen implements ApplicationListener<ContextStartedEvent> {
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        //保证只执行一次
        if (event.getApplicationContext().getParent() == null) {
            System.out.println("初始化完毕");
        }
    }
}
