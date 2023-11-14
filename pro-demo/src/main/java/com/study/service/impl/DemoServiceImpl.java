package com.study.service.impl;

import com.study.config.AppContext;
import com.study.config.threadpool.CompletableFutures;
import com.study.config.threadpool.ThreadPoolExecutors;
import com.study.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author LiHaoHan Created on 2023/11/11
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public void threadTest() {
        // 生成多个异步任务
        Executor executors = ThreadPoolExecutors.newThreadPoolExecutors();
        List<CompletableFuture<Object>> futures = new ArrayList<>(3);
        ArrayList<Object> objects = new ArrayList<>(3);
        objects.add(1);
        objects.add(1);
        objects.add(1);
        objects.forEach((Object o) -> {
            CompletableFuture<Object> completableFuture = CompletableFutures
                    .orTimeout(CompletableFuture.supplyAsync(() -> {
                        log.info("线程上下文信息：{}", AppContext.getContext().getValue());
                        return null;
                    }, executors), 23, TimeUnit.SECONDS)
                    .exceptionally(ex -> null);
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
