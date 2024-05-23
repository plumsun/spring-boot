package com.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

//@Configuration
public class WebFilterConfig {

    @Bean
    public FilterRegistrationBean<GzipResponseFilter> gzipResponseFilter() {
        FilterRegistrationBean<GzipResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GzipResponseFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URLs
        return registrationBean;
    }
}
