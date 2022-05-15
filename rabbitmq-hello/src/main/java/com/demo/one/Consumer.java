package com.demo.one;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @description:
 * @date: 2022/3/1 16:19
 * @author: LiHaoHan
 * @program: com.demo.one
 */
public class Consumer {


    private static final String QUEUE_NAME = "HELLO WORLD";


    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.15.50.129");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        /**
         * String queue 队列名
         * boolean autoAck 自动回复
         * DeliverCallback deliverCallback 传递回调
         * CancelCallback cancelCallback 取消回调
         */
        channel.basicConsume(QUEUE_NAME, true, new DeliverCallback() {
            @Override
            public void handle(String consumerTag, Delivery message) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
                System.out.println("message = " + new String(message.getBody()));
            }
        }, new CancelCallback() {
            @Override
            public void handle(String consumerTag) throws IOException {
                System.out.println("consumerTag = " + consumerTag);
            }
        });
        System.out.println("消费");
    }

}
