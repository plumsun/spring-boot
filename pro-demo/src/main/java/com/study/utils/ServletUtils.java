package com.study.utils;

import com.alibaba.fastjson2.JSON;
import com.study.config.AppContext;
import com.study.entity.BusinessCode;
import com.study.exception.ResultBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Servlet utils.
 *
 * @author LiHaoHan
 * @date 2023 /2/20
 */
@Slf4j
@Component
public class ServletUtils {


    /**
     * 设置多线程共享属性
     *
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setAttribute(String key, T value) {
        AppContext.getContext().getJsonObject().put(key, value);
    }

    /**
     * 获取request域中指定属性值
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getAttribute(String key, Class<T> clazz) {
        Object value = AppContext.getContext().getJsonObject().get(key);
        Optional<Object> optional = Optional.ofNullable(value);
        if (optional.isPresent()) {
            return JSON.to(clazz, optional.get());
        }
        throw new ResultBaseException(BusinessCode.CODE_500008);
    }
}
