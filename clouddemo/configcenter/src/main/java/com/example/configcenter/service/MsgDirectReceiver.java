package com.example.configcenter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RabbitListener(queues = "MsgDirectQueue")//监听的队列名称 MsgDirectQueue
@Slf4j
public class MsgDirectReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        log.info("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }
}
