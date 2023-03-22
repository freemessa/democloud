package com.example.configcenter;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigcenterApplication.class, args);
    }

    //@Bean
    //public Queue MsgDirectQueueA() {
    //    return new Queue("MsgDirectQueueA",true,true,false);
    //}
}
