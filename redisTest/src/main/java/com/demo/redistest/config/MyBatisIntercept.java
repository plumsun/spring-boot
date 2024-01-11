package com.demo.redistest.config;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.util.Properties;


@Component
@Intercepts({@Signature(
        type= Executor.class,
        method = "update",
        args = {MappedStatement.class,Object.class})})
public class MyBatisIntercept implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        /**
         * 业务逻辑，例如：
         *      在修改时进行修改时间的插入
         */
        return null;
    }

    @Override
    public Object plugin(Object target) {
        /**
         * 生产代理对象
         */
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        /**
         * 设置拦截器的属性
         */
    }
}
