package com.example.configcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConfigcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigcenterApplication.class, args);
    }

}
