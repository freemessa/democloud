package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebfluxApplication {

    public static void main(String[] args) {
        //System.setProperty("csp.sentinel.app.type", "1");
        SpringApplication.run(WebfluxApplication.class, args);
    }

}
