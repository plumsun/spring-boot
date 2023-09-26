package com.study.entity.resp;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;

/**
 * @author LiHaoHan
 * @date 2023/2/24
 */
public class Response {

    private static final long serialVersionUID = 6310663381656773827L;

    private int code;

    private String message;

    private Object data;

    /**
     * timestamp
     */
    private long timestamp;

    public Response() {
        this.timestamp = DateUtil.currentSeconds();
    }

    public Response(int code, String message, Object data) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.timestamp = DateUtil.currentSeconds();
    }

    public Response(int code, Object data) {
        this.code = code;
        this.data = data;
        this.timestamp = DateUtil.currentSeconds();
    }

    public Response(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = DateUtil.currentSeconds();
    }

    public Response(CodeI codeI) {
        this.code = codeI.getCode();
        this.message = codeI.getMessage();
        this.timestamp = DateUtil.currentSeconds();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public static Response success(Object data) {
        Response r = new Response(200, data);
        r.setMessage("Success");
        return r;
    }

    public static Response success() {
        Response r = new Response(200, null);
        r.setMessage("Success");
        return r;
    }

    public static Response success(String message, Object data) {
        Response r = new Response(200, message, data);
        return r;
    }

    public static Response err() {
        Response r = new Response(500, null);
        r.setMessage("服务异常");
        return r;
    }


    public static Response err(String msg, Object data) {
        Response r = new Response(500, data);
        r.setMessage(msg);
        return r;
    }

    public static Response err(String msg) {
        Response r = new Response(500, msg);
        r.setData(null);
        return r;
    }

    public static Throwable err(Throwable cause) throws Throwable {
        throw cause.getClass().newInstance();
    }

    public static Response err(int code, String msg) {
        Response r = new Response(code, msg);
        r.setData(null);
        return r;
    }


    public static Response err(CodeI codeI) {
        Response r = new Response(codeI);
        r.setData(null);
        return r;
    }

    public static Response build(Integer code, String msg, Object data) {
        Response r = new Response();
        r.setCode(code);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }
}
