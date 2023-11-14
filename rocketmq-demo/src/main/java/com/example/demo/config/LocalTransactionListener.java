package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RocketMQ 本地事务反查
 *
 * @author LiHaoHan Created on 2023/10/18
 */
@Slf4j
@Component
public class LocalTransactionListener implements TransactionListener {

    private final ConcurrentHashMap<String, LocalTransactionState> transactionState = new ConcurrentHashMap<>();

    /**
     * 当生产者收到MQ Server 响应的 half 消息成功后，会调用当前方法执行本地事务
     *
     * @param msg Half(prepare) message
     * @param arg Custom business parameter
     * @return transaction state
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        LocalTransactionState state;
        try {
            // note : 2023/10/18 业务逻辑
            state = LocalTransactionState.COMMIT_MESSAGE;
            transactionState.put(msg.getTransactionId(), state);
            return state;
        } catch (Exception e) {
            log.error("事务执行失败", e);
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        transactionState.put(msg.getTransactionId(), state);
        return state;
    }

    /**
     * 如果生产者未响应正常消息到server端时，会调用当前方法查询本地事务执行状态
     *
     * @param msg Check message
     * @return transaction state
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return transactionState.get(msg.getTransactionId());
    }
}
