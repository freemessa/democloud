package com.example.flowable.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUserCenterProperties {
    private String appId;
    private String userId;
    private String password;
}
