package com.demo.redistest;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;

@RunWith(SpringRunner.class)
// @ContextConfiguration("classpath:applicationContent.xml")
@SpringBootTest
class RedisTestApplicationTests {


    @Resource
    RedisTemplate<String, Object> redisTemplate;


    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("1",1);
        Set keys = redisTemplate.keys("*");
        System.out.println("keys = " + keys);
    }

}
