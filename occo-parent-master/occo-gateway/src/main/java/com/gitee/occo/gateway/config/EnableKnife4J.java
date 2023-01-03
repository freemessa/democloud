package com.gitee.occo.gateway.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Knife4jConfiguration.class})
@interface EnabledKnife4j {

}

