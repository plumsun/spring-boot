package com.study.config.intercept;

import com.study.config.AppContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * springmvc 拦截器
 *
 * @author LiHaoHan
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AppContext.getContext().setValue(Thread.currentThread().getName());
        log.info("DispatcherServlet执行链,接口执行之前,当前调用链线程名:{}", Thread.currentThread().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        String value = AppContext.getContext().getValue();
        System.out.println("value = " + value);
        log.info("DispatcherServlet执行链,业务接口执行完毕,视图处理之前,当前调用链线程名:{}", Thread.currentThread().getName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String value = AppContext.getContext().getValue();
        System.out.println("value = " + value);
        log.info("DispatcherServlet执行链,视图处理后,当前调用链线程名:{}", Thread.currentThread().getName());
    }
}
