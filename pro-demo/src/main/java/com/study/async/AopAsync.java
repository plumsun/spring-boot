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

    /**
     * 当主方法需要通过异步方法的返回值进行逻辑处理时，此时异步方法的执行时间会影响主方法的响应时间，
     * 如果没有必要的话建议不要使用带返回值的异步方法进行逻辑处理。
     * @return Futrue
     * @throws Exception
     */
    @Async
    public Future<Integer> exceptionTest() throws Exception{
        Thread.sleep(1000);
        return new AsyncResult<>(1);
    }

    @Async
    public void voidTest() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("111");
    }
}
