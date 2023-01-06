package com.example.flowable.config;

import lombok.extern.slf4j.Slf4j;
import org.flowable.ui.common.rest.idm.remote.RemoteAccountResource;
import org.flowable.ui.modeler.rest.app.EditorGroupsResource;
import org.flowable.ui.modeler.rest.app.EditorUsersResource;
import org.flowable.ui.modeler.rest.app.StencilSetResource;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
@ComponentScan(basePackages = {
        "org.flowable.ui.idm.rest.app",
        "org.flowable.ui.common.rest.exception",
        "org.flowable.ui.modeler.rest.app",
        "org.flowable.ui.common.rest"
}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value =
                RemoteAccountResource.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value =
                EditorUsersResource.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value =
                EditorGroupsResource.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value =
                StencilSetResource.class),
}
)
@EnableAsync
@Slf4j
public class AppDispatcherServletConfiguration implements WebMvcRegistrations {

    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        requestMappingHandlerMapping.setRemoveSemicolonContent(false);
        Object[] interceptors = {localeChangeInterceptor()};
        requestMappingHandlerMapping.setInterceptors(interceptors);
        return requestMappingHandlerMapping;
    }
}
