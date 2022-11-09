package com.study.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Aspect
@Component
@Slf4j
public class BusinessAspect {


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
    @Before("businessAuthenticationPointcut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        log.info("前置通知");
        Object[] args = joinPoint.getArgs();
        System.out.println("args = " + Arrays.toString(args));
        // 开始打印请求日志
        print(joinPoint);
    }

    /**
     * 后置通知
     *
     * @param joinPoint
     * @param rvt
     * @return
     */
    @AfterReturning(returning = "rvt",
            pointcut = "(within(@org.springframework.web.bind.annotation.RestController *)" +
                    " || within(@org.springframework.stereotype.Controller *))")
    public Object afterExec(JoinPoint joinPoint, Object rvt) {
        log.info("后置通知");
        String json = excludeParam(rvt);
        if (!json.contains("\"flag\":\"T\"") && !json.contains("\"message\":\"Success\"")) {
            log.info("--- AfterReturningResult: " + json);
        }
        return rvt;
    }


    public void print(JoinPoint point) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", getParams(point));
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint
     * @return
     */
    private static String getParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder params = new StringBuilder();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                //判断参数类型
                if ((arg instanceof HttpServletResponse) || (arg instanceof HttpServletRequest)
                        || (arg instanceof MultipartFile) || (arg instanceof MultipartFile[])) {
                    continue;
                }
                try {
                    params.append(JSONObject.toJSONString(arg));
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        return params.toString();
    }

    /**
     * 序列化String时排除出指定字段
     * @param params body
     * @return
     */
    private String excludeParam(Object params){
        String[] excludeProperties = {"password", "file"};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter filter = filters.addFilter();
        filter.addExcludes(excludeProperties);
        String json = JSONObject.toJSONString(params, filter);
        log.info("请求参数: {}",json);
        return json;
    }
}
