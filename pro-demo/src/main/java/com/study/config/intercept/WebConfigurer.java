package com.study.config.intercept;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc自定义配置
 *
 * @author LiHaoHan
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {


    /**
     * The Cors switch.
     */
    @Value("${ep.settings.corsSwitch:true}")
    Boolean corsSwitch;

    /**
     * 跨域处理
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        if (Boolean.TRUE.equals(corsSwitch)) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowCredentials(true)
                    .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                    .maxAge(3600);
        }
    }

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
    }
}
