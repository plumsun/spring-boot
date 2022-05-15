package com.study.service.impl;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @description:
 * @date: 2022/3/26 21:06
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ExcelServiceImplTest {
    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(null);
        objects.add(5);
        objects.add(3);
        System.out.println("objects = " + objects);
        HashSet<Integer> integers = new HashSet<>();
        integers.add(null);
        System.out.println("integers = " + integers);
    }


}