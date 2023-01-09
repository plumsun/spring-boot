package com.study.entity.resp;


import com.alibaba.fastjson2.JSONObject;
import com.study.utils.DateUtils;

/**
 * @author User Response with single record to return
 */
public class RestResult {

    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    private Integer code;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private Object data;


    public RestResult() {
        this.timestamp = DateUtils.currentMillisecondTimestamp();
    }

    public RestResult(Object data) {
        this.data = data;
        this.timestamp = DateUtils.currentMillisecondTimestamp();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
