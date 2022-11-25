package com.study.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.study.entity.StatusType;
import com.study.entity.Test;
import com.study.entity.TestDemo;
import com.study.entity.resp.RestResult;
import com.study.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description:
 * @date: 2022/3/26 21:06
 * @author: LiHaoHan
 * @program: com.study.service.impl
 */
@Slf4j
@SpringBootTest
public class ExcelServiceImplTest {

    /**
     * 测试HashSet存值问题
     */
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

    /**
     * 测试对象拷贝
     */
    @org.junit.Test
    public void test1() {
        ArrayList<Test> list1 = new ArrayList<>();
        // ArrayList<Test> list2 = new ArrayList<>();
        list1.add(new Test("1", "li"));
        list1.add(new Test("2", "l"));

        ArrayList<Test> list2 = new ArrayList<>();
        list2.add(new Test("3", "li"));
        list2.add(new Test("4", "l"));
        // ArrayList<Test> list2 = new ArrayList<>();
        //获取两个容器数据的交集
        Collection<Test> tests = CollUtil.union(list1, list2);
        Collections.copy(list1, list2);
        //对象clone
        ArrayList<Test> clone = (ArrayList) SerializationUtils.clone((Serializable) list1);
        System.out.println("list1 = " + tests);
        // System.out.println("list2 = " + list2);
    }

    @org.junit.Test
    public void test2() {
        Object set = set();
        cn.hutool.json.JSONObject object = JSONUtil.parseObj(set);
        HashMap map = object.toBean(HashMap.class);
        Object o = map.get("2");
        if (!ObjectUtil.isEmpty(o)) {
            System.out.println("o = " + o);
        }
        System.out.println("object = " + map);
    }

    private Object set() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", "data");
        map.put("pk", "1");
        System.out.println("map = " + map);
        TestDemo testDemo = new TestDemo();
        testDemo.setId("1");
        testDemo.setObj(map);
        return map;
    }

    @org.junit.Test
    public void test4() {
        ArrayList<String> elementShip00 = new ArrayList<>();
        ArrayList<String> element10 = new ArrayList<>();
        String element60 = "";
        String element99 = "";
        StrBuilder sb = new StrBuilder();
        String property = "'";
        sb.append(Joiner.on(":").join(elementShip00)).append(property);
        sb.append(Joiner.on(":").join(element10)).append(property);
        sb.append(element60);
        sb.append(element99).append(property);
        System.out.println("sb = " + sb);
    }

    @org.junit.Test
    public void test3() {
        String statusType = StatusType.getInfoByCode("1");
        System.out.println(statusType);
        RestResult chenggong = RestResult.success("chenggong");
        System.out.println("result = " + chenggong);
    }

    @org.junit.Test
    public void strTest() throws UnsupportedEncodingException {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String str1 = "SUDGSV- SDF- -FDSD SD12 -445 -";
        System.out.println("str1.length() = " + str1.length());
        String str = "很乏味收到SUDGSV- SDF- -FDSD SD12 -445 -*+>>??<<*&^%$#@!!爱国法评估福安市";
        int length = str.length();
        System.out.println("length = " + length);

        String s = "我ZWR爱你们JAVA";
        System.out.println("截取前60位：" + StringUtils.substring(str, 60));
        System.out.println("截取前60位：" + sub(str, "[\u4e00-\u9fa5]", 60));
    }

    private String sub(String val, String reg, int end) {
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("startTime = " + startTime);
        if (StrUtil.isEmpty(val)) {
            return val;
        }
        if (!(end > 0 && end < val.getBytes(StandardCharsets.UTF_8).length)) {
            return val;
        }
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(val);
        for (int i = 0; i < end; i++) {
            sb.append(val.charAt(i));
            if (m.find()) {
                int start = m.start();
                if (start < end) {
                    end -= 2;
                }
            }
        }
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("endTime = " + endTime);
        long until = LocalDateTime.from(startTime).until(endTime, ChronoUnit.MILLIS);
        Duration between = Duration.between(startTime, endTime);
        System.out.println(between.toMillis());
        System.out.println("until = " + until);
        return sb.toString();
    }

    @org.junit.Test
    public void subStrTest() {
        String str = "123,";
        String s = JSONObject.toJSONString(str);
        System.out.println("s = " + s);
        String substring = str.substring(0, 3);
        String regEx = ".*[\\s`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Pattern p = Pattern.compile(regEx);
    }

    @org.junit.Test
    public void logError() {
        try {
            int i = 1 / 0;
            System.out.println("i = " + 1);
        } catch (Exception e) {
            log.error("外贸导入数据报错原因{},报错数据{}", e.getMessage(), "11111");
        }
    }

}