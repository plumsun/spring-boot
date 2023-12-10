package com.example.sdkdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:META-INF/spring.factories")
class SdkdemoApplicationTests {

    @Autowired
    private MyService myService;

    @Test
    void contextLoads() {
        myService.doSomething();
    }

}
