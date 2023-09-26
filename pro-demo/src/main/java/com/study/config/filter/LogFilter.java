package com.study.config.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * servlet容器过滤器，生命周期跟容器一致
 *
 * @author LiHaoHan
 */
@Slf4j
public class LogFilter implements Filter {

    /**
     * 容器初始化完成时调用，初始化过滤器，
     *
     * @param filterConfig The configuration information associated with the
     *                     filter instance being initialised
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("filter init");
    }

    /**
     * 每次servlet请求到达时调用一次
     *
     * @param request  The request to process
     * @param response The response associated with the request
     * @param chain    Provides access to the next filter in the chain for this
     *                 filter to pass the request and response to for further
     *                 processing
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("filter doFilter");
    }

    /**
     * 容器销毁时调用
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("filter doFilter");
    }
}
