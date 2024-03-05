package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate() throws InterruptedException {
        // 退避策略
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        ThreadWaitSleeper sleeper = new ThreadWaitSleeper();
        sleeper.sleep(1);
        backOffPolicy.withSleeper(sleeper);
        backOffPolicy.setMultiplier(3D);
        backOffPolicy.setInitialInterval(1000);

        // 重试策略
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);

        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        // retryTemplate.registerListener(new MyRetryListener());
        return retryTemplate;
    }
}