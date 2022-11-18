package com.study.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: LogInterceptor
 * @Date: 2022/11/8
 * @Author: LiHaoHan
 * @Description:
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final String traceId = "traceId";

    // private static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //if(request.getHeader(traceId).isEmpty()){
        /*
            bdfc 76b2 f3ac 860b
            tarceId: ip + date +  + pid
         */
        //String trace = TraceIdUtils.createTraceId().toString();
        //System.out.println("string = " + trace);
        //    MDC.put(traceId,trace);
        //    log.info("traceId:{}",trace);
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove("traceId");
    }
}
