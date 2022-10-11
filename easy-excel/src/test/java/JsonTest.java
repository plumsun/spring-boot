
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.study.entity.RestResult;
import com.study.entity.TestDemo;
import net.sf.json.JSONObject;
import netscape.javascript.JSException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @description:
 * @date: 2021/11/4 17:01
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@SpringBootTest
public class JsonTest {
    @Test
    public void test(){
        String str="{\n" +
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


    @Test
    public void test1(){
        HashMap<Object, Object> map = new HashMap<>();
        map.entrySet().stream().forEach(System.out::println);
    }


    @Test
    public void test2(){
        Object set = set();
        cn.hutool.json.JSONObject object = JSONUtil.parseObj(set);
        HashMap map = object.toBean(HashMap.class);
        Object o = map.get("2");
        if(!ObjectUtil.isEmpty(o)){
            System.out.println("o = " + o);
        }
        System.out.println("object = " + map);
    }

    @Test
    public void test3(){
        RestResult chenggong = RestResult.T("chenggong");

        System.out.println("result = " + chenggong);
    }



    private Object set(){
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data","data");
        map.put("pk","1");
        System.out.println("map = " + map);
        TestDemo testDemo = new TestDemo();
        testDemo.setId("1");
        testDemo.setObj(map);
        return map;
    }
}