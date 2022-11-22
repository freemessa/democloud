package com.example.regtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class RegtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegtestApplication.class, args);
    }

}
