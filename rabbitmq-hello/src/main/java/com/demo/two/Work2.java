package com.demo.two;

import com.rabbitmq.client.Channel;
import utils.RabbitMQUtil;

/**
 * @description:
 * @date: 2022/3/2 16:53
 * @author: LiHaoHan
 * @program: com.demo.two
 */
public class Work1 {

    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMQUtil.getChannel();

    }
}
