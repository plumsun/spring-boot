package com.example.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaoHan Created on 2023/10/15
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread.pool")
public class ThreadPoolConfiguration {

    /**
     * 线程池大小
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 线程池最大容量
     */
    private int maximumPoolSize = corePoolSize * 2;

    /**
     * 线程保留时间
     */
    private long keepAliveTime = 60L;

    /**
     * 任务队列最大容量
     */
    private int workQueueSize = 10000;
}
