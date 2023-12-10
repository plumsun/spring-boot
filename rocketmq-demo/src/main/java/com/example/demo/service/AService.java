package com.example.demo.service;

import com.example.demo.entity.User;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

/**
 * @author LiHaoHan Created on 2023/12/7
 */
public interface AService {

    public List<User> get(List<Integer> ids);
    @Retryable(
            maxAttempts = 6,
            backoff = @Backoff(
                    delay = 100,
                    maxDelay = 100,
                    multiplier = 2,
                    random = true), include = {Exception.class}
    )
    void test() ;
    @Recover
    void recover();
}
