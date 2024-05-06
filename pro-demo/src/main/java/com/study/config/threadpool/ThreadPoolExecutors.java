package com.study.config.threadpool;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.study.config.AppContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 自定义线程池
 *
 * @author LiHaoHan Created on 2023/7/23
 */
@Configuration
public class ThreadPoolExecutors {

    private ThreadPoolExecutors() {
        throw new IllegalStateException("Utility class");
    }

    private static final int CORES = Runtime.getRuntime().availableProcessors();

    private static final int LIMIT_SIZE = 10000;

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORES,
            CORES * 2,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(LIMIT_SIZE),
            new ThreadFactory() {
                final AtomicInteger a = new AtomicInteger(0);
                @Override
                public Thread newThread(@Nonnull Runnable r) {
                    return new Thread(r, "ThreadPool-Future-" + a.getAndAdd(1));
                }
            }, new ThreadPoolExecutor.AbortPolicy());

    public static Executor newThreadPoolExecutors() {
        return TtlExecutors.getTtlExecutor(executor);
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>(3);
        objects.add(1);
        objects.add(1);
        objects.add(1);
        objects.forEach((o) -> {
            CompletableFuture<Void> completableFuture = CompletableFutures
                    .orTimeout(CompletableFuture.runAsync(() -> {
                        System.out.println("atawetawetawet");
                    }), 23, TimeUnit.SECONDS)
                    .exceptionally(ex -> {
                        return null;
                    });
        });
        // CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }

    private static void returnObject() {
        AppContext.getContext().setValue("hello");
        // 生成多个异步任务
        Executor executors = ThreadPoolExecutors.newThreadPoolExecutors();
        List<CompletableFuture<Object>> futures = new ArrayList<>(3);
        ArrayList<Object> objects = new ArrayList<>(3);
        objects.add(1);
        objects.add(1);
        objects.add(1);
        objects.forEach((o) -> {
            CompletableFuture<Object> completableFuture = CompletableFutures
                    .orTimeout(CompletableFuture.supplyAsync(() -> {
                        System.out.println(AppContext.getContext().getValue());
                        return null;
                    }, executors), 23, TimeUnit.SECONDS)
                    .exceptionally(ex -> {
                        return null;
                    });
            futures.add(completableFuture);
        });
        List<Object> join = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream().map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()))
                .join();
        System.out.println("join = " + join);
    }
}
