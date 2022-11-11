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
        Future<Integer> future = aopAsync.exceptionTest();
        log.info(future.get().toString());
    }
}
