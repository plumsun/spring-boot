package com.demo.two;

import com.demo.enums.QueueNames;
import com.demo.utils.RabbitMQUtil;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;

/**
 * @description:
 * @date: 2022/3/2 16:53
 * @author: LiHaoHan
 * @program: com.demo.two
 */
public class Work2 {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtil.getChannel();
        System.out.println("Work2.......");
        channel.basicConsume(QueueNames.WORK_QUEUE_NAME.getName(), true, new DeliverCallback() {
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

    }
}
