package com.example.factory;

import com.example.config.ThreadPoolConfiguration;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池
 *
 * @author LiHaoHan Created on 2023/7/23
 */
@SuppressWarnings("all")
@Component
public class ThreadPoolExecutors {

    private static ThreadPoolConfiguration threadPoolConfiguration;


    public ThreadPoolExecutors(ThreadPoolConfiguration threadPoolConfiguration) {
        this.threadPoolConfiguration = threadPoolConfiguration;
    }

    private static volatile ThreadPoolExecutor executor;

    public static ThreadPoolExecutor newThreadPoolExecutors() {
        if (Objects.isNull(executor)) {
            synchronized (ThreadPoolExecutors.class) {
                if (Objects.isNull(executor)) {
                    new ThreadPoolExecutor(
                            threadPoolConfiguration.getCorePoolSize(),
                            threadPoolConfiguration.getMaximumPoolSize(),
                            threadPoolConfiguration.getKeepAliveTime(),
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(threadPoolConfiguration.getWorkQueueSize()),
                            new ThreadFactory() {
                                final AtomicInteger a = new AtomicInteger(0);

                                @Override
                                public Thread newThread(@NonNull Runnable r) {
                                    return new Thread(r, "ThreadPool-Future-" + a.getAndAdd(1));
                                }
                            }, new ThreadPoolExecutor.AbortPolicy());
                }
            }
        }
        return executor;
    }
}
