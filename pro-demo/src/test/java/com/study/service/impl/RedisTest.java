package com.study.service.impl;

import cn.hutool.json.JSONUtil;
import com.study.entity.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: RedisTest
 * @Date: 2022/10/24
 * @Author: LiHaoHan
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @org.junit.Test
    public void redisTest() {
        String k = redisTemplate.opsForValue().get("k");
        List<Test> infoList = JSONUtil.parseArray(k).toList(Test.class);
        System.out.println("infoList = " + infoList);
        ArrayList<Test> list1 = new ArrayList<>();
        // ArrayList<Test> list2 = new ArrayList<>();
        list1.add(new Test());
        list1.add(new Test());
        //CollUtil.union(list1,infoList);
        Collections.copy(list1,infoList);
        System.out.println("list1 = " + list1);
    }

}
