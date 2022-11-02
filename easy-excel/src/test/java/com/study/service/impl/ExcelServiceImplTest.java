package com.study.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Joiner;
import com.study.entity.resp.RestResult;
import com.study.entity.StatusType;
import com.study.entity.Test;
import com.study.entity.TestDemo;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    @org.junit.Test
    public void test1() throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        ArrayList<Test> list1 = new ArrayList<>();
        // ArrayList<Test> list2 = new ArrayList<>();
        list1.add(new Test("1", "li"));
        list1.add(new Test("2", "l"));

        ArrayList<Test> list2 = new ArrayList<>();
        list2.add(new Test("3", "li"));
        list2.add(new Test("4", "l"));
        // ArrayList<Test> list2 = new ArrayList<>();
        Collection<Test> tests = CollUtil.union(list1, list2);
        Collections.copy(list1, list2);
        // ArrayList<Test> list2 = (ArrayList) SerializationUtils.clone((Serializable) list1);
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
        RestResult chenggong = RestResult.T("chenggong");
        System.out.println("result = " + chenggong);
    }

    @org.junit.Test
    public void streamTest(){
        ArrayList<String> list = new ArrayList<>();
        list.stream().filter(StrUtil::isEmpty).forEach(System.out::println);
    }

}