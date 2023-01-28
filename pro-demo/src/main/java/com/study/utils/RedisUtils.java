package com.study.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @description: Redis工具类
 * @date: 2022/10/22 20:36
 * @author: LiHaoHan
 * @program: com.study.util
 */
@Repository
public class RedisUtils {

    private RedisUtils() {
        throw new IllegalStateException("Utility class");
    }

    @Autowired
    private StringRedisTemplate redisTemplate;


    public void setList(String key, String value, Long time, TimeUnit unit){
        redisTemplate.opsForList().rightPush(key,value);
    }
}
