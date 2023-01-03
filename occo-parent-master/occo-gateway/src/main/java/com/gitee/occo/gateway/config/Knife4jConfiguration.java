package com.gitee.occo.gateway.config;

import com.gitee.occo.gateway.utils.CommonExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ComponentScan(basePackages = "com.isoftstone.rms.gateway")
@Slf4j
public class Knife4jConfiguration {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2(Knife4jConfig knife4jConfig) {
        // 自定义统一错误码返回 -需要自行修改，仅供参考
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        for (CommonExceptionCode value : CommonExceptionCode.values()) {
            ResponseMessage build = new ResponseMessageBuilder().code(value.getCode()).message(value.getMsg()).responseModel(
                    new ModelRef(value.getMsg())).build();
            responseMessageList.add(build);
        }

        // 自定义通用header 定义  -需要自行修改，仅供参考
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(new ParameterBuilder()
                .name("Auth-Token")
                .description("我是描述")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build());
        log.info("Knife4j 加载完成....");
        return new Docket(DocumentationType.SWAGGER_2)
                // 自定义错误码
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                // header参数定义
                .globalOperationParameters(parameters)
                .apiInfo(new ApiInfoBuilder()
                        .title(knife4jConfig.getTitle())
                        .description(knife4jConfig.getDescription())
                        .termsOfServiceUrl(knife4jConfig.getTermsOfServiceUrl())
                        .contact(new Contact(knife4jConfig.getContactName(), "", ""))
                        .version(knife4jConfig.getVersion())
                        .build())
                //分组名称
                // .groupName(knife4jConfig.getVersion())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(knife4jConfig.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }
}

