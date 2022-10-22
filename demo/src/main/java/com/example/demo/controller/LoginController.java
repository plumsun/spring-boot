package com.example.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.pojo.CacheNumber;
import com.example.demo.service.CacheService;
import com.example.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @date: 2022/7/18 12:04
 * @author: LiHaoHan
 * @program: com.example.demo.controller
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    EmpService empService;
    @Resource
    CacheService cacheService;

    @Resource
    RedisTemplate<String,String> redisTemplate;

    @GetMapping("/")
    @Transactional(rollbackFor = RuntimeException.class)
    public String login() {
        Character.isDigit(1);
        return this.empService.list().toString();
    }

    @PostMapping("/tt")
    public void t() {
        saveCacheNum("");
    }


    @Async
    public void saveCacheNum(String redisKey) {
        CacheNumber cacheNumber = this.cacheService.getById(redisKey);
        if (ObjectUtil.isEmpty(cacheNumber))
            this.cacheService.save(new CacheNumber(redisKey, 1));
        UpdateWrapper<CacheNumber> wrapper = new UpdateWrapper<>();
        wrapper.eq("redis_key", redisKey);
        wrapper.set("number", cacheNumber.getNumber() + 1);
        this.cacheService.update(wrapper);
    }
}
