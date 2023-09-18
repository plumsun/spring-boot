package com.demo.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * jedis config
 *
 * @author LiHaoHan
 * @date 2023/2/28
 */
@Configuration
@Data
public class JedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWait;
    @Value("${spring.redis.timeout}")
    private Integer timeout;
    @Value("${spring.redis.cluster.max-redirects}")
    private Integer maxRedirects;


    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> hosts = new HashSet<>();
        String[] split = nodes.split(",");
        for (String node : split) {
            String[] portArray = node.split(":");
            HostAndPort host = new HostAndPort(portArray[0], Integer.parseInt(portArray[1]));
            hosts.add(host);
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(this.maxIdle);
        config.setMaxTotal(this.maxActive);
        config.setMinIdle(this.minIdle);
        config.setMaxWaitMillis(this.maxWait);
        return new JedisCluster(hosts, this.timeout, this.maxRedirects, config);
    }
}
