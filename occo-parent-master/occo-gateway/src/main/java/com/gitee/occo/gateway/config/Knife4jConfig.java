package com.gitee.occo.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4jConfig <br>
 *
 * @date: 2021/11/26 <br>
 * @author: llxiao <br>
 * @since: 1.0 <br>
 * @version: 1.0 <br>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "knife.swagger")
public class Knife4jConfig {

    private String version;
    private String basePackage;
    private String title;
    private String description;
    private String contactName;
    private Boolean enabled;
    private String termsOfServiceUrl = "https://xxx.com";
}

