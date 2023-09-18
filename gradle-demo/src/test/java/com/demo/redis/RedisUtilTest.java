package com.demo.redis;

import com.demo.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @date: 2023/6/30
 * @author: LiHaoHan
 */
@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class RedisUtilTest {

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void setVal() {
        System.out.println("redisUtil = " + redisUtil);
    }
}