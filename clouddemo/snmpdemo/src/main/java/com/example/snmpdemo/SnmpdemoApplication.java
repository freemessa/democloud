package com.example.snmpdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
//指定要扫描的mybatis映射类的路径
@MapperScan("com.example.snmpdemo.map")
public class SnmpdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnmpdemoApplication.class, args);
    }

}
