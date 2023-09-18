package com.demo.controller;

import com.demo.model.Response;
import com.demo.redis.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 测试
 *
 * @author LiHaoHan
 * @date 2023/2/17
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/")
    public Response get(){
        return Response.success();
    }

    @GetMapping("/feign")
    public Response feign(){
        return Response.success();
    }
}
