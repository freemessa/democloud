package com.example.nacosc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Knife4jConfiguration {
    // 是否开启swagger
    @Value("${swagger.enabled:true}")
    private boolean enableSwagger;

    @Bean(value = "defaultApi2")
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(enableSwagger)
                .select()
                // 扫描包路径，设置路径下所有@Api标记的类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.example.nacosc.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("regtest接口文档")   // 设置文档标题
                .description("regtest接口说明")    // 文档描述
                .version("1.0.0")   //版本号
                .build();
    }
}
