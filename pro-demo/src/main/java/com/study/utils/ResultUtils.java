package com.study.utils;

import com.study.entity.resp.BaseErrorInfo;
import com.study.entity.resp.RestResult;

/**
 * Returns the object handler class
 *
 * @author LiHaoHan
 * @date 2022/11/21
 */
public class ResultUtils {


    public static  RestResult success(Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(200);
        restResult.setMessage("success");
        restResult.setData(data);
        return restResult;
    }
    public static  RestResult success() {
        RestResult restResult = new RestResult();
        restResult.setCode(200);
        restResult.setMessage("success");
        restResult.setData(null);
        return restResult;
    }

    public static  RestResult err() {
        RestResult restResult = new RestResult();
        restResult.setCode(500);
        restResult.setMessage("error");
        restResult.setData(null);
        return restResult;
    }

    public static  RestResult err(Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(500);
        restResult.setMessage("error");
        restResult.setData(data);
        return restResult;
    }

    public static  RestResult err(String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(500);
        restResult.setMessage(msg);
        restResult.setData(null);
        return restResult;
    }

    public static  RestResult err(BaseErrorInfo info) {
        RestResult restResult = new RestResult();
        restResult.setCode(info.getResultCode());
        restResult.setMessage(info.getResultMsg());
        restResult.setData(null);
        return restResult;
    }

    public static  RestResult of(Integer code, String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMessage(msg);
        restResult.setData(null);
        return restResult;
    }

    public static  RestResult build(Integer code, String msg, Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMessage(msg);
        restResult.setData(data);
        return restResult;
    }

}
