package com.example.configcenter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "topic.man")
@Slf4j
public class TopicManReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        log.info("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
