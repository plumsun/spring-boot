package com.study.service.impl;

import com.study.async.AopAsync;
import com.study.service.AopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * @program: AopServiceImpl
 * @Date: 2022/11/11
 * @Author: LiHaoHan
 * @Description:
 */
@Slf4j
@Service
public class AopServiceImpl implements AopService {

    @Resource
    private AopAsync aopAsync;

    @Override
    public void asyncTest() throws Exception {
        //当前调用的异步是字面上的多线程，还是会影响到主线程的响应时间
        Future<Integer> future = aopAsync.exceptionTest();
        log.info(future.get().toString());
    }

    @Override
    public void asyncVoidTest() throws Exception {
        aopAsync.voidTest();
    }
}
