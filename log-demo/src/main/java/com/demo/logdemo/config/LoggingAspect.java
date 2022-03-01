package com.demo.logdemo.config;


import com.migu.gvpcore.core.BaseLoggingAspect;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect extends BaseLoggingAspect {

    @Value("${spring.application.name}")
    private String systemId;

    public LoggingAspect(Environment env) {
        super(env);
    }

    @Override
    @Pointcut("within(com.demo.logdemo.*) ")
    public void remoteCallPointcut() {
    }

    @Override
    @Around("remoteCallPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.remoteLogAround(joinPoint, systemId);
    }

    @Override
    @AfterThrowing(pointcut = "remoteCallPointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        super.remoteLogAfterThrowing(joinPoint, ex);
    }

}
