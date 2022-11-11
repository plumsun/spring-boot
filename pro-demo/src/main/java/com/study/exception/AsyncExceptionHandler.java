package com.study.exception;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * @program: AsyncExceptionHandler
 * @Date: 2022/11/11
 * @Author: LiHaoHan
 * @Description:
 */
@Configuration
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {

    }
}
