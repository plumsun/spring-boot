package com.study.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @program: AopAsync
 * @Date: 2022/11/11
 * @Author: LiHaoHan
 * @Description:
 */
@Component
public class AopAsync {

    @Async()
    public Future<Integer> exceptionTest() throws Exception{
        Thread.sleep(1000);
        return new AsyncResult<>(1);
    }
}
