package com.example.demo.two;

import com.example.demo.enums.QueueNames;
import com.example.demo.utils.RabbitMQUtil;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @description:
 * @date: 2022/3/2 17:48
 * @author: LiHaoHan
 * @program: com.demo.two
 */
public class Task {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtil.getChannel();
        System.out.println("生产者.......");
        channel.queueDeclare(QueueNames.WORK_QUEUE_NAME.getName(), false,false,false,null);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            channel.basicPublish("", QueueNames.WORK_QUEUE_NAME.getName(),null,s.getBytes(StandardCharsets.UTF_8));
        }
    }
}
