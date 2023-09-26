package com.study.controller;

import com.study.entity.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * redis控制层
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * redis测试
     */
    @PostMapping("redis")
    public void redisTest() {
        ArrayList<Test> list = new ArrayList<>();
    }

    @GetMapping("delete")
    public void deleteTest(@RequestBody Map map){
        Object key = map.get("key");
        String s = key.toString();
        this.stringRedisTemplate.delete(s);
    }
}
