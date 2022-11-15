package com.example.fegin.service.impl;

import com.example.fegin.feign.RegisterFegin;
import com.example.fegin.service.RegisterService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterFegin registerFegin;

    //通过Feign调用接口模式，发生延迟异常，默认是不触发Hystrix降级服务，
    // 需要开启设置(D版本默认关闭ABC默认打开的)
    @Override
    @HystrixCommand(fallbackMethod = "regHystrix")
    public String reg() {
        return registerFegin.reg();
    }

    private String regHystrix() {
        return  "replace by hystrix!";
    }
}
