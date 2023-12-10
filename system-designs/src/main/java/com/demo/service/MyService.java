package com.demo.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private final ApplicationEventPublisher publisher;

    public MyService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void doSomething() {
        // 做一些事情...
        String message = "Something happened";
        MyEvent event = new MyEvent(message);
        System.out.println("message = " + message);
        publisher.publishEvent(event);
    }
}