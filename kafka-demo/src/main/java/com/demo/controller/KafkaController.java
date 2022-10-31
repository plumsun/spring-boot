package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.RespectBinding;

/**
 * @program: KafkaController
 * @Date: 2022/10/24
 * @Author: LiHaoHan
 * @Description:
 */
@Controller
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;

    @Value("${spring.kafka.consumer.group-id}")
    String topic;

    @GetMapping("producer")
    public void proTest(@RequestParam String value){
        System.out.println("value = " + value);
        kafkaTemplate.send(topic,value).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败："+ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });


    }
}
