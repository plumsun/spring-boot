package com.demo.redistest.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class MyBatisConfig {

    @Autowired
    private MyBatisIntercept myBatisIntercept;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //将自定义拦截器装入mybatis
        bean.setDataSource(dataSource);
        bean.setPlugins(myBatisIntercept);
        return bean;
    }
}
