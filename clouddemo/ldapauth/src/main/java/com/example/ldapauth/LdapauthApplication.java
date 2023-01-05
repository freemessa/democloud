package com.example.ldapauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;

@SpringBootApplication
@EnableLdapRepositories
public class LdapauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LdapauthApplication.class, args);
    }

}
