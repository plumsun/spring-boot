package com.example.sdkdemo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
        Gson gson = new Gson();
        Object o = gson.fromJson();
        System.out.println(" 初始化完毕");
    }
}
