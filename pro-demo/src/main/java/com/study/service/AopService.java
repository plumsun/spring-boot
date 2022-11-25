package com.study.service;

import org.springframework.stereotype.Service;

/**
 * @program: AopService
 * @Date: 2022/11/11
 * @Author: LiHaoHan
 * @Description: aop业务接口
 */
@Service
public interface AopService {

    void asyncTest() throws Exception;

    void asyncVoidTest() throws Exception;
}
