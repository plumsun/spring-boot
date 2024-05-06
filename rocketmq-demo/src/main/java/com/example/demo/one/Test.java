package com.example.demo.one;

import com.example.demo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

/**
 * @description:
 * @date: 2022/5/12 15:18
 * @author: LiHaoHan
 * @program: com.demo.one
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMQUtil.getChannel();
        channel.queueDeclare();
    }
}
