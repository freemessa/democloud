package com.example.fegin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // 是否开启swagger
    @Value("${swagger.enabled}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 是否开启swagger
                .enable(enableSwagger)
                .select()
                // 扫描包路径，设置路径下所有@Api标记的类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.example.fegin.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")   // 设置文档标题
                .description("接口说明")    // 文档描述
                .version("1.0.0")   //版本号
                .build();
    }

}
