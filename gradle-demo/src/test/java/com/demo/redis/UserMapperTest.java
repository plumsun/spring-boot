package com.demo.redis;


import org.junit.Test;

import java.util.Arrays;

/**
 * @date: 2023/7/5
 * @author: LiHaoHan
 */
// @SpringBootTest(classes = App.class)
// @RunWith(SpringRunner.class)
public class UserMapperTest {

    @Test
    public void Test(){
        String str = "s,s.2.s,seg";
        String[] split = str.split(",");
        System.out.println("split = " + Arrays.toString(split));
    }
}