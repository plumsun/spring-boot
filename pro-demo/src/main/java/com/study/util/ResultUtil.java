package com.study.util;

import com.study.entity.resp.BaseErrorInfo;
import com.study.entity.resp.RestResult;

/**
 * Returns the object handler class
 *
 * @author LiHaoHan
 * @date 2022/11/21
 */
public class ResultUtil<T> {


    public static <T> RestResult<T> success(T data) {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(200);
        restResult.setMessage("success");
        restResult.setData(data);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

    public static <T> RestResult<T> err() {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(500);
        restResult.setMessage("error");
        restResult.setData(null);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

    public static <T> RestResult<T> err(T data) {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(500);
        restResult.setMessage("error");
        restResult.setData(data);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

    public static <T> RestResult<T> err(BaseErrorInfo info) {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(info.getResultCode());
        restResult.setMessage(info.getResultMsg());
        restResult.setData(null);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

    public static <T> RestResult<T> of(Integer code, String msg) {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(code);
        restResult.setMessage(msg);
        restResult.setData(null);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

    public static <T> RestResult<T> build(Integer code, String msg, T data) {
        RestResult<T> restResult = new RestResult<T>();
        restResult.setCode(code);
        restResult.setMessage(msg);
        restResult.setData(data);
        restResult.setTimestamp(DateUtil.currentMillisecondTimestamp());
        return restResult;
    }

}
