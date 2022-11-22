package com.example.fegin.feign.hystrix;

import com.example.fegin.feign.RegisterFegin;
import org.springframework.stereotype.Component;

@Component
public class RegisterHystrix implements RegisterFegin {
    @Override
    public String reg() {
        return "hystrix return news!";
    }
}
