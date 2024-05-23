package com.example.demo.two;

import com.example.demo.enums.QueueNames;
import com.example.demo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

/**
 * @description:
 * @date: 2022/3/2 16:53
 * @author: LiHaoHan
 * @program: com.demo.two
 */
public class Work1 {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtil.getChannel();
        System.out.println("Work1.......");
        channel.basicConsume(QueueNames.WORK_QUEUE_NAME.getName(), true,
                (consumerTag, message) -> {
                    System.out.println("consumerTag = " + consumerTag);
                    System.out.println("message = " + new String(message.getBody()));
                },
                consumerTag -> System.out.println("consumerTag = " + consumerTag));
    }
}
