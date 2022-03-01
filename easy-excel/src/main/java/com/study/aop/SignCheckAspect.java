package com.study.aop;

import cn.hutool.core.util.StrUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

// @Aspect
// @Component
public class SignCheckAspect {




    @Pointcut("execution(* com.study.controller.*.*(..))) ")
    public void logPointCut() {}


    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        String tenantId = request.getHeader("tenantId");
        String version = request.getHeader("version");
        String userId = request.getHeader("userId");
        String mobile = request.getHeader("mobile");
        String timestamp = request.getHeader("timestamp");
        String signature = request.getHeader("signature");

        
        if (!StrUtil.hasBlank(tenantId, version, userId, timestamp, signature,mobile)) {
            System.out.println("tenantId="+tenantId +";version=" +version +";userId=" + userId + ";timestamp=" +timestamp +";signature="+signature);
            System.out.println("mobile"+mobile);
        	return "error";
        }
        return point.proceed();
    }

}
