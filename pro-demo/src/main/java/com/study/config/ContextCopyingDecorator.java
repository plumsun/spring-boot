package com.study.config;

import com.study.util.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * spring多线程间数据传递
 *
 * @author LiHaoHan
 * @date 2022/11/21
 */
@Slf4j
public class ContextCopyingDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = (HttpServletRequest) attributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String val = request.getHeader(TraceIdUtils.TRACE_ID);

            return () -> {

                try {
                    //将父线程中traceId传递到子线程中
                    TraceIdUtils.setTraceId(val);
                    //run
                    runnable.run();
                } catch (Exception e) {
                    log.error("Child Thread Run Error", e);
                    throw e;
                } finally {
                    //清除子线程中数据
                    TraceIdUtils.remove();
                }

            };

        } catch (Exception e) {
            log.error("Child Thread Config Error", e);
            return runnable;
        }
    }
}
