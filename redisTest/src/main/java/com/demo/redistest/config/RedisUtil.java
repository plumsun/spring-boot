package com.demo.redistest.config;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @date: 2022/7/21 18:23
 * @author: LiHaoHan
 * @program: com.demo.config
 */
public class RedisUtil {

    @Autowired
    RedissonClient redissonClient;

    public void lockTest(){
        RLock lock = redissonClient.getLock("");
        lock.tryLock();
    }
}
