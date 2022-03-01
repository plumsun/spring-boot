package com.demo.one;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

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
        Connection connection = connectionFactory.newConnection();
        // connection.
        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        snowflake.nextId();
    }
}
