package com.example.adauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableDiscoveryClient
public class AdauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdauthApplication.class, args);
    }

}
