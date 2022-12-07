package com.study.service.impl;

import cn.hutool.core.util.StrUtil;
import com.study.entity.ClCodShbesEntity;
import com.study.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author LiHaoHan
 * @date 2022/11/24
 */
@Slf4j
@SpringBootTest
public class JavaTest {

    /**
     * 反射校验实体类中属性
     */
    @Test
    public void objFieldTest() {
        com.study.entity.Test test = new com.study.entity.Test("null", "2");
        ObjectUtils.validateField(test);
    }

    /**
     * stream.max() 测试
     *
     * @throws InterruptedException
     */
    @Test
    public void streamTest() throws InterruptedException {
        ClCodShbesEntity object1 = new ClCodShbesEntity();
        object1.setUpdateTime(new Date());
        Thread.sleep(1000);
        ClCodShbesEntity object2 = new ClCodShbesEntity();
        object2.setUpdateTime(new Date());
        ArrayList<ClCodShbesEntity> list = new ArrayList<>();
        list.add(object1);
        list.add(object2);
        System.out.println("list = " + list);
        ClCodShbesEntity entity = list.stream().max(Comparator.comparing(ClCodShbesEntity::getUpdateTime)).get();
        System.out.println("entity = " + entity);
    }


    /**
     *
     */
    @Test
    public void test(){
        boolean expectedDate = StrUtil.isAllBlank("", "");
        System.out.println("expectedDate = " + expectedDate);
    }

    private void err(){
        int i=1/0;
    }

    @Test
    public void threadTest(){
       Runnable run =  new Runnable(){
           @Override
           public void run() {
               
           }
       };
    }
}
class MyThread extends Thread{

    private volatile List list;

    public MyThread(List list){
        this.list =list;
    }

    @Override
    public void run() {

    }
}