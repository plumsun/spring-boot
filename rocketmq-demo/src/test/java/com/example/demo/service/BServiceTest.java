package com.example.demo.service;

import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author LiHaoHan Created on 2023/11/2
 */
// @RunWith(value = SpringRunner.class)
// @SpringBootTest
@Slf4j
public class BServiceTest {

    @Resource
    private BService bService;

    @Test
    public void get() {
        List<Integer> ids = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<User> users = bService.get(ids);
        Assert.assertNotNull("查询数据失败", users);
        log.info("查询结果：{}", users);
    }


    @Test
    public void getNumber() {
        CompletableFuture<Integer> aFuture = CompletableFuture.supplyAsync(this::aServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;});
        CompletableFuture<Integer> bFuture = CompletableFuture.supplyAsync(this::bServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;});
        CompletableFuture<Integer> cFuture = CompletableFuture.supplyAsync(this::cServiceAccumulate)
                .exceptionally(ex -> {
                    log.error("执行异常", ex);
                    return 0;});
        CompletableFuture<Object> all = CompletableFuture.allOf(aFuture, bFuture, cFuture)
                .thenApply((Function<Void, Object>) unused -> {
                    AtomicInteger atomicInteger = new AtomicInteger();
                    atomicInteger.getAndAdd(aFuture.join());
                    atomicInteger.getAndAdd(bFuture.join());
                    atomicInteger.getAndAdd(cFuture.join());
                    return atomicInteger.get();})
                .exceptionally((throwable) -> {
                    log.error("执行异常", throwable);
                    return 0;});
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