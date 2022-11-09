
import com.study.App;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Comparator;
import java.util.Map;

/**
 * @description:
 * @date: 2021/11/4 17:01
 * @author: LiHaoHan
 * @program: PACKAGE_NAME
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
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
        log.info("body:{}",body);
        JSONObject jsonObject1 = JSONObject.fromObject(body);
        String data = jsonObject1.optString("data");
        log.info("data:{}",data);
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
        log.info(s);
    }

    @Test
    public void xmlToJson(){
        XMLSerializer serializer = new XMLSerializer();
        String json = serializer.readFromFile(new File("D:\\json.xml")).toString();
        log.info(json);
        com.study.entity.Test test = com.alibaba.fastjson.JSONObject.parseObject(json, com.study.entity.Test.class);
        log.info("{}",test);
    }

}