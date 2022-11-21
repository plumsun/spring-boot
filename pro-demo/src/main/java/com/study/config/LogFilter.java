package com.study.config;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author LiHaoHan
 * @date 2022/11/21
 */
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter doFilter");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("filter doFilter");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("filter doFilter");
    }
}
