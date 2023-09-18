package com.demo.component;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;

/**
 * The type Kafka produce.
 *
 * @author LiHaoHan
 */
@Component
@Slf4j
public class KafkaProduce {

    /**
     * 拿取 配置大数据的kafka 执行对象
     */
    @Resource(name = "KafkaTemplate")
    protected KafkaTemplate<String, String> kafkaTemplate;

    /**
     * The Send topic name.
     */
    @Value(value = "${BigData.kafka.producer.send.topic.name}")
    private String sendTopicName;

    /**
     * 同步发送报文
     *
     * @param dto 回执对象
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public void sendReceipt(KafkaDto dto) throws Exception {
        String json = JSON.toJSONString(dto);
        String messageId = dto.getMessageId();
        log.info("kafka发送消息,消息id:{},data:{}", messageId, JSON.toJSONString(dto));
        try {
            SendResult<String, String> result = kafkaTemplate.send(sendTopicName, json).get();
            log.info("kafka发送成功,消息id:{},send result:{}", messageId, JSON.toJSONString(result));
        } catch (Exception e) {
            log.error(MessageFormat.format("kafka发送失败,消息id:{0}", messageId), e);
            throw e;
        }
    }

    /**
     * 异步发送报文，业务系统需提供回调接口
     *
     * @param dto the dto
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public void asyncSendReceipt(KafkaDto dto) throws Exception {
        String json = JSON.toJSONString(dto);
        String messageId = dto.getMessageId();
        log.info("kafka发送消息,消息id:{},data:{}", messageId, JSON.toJSONString(dto));
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(sendTopicName, json);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(@Nullable Throwable ex) {
                log.error(MessageFormat.format("--->kafka发送失败,消息id:{0}", messageId), ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("--->kafka发送成功,消息id:{},send result:{}", messageId, JSON.toJSONString(result));
            }
        });
    }
}


