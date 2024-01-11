package com.demo.redistest.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @description:
 * @date: 2022/7/21 18:30
 * @author: LiHaoHan
 * @program: com.demo.config
 */
@Configuration
public class RedissonConfig {
    /**
     * 集成redisson客户端
     * @return
     * @throws IOException
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        Config config = Config.fromYAML(new ClassPathResource("redissonSingle.yml").getInputStream());
        return Redisson.create(config);
    }

}
