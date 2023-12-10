package com.example.sdkdemo;

import org.springframework.context.ApplicationEvent;

/**
 * @author LiHaoHan Created on 2023/12/7
 */
public class MyEvent extends ApplicationEvent {


    public MyEvent(Object source) {
        super(source);
    }


}
