package com.study.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @description:
 * @date: 2022/3/26 21:06
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@SpringBootTest
public class ExcelServiceImplTest {

    @org.junit.Test
    public void extracted() {
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