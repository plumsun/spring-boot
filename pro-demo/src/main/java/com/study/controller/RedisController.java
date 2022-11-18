package com.study.controller;

import cn.hutool.json.JSONUtil;
import com.study.entity.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * @description: redis控制层
 * @date: 2022/11/9 20:40
 * @author: LiHaoHan
 * @program: com.study.controller
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
        list.add(new Test("1", "li"));
        list.add(new Test("1", "li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        list.add(new Test("1", "li"));
        list.add(new Test("1", "li"));
        redisTemplate.boundListOps("s").leftPush(JSONUtil.toJsonStr(list));
        redisTemplate.opsForList();
    }

    @GetMapping("delete")
    public void deleteTest(@RequestBody Map map){
        Object key = map.get("key");
        String s = key.toString();
        this.stringRedisTemplate.delete(s);
    }
}
