package com.example.fegin.feign;

import com.example.fegin.feign.hystrix.RegisterHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="regtest",fallback = RegisterHystrix.class)
public interface RegisterFegin {
    @GetMapping("/reg")
    String reg();
}
