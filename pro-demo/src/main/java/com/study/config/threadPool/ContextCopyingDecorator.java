package com.study.config.threadPool;

import com.study.utils.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

/**
 * spring多线程间数据传递(废弃)
 *
 * @author LiHaoHan
 * @date 2022/11/21
 */
@Slf4j
public class ContextCopyingDecorator implements TaskDecorator {

    /**
     * 异步线程执行实例
     *
     * @param runnable
     * @return
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        try {
            RequestAttributes context = RequestContextHolder.currentRequestAttributes();
            Map<String, String> previous = MDC.getCopyOfContextMap();
            //3
            return () -> {
                log.info("child thread traceId:{}", previous.get(TraceIdUtils.TRACE_ID));
                try {
                    RequestContextHolder.setRequestAttributes(context);
                    MDC.setContextMap(previous);
                    //3
                    runnable.run();
                } finally {
                    log.error("thread error:{}", previous.get(TraceIdUtils.TRACE_ID));
                    RequestContextHolder.resetRequestAttributes();
                    MDC.clear();
                }
            };
        } catch (IllegalStateException e) {
            log.error("thread decorate error", e);
            return runnable;
        }
    }

}
