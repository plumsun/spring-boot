package com.study.config.threadpool;

import cn.hutool.core.collection.CollUtil;
import com.study.utils.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * @author LiHaoHan
 * @date 2022/11/22
 */
@Slf4j
public class ThreadPoolExecutor extends ThreadPoolTaskExecutor {


    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Map<String, String> content = MDC.getCopyOfContextMap();
        return super.submit(() -> {
            T result = null;
            if (CollUtil.isEmpty(content)) {
                MDC.put(TraceIdUtils.TRACE_ID, TraceIdUtils.getTraceId());
            } else MDC.setContextMap(content);
            try {
                result = task.call();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception", e);
                }
            }
            return result;
        });
    }

    @Override
    public void execute(Runnable task) {
        Map<String, String> content = MDC.getCopyOfContextMap();
        super.execute(() -> {
            if (CollUtil.isEmpty(content)) {
                MDC.put(TraceIdUtils.TRACE_ID, TraceIdUtils.getTraceId());
            } else MDC.setContextMap(content);
            try {
                task.run();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    MDC.clear();
                } catch (Exception e) {
                    log.warn("MDC clear exception", e);
                }
            }
        });
    }
}
