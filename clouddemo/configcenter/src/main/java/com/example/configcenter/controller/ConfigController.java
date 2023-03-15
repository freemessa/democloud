package com.example.configcenter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RefreshScope //动态刷新
public class ConfigController {
    @Value("${config.info}")
    private String info;     //该属性值是从nacos配置中心拉取到的配置

    @Value("${us.name}")
    private String name;     //该属性值是从nacos配置中心拉取到的配置

    @Value("${logging.file.name}")
    private String logName;     //该属性值是从nacos配置中心拉取到的配置

    @GetMapping("/testConfig")
    public String testConfig(){
        log.info(name);
        log.info(logName);
        return info;
    }
}
