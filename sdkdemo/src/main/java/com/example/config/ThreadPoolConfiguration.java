package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiHaoHan Created on 2023/10/15
 */
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


    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getWorkQueueSize() {
        return workQueueSize;
    }

    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }
}
