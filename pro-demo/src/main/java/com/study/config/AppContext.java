package com.study.config;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.ttl.TransmittableThreadLocal;

import java.io.Serializable;

/**
 * 线程上下文
 * 需要在线程中传递信息，直接加参数以及get,set方法即可
 * 使用完毕一定要删除
 *
 * @author LiHaoHan
 * @date 2023/4/4
 */
public class AppContext implements Serializable {

    private static final long serialVersionUID = 1;

    private String value;

    private String token;

    private JSONObject jsonObject = new JSONObject();

    private static final TransmittableThreadLocal<AppContext> LOCAL = new TransmittableThreadLocal<AppContext>() {
        @Override
        protected AppContext initialValue() {
            return new AppContext();
        }
    };

    public static AppContext getContext() {
        return LOCAL.get();
    }

    public static void setContext(AppContext context) {
        LOCAL.set(context);
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}