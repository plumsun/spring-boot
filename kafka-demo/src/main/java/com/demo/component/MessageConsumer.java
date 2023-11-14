package com.demo.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * kakfa消费者演示
 *
 * @author LiHaoHan Created on 2023/9/5
 */
@Slf4j
@Component
public class MessageConsumer {

    /**
     * The Transaction template.
     */
    @Autowired
    protected TransactionTemplate transactionTemplate;

    @RetryableTopic
    @KafkaListener(topics = "${spring.kafka.consumer.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "KafkaListenerContainerFactory")
    private void receiverMsg(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack) {
        String recordString = consumerRecord.toString();
        log.info("[kafka接收回执消息]__{}", recordString);
        long start = System.currentTimeMillis();
        process(recordString);
        log.info("[回执处理]_总耗时_{}__ms", System.currentTimeMillis() - start);
        ack.acknowledge();
    }

    /**
     * 业务处理
     *
     * @param msg the msg
     */
    public void process(String msg) {
        // TODO document why this method is empty
    }
}
