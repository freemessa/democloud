package com.example.nacosc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class NacoscApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =   SpringApplication.run(NacoscApplication.class, args);
        Environment env = applicationContext.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path","");
        System.out.println("\n--------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path+ "/index.html\n\t" +
                "swagger-ui: \thttp://localhost:" + port + path + "/swagger-ui.html\n\t" +
                "----------------------------------------------------------");
    }

}
