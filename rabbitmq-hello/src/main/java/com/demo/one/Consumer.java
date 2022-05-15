package com.demo.one;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @date: 2022/3/1 16:19
 * @author: LiHaoHan
 * @program: com.demo.one
 */
public class Producer {


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
        //创建队列
        /**
         * String queue 队列名
         * boolean durable 是否持久化
         * boolean exclusive 排他
         * boolean autoDelete 自动删除
         * Map<String, Object> arguments 队列参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 发送消息
        /**
         * String exchange 交换机
         * String routingKey 路由
         * BasicProperties props 其他参数信息
         * byte[] body  消息体
         */
        String message="hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes(StandardCharsets.UTF_8));
        System.out.println("消息发送成功");
    }

}
