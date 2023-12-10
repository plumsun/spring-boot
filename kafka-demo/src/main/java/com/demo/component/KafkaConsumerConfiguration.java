package com.demo.component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

/**
 * kafka消费者配置
 *
 * @author LiHaoHan
 */
@Slf4j
@Configuration
@Data
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String consumerBootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private Boolean enableAutoCommit;


    /**
     * 1 读取配置文件的 大数据kafka 配置项
     *
     * @return map
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // 设置消费者集群地址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootstrapServers);
        // 消费组id
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        // 是否自动消费
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        // 序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * 2 大数据kafka  消费工厂
     *
     * @return consumer factory
     */
    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * 3 大数据kafka 的监听通道
     *
     * @return kafka listener container factory
     */
    @Transactional
    @Bean(name = "KafkaListenerContainerFactory")
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactoryK2() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        // 注入消费者工厂
        factory.setConsumerFactory(consumerFactory());
        // 设置消费线程数量
        factory.setConcurrency(5);
        // 设置消息处理超时时间，默认为5s
        factory.getContainerProperties().setPollTimeout(10000);
        BackOff backOff = new FixedBackOff(3000, 2);
        // 失败重试策略，默认放入死信队列，队列名为：当前 topicName + .DLT
        factory.setCommonErrorHandler(new DefaultErrorHandler(backOff));
        return factory;
    }
}