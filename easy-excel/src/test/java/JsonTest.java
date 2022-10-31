
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.base.Joiner;
import com.study.entity.RestResult;
import com.study.entity.StatusType;
import com.study.entity.Test;
import com.study.entity.TestDemo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @description:
 * @date: 2021/11/4 17:01
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@SpringBootTest
public class JsonTest {
    @org.junit.Test
    public void test() {
        String str = "{\n" +
                "    \"code\": 200,\n" +
                "    \"body\": {\n" +
                "        \"code\": 0,\n" +
                "        \"data\": {\n" +
                "            \"count_array\": [\n" +
                "                {\n" +
                "                    \"room_id\": \"boxx202110261555021_SR12\",\n" +
                "                    \"user_count\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"room_id\": \"boxx202109171524233_SR12\",\n" +
                "                    \"user_count\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"room_id\": \"boxx202109181426505_SR12\",\n" +
                "                    \"user_count\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"room_id\": \"boxx202109181523066_SR12\",\n" +
                "                    \"user_count\": 0\n" +
                "                }\n" +
                "            ],\n" +
                "            \"seq\": 610402871,\n" +
                "            \"version\": 1\n" +
                "        },\n" +
                "        \"message\": \"success\"\n" +
                "    },\n" +
                "    \"message\": \"success\",\n" +
                "    \"timeStamp\": 1636015727097\n" +
                "}";
        JSONObject jsonObject = JSONObject.fromObject(str);
        String body = jsonObject.optString("body");
        System.out.println("body = " + body);
        JSONObject jsonObject1 = JSONObject.fromObject(body);
        String data = jsonObject1.optString("data");
        System.out.println("data = " + data);
    }




    @org.junit.Test
    public void pai() {
        String str = "{\"name\":\"xxx\",\"abc\":\"wwwwsss\"}";
        com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(str);
        object.entrySet().stream().sorted(new Comparator<Map.Entry<String, Object>>() {
            @Override
            public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        }).forEach(System.out::println);
        String s = object.toJSONString();
        System.out.println("s = " + s);
    }

}