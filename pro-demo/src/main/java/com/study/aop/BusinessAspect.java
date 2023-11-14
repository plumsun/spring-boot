package com.study.aop;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.ValueFilter;
import com.google.common.collect.Lists;
import com.study.config.AppContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Aspect
@Component
@Slf4j
public class BusinessAspect {

    /**
     * ing ~
     */
    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Autowired
    HttpServletRequest request;

    /**
     * 切面
     */
    @Pointcut("within( com.study.controller..*)")
    public void businessAuthenticationPointcut() {
    }


    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("businessAuthenticationPointcut()")
    public Object authAroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint);
    }

    /**
     * 执行流程
     *
     * @param joinPoint the join point
     * @return the object
     * @throws Throwable the throwable
     */
    private Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // first
        this.doBefore(joinPoint);
        // seconds
        Object result = joinPoint.proceed();
        // Object parse json.
        this.doAfterReturning(joinPoint, result);
        return result;
    }

    /**
     * 前置
     *
     * @param joinPoint the join point
     */
    private void doBefore(JoinPoint joinPoint) {
        threadLocal.set(System.currentTimeMillis());
        Object[] args = joinPoint.getArgs();
        String test = request.getHeader("test");
        AppContext.getContext().setValue(test);
        print(args, request);
    }

    /**
     * 后置正常返回
     * 全局异常捕获后返回错误信息，会调用这个
     *
     * @param joinPoint the join point
     * @param result    the result
     */
    private void doAfterReturning(JoinPoint joinPoint, Object result) {
        String classname = joinPoint.getTarget().getClass().getSimpleName();
        if (classname.equals("PdfController")) {
            return;
        }
        long timeTaken = System.currentTimeMillis() - threadLocal.get();
        threadLocal.remove();
        String json = this.excludeParam(result);
        log.info("系统结束调用,耗时 = {}, result = {}", timeTaken, json);
        AppContext.removeContext();
    }


    public void print(Object[] objects, HttpServletRequest request) {
        // 打印请求 url
        log.info("---before URL: [{}],IP: [{}],Request Method: [{}],Request Args: [{}]",
                request.getRequestURL().toString(),
                request.getRemoteAddr(),
                request.getMethod(),
                getParams(objects));
    }

    /**
     * 获取请求参数
     *
     * @param objects the objects
     * @return params
     */
    private static String getParams(Object[] objects) {
        StringBuilder params = new StringBuilder();
        if (objects != null) {
            for (Object arg : objects) {
                // 判断参数类型
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest)
                        || (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                try {
                    params.append(JSON.toJSONString(arg));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        return params.toString();
    }

    /**
     * 序列化String时排除出指定字段
     *
     * @param params body
     * @return string
     */
    private String excludeParam(Object params) {
        List<String> list = Lists.newArrayList("password", "file");
        ValueFilter valueFilter = (Object object, String name, Object value) -> {
            if (list.contains(name)) {
                return "敏感数据";
            }
            return value;
        };
        return JSON.toJSONString(params, valueFilter);
    }
}
