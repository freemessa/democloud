package com.example.flowable.config;

import org.flowable.ui.common.security.SecurityConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration {
    @Configuration(proxyBeanMethods = false)//所以这个地方-1让该配置项在FlowableUiSecurityAutoConfiguration中对应配置项前加载，以跳过授权
    @Order(SecurityConstants.FORM_LOGIN_SECURITY_ORDER - 1)
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    //必须要将csrf设置为disable，不然后面发送POST请求时会报403错误
                    .csrf().disable()
                    //为了简单起见，简单粗暴方式直接放行modeler下面所有请求
                    .authorizeRequests().antMatchers("/modeler/**").permitAll();
        }
    }
}
