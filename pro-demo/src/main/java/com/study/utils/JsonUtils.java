package com.study.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * json工具类
 */
@Slf4j
public class JsonUtils {

    private final static String JSON_SEPERATOR = "%JS%";
    public static final String DEFAULT_CHARSET = "UTF-8";


    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 序列化的时候的特性
     * DisableCircularReferenceDetect 消除对同一对象循环引用的问题，默认为false
     * SortField 按字段名称排序后输出。默认为false
     * WriteDateUseDateFormat 全局修改日期格式,默认为false。
     */
    private static SerializerFeature[] serializerFeatures = {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.MapSortField,
            SerializerFeature.WriteDateUseDateFormat
    };

    /**
     * 反序列化的时候的特性
     * OrderedField 属性保持原来的顺序
     */
    private static Feature[] features = {
            Feature.OrderedField
    };

    public static SerializerFeature[] getSerializerFeatures() {
        return serializerFeatures;
    }

    public static void setSerializerFeatures(SerializerFeature[] serializerFeatures) {
        JsonUtils.serializerFeatures = serializerFeatures;
    }

    public static Feature[] getFeatures() {
        return features;
    }

    public static void setFeatures(Feature[] features) {
        JsonUtils.features = features;
    }

    public static JSONObject jsonToBean(String json) {
        return JSON.parseObject(json, features);
    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz, features);
    }

    public static <T> String beanToJson(T entity) {
        return JSON.toJSONString(entity, serializerFeatures);
    }

    public static <T> String beanToJsonWithDateFormat(T entity, String dateFormat) {
        return JSON.toJSONStringWithDateFormat(entity, dateFormat, serializerFeatures);
    }


    public static JSONObject beanToBean(Object entity) {
        return jsonToBean(beanToJson(entity));
    }


    public static <T> T beanToBean(Object entity, Class<T> clazz) {
        return jsonToBean(beanToJson(entity), clazz);
    }


    public static JSONArray jsonToArray(String json) {
        return JSON.parseArray(json);
    }

    public static <T> List<T> jsonToArray(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    public static JSONArray beanToArray(Object entity) {
        String jsonString = beanToJson(entity);
        return jsonToArray(jsonString);
    }

    public static <T> List<T> beanToArray(Object entity, Class<T> clazz) {
        String jsonString = beanToJson(entity);
        return jsonToArray(jsonString, clazz);
    }

    public static <T> T jsonToBeanWithException(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz, features);
    }

    public static <T> String beanToJsonWithNullValue(T entity) {
        List<SerializerFeature> serializerFeatures = new ArrayList<>(Arrays.asList(JsonUtils.serializerFeatures));
        serializerFeatures.add(SerializerFeature.WriteMapNullValue);
        String jsonString = JSON.toJSONString(entity, serializerFeatures.toArray(new SerializerFeature[]{}));
        return "null".equals(jsonString) ? null : jsonString;
    }

    public static JSON filePath2JSON(String filePath) throws IOException {
        try (InputStream inputStream = JsonUtils.class.getClassLoader().getResourceAsStream(filePath)) {
            return (JSON) JSON.parse(IOUtils.toString(inputStream, DEFAULT_CHARSET));
        }
    }
}
