package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LiHaoHan Created on 2023/11/2
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
@Slf4j
public class BServiceTest {

    @Resource
    private AService aService;

    @Test
    public void get() {
    }


    @Test
    public void getNumber() {
        ArrayList<CompletableFuture<Integer>> futures = new ArrayList<>(3);
        CompletableFuture<Integer> aFuture = CompletableFuture.supplyAsync(this::aServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;
                });
        CompletableFuture<Integer> bFuture = CompletableFuture.supplyAsync(this::bServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;
                });
        CompletableFuture<Integer> cFuture = CompletableFuture.supplyAsync(this::cServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;
                });
        futures.add(aFuture);
        futures.add(bFuture);
        futures.add(cFuture);
        AtomicInteger atomicInteger = new AtomicInteger();
        CompletableFuture<Integer> all = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(unused -> {
                    futures.stream()
                            // 获取所有 future 执行结果
                            .map(CompletableFuture::join)
                            .forEach(atomicInteger::addAndGet);
                    return atomicInteger.get();
                })
                .exceptionally((throwable) -> {
                    log.error("执行异常", throwable);
                    return 0;
                });
        log.info("最终结果：{}", all.join());
    }


    private Integer aServiceAccumulate() {
        log.info("AService start ....");
        return 5;
    }

    private Integer bServiceAccumulate() {
        log.info("BService start ....");
        return 3;
    }

    private Integer cServiceAccumulate() {
        log.info("CService start ....");
        return 4;
    }


}