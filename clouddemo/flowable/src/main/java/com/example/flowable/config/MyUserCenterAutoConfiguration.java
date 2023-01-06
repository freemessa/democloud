package com.example.flowable.config;

import com.example.flowable.service.MyIdentityServiceImpl;
import org.flowable.idm.api.IdmIdentityService;
import org.flowable.idm.spring.SpringIdmEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.flowable.spring.boot.FlowableSecurityAutoConfiguration;
import org.flowable.spring.boot.ProcessEngineServicesAutoConfiguration;
import org.flowable.spring.boot.idm.IdmEngineServicesAutoConfiguration;
import org.flowable.spring.security.FlowableAuthenticationProvider;
import org.flowable.ui.common.properties.FlowableCommonAppProperties;
import org.flowable.ui.common.security.CustomPersistentRememberMeServices;
import org.flowable.ui.common.security.FlowableUiSecurityAutoConfiguration;
import org.flowable.ui.common.security.PersistentTokenService;
import org.flowable.ui.idm.conf.IdmSecurityConfiguration;
import org.flowable.ui.idm.security.UserDetailsService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.RememberMeServices;

@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore({
        FlowableUiSecurityAutoConfiguration.class,
        FlowableSecurityAutoConfiguration.class,
        IdmEngineServicesAutoConfiguration.class,
        ProcessEngineServicesAutoConfiguration.class,
        IdmSecurityConfiguration.class
})
@EnableConfigurationProperties({
        MyUserCenterProperties.class
})
public class MyUserCenterAutoConfiguration implements ApplicationContextAware {
    protected final MyUserCenterProperties properties;

    ApplicationContext applicationContext;

    public MyUserCenterAutoConfiguration(MyUserCenterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public EngineConfigurationConfigurer<SpringIdmEngineConfiguration> myIdmEngineConfigurer() {
        return idmEngineConfiguration -> idmEngineConfiguration
                .setIdmIdentityService(new MyIdentityServiceImpl(applicationContext, properties, idmEngineConfiguration));
    }

    @Bean
    public FlowableAuthenticationProvider flowableAuthenticationProvider(IdmIdentityService idmIdentitySerivce, @Qualifier("myUserDetailsServiceImpl") UserDetailsService userDetailsService) {
        return new FlowableAuthenticationProvider(idmIdentitySerivce, userDetailsService);
    }

    @Bean
    @ConditionalOnMissingBean
    public RememberMeServices flowableUiRememberMeService(FlowableCommonAppProperties properties, @Qualifier("myUserDetailsServiceImpl") UserDetailsService userDetailsService, PersistentTokenService persistentTokenService) {
        return new CustomPersistentRememberMeServices(properties, userDetailsService, persistentTokenService);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

