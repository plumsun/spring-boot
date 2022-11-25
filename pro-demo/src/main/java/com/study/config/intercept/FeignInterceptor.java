package com.study.config.intercept;

import com.study.utils.TraceIdUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Service;

/**
 * feign调用传入traceId
 *
 * @author LiHaoHan
 * @date 2022/11/21
 */
@Service
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(TraceIdUtils.TRACE_ID, TraceIdUtils.getTraceId());
    }
}
