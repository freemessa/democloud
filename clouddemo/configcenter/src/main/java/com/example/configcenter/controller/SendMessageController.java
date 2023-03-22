package com.example.configcenter.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;  //使用RabbitTemplate,这提供了接收/发送等等方法

    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageData = "test message, hello!";
        Map<String, Object> map = getStringObjectMap(messageData);
        //将消息携带绑定键值：MsgDirectRouting 发送到交换机MsgDirectExchange
        rabbitTemplate.convertAndSend("MsgDirectExchange", "MsgDirectRouting", map);
        return "ok";
    }

    private Map<String, Object> getStringObjectMap(String messageData) {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        return map;
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        String messageData = "message: M A N ";
        Map<String, Object> manMap = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("topicExchange", "topic.man", manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        String messageData = "message: woman is all ";
        Map<String, Object> womanMap = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman", womanMap);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        String messageData = "message: testFanoutMessage ";
        Map<String, Object> map = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    @GetMapping("/TestMessageAck")
    public String TestMessageAck() {
        String messageData = "message: non-existent-exchange test message ";
        Map<String, Object> map = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("non-existent-exchange", "MsgDirectRouting", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck2")
    public String TestMessageAck2() {
        String messageData = "message: lonelyDirectExchange test message ";
        Map<String, Object> map = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";
    }

    @GetMapping("/TestMessageAck3")
    public String TestMessageAck3() {
        String messageData = "message: noExchange and noQueue test message ";
        Map<String, Object> map = getStringObjectMap(messageData);
        rabbitTemplate.convertAndSend("lonelyDirectExchange2", "TestDirectRouting", map);
        return "ok";
    }


}
