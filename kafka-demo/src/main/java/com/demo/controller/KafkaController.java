package com.demo.controller;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * kafka生产者演示
 *
 * @author LiHaoHan
 */
@Slf4j
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    /**
     * The Kafka template.
     */
    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * The Topic.
     */
    @Value("${spring.kafka.producer.topic}")
    String topic;

    /**
     * The Path.
     */
    @Value("${file.path:d:/1}")
    String path;

    /**
     * kafka发送消息，异步发送，接收ack
     *
     * @param value the value
     */
    @GetMapping("/asyncSend")
    public void asyncSend(@RequestParam String value) {
        log.info("kafka send value:{}", value);
        kafkaTemplate.send(topic, value).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(@NonNull Throwable ex) {
                log.error("kafka send error,message:{}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.error("kafka send success,topic:{},partition:{},offset:{}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        });
    }

    /**
     * 同步发送报文
     *
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    @GetMapping("/sendReceipt")
    public void sendReceipt(@RequestParam String value) throws Exception {
        log.info("kafka send value:{}", value);
        try {
            SendResult<String, Object> result = kafkaTemplate.send(topic, value).get();
            log.info("--->kafka发送成功,消息id:{},send result:{}", value, JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("--->kafka发送失败,消息id:{}", value, e);
            throw e;
        }
    }

    /**
     * Value test string.
     *
     * @return the string
     */
    @GetMapping("getProp")
    public String valueTest() {
        return path;
    }
}
