package com.study.service.impl;

import cn.hutool.core.util.StrUtil;
import com.study.entity.ClCodShbesEntity;
import com.study.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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


    @Test
    public void optionalTest1(){
        System.out.println(System.currentTimeMillis());
        com.study.entity.Test test = new com.study.entity.Test();
        test.setId("1");
        Optional<com.study.entity.Test> optional = Optional.of(test);
        String s = optional.map(com.study.entity.Test::getId).orElse("");
        System.out.println("s = " + s);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void optionalTest2(){
        System.out.println(System.currentTimeMillis());
        String s = "";
        com.study.entity.Test test = new com.study.entity.Test("1","class");
        if(test.getId().equals("1")){
            s = test.getName();
        }else if(test.getId().equals("2")){
            s = test.getName();
        }else if(test.getId().equals("3")){
            s = test.getName();
        }
        System.out.println("s = " + s);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void optionalTest3(){
        System.out.println(System.currentTimeMillis());
        String s = "";
        com.study.entity.Test test = new com.study.entity.Test("1","class");
        String id = test.getId();
        if(id.equals("1")){
            s = test.getName();
        }else if(id.equals("2")){
            s = test.getName();
        }else if(id.equals("3")){
            s = test.getName();
        }
        System.out.println("s = " + s);
        System.out.println(System.currentTimeMillis());
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