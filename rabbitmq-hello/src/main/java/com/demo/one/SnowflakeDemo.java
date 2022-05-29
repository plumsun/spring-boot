package com.demo.one;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @description: 雪花算法
 * @date: 2022/3/2 10:20
 * @author: LiHaoHan
 * @program: com.demo.one
 */
public class SnowflakeDemo {
    public static void main(String[] args) {


        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

            }
        });


        Snowflake snowflake = IdUtil.getSnowflake(1,1);
        long l = snowflake.nextId();
        String s = snowflake.nextIdStr();
        System.out.println("s = " + s);
        System.out.println("l = " + l);
    }
}
