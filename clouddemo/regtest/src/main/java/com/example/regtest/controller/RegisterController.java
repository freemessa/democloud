package com.example.regtest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @GetMapping("/reg")
    public String register() throws Exception {
        Thread.sleep(5000); //Hystrix 降级 测试
        return "Welcome to register server.";
    }

    @GetMapping("/isExist")
    public String isExist(@RequestParam String userName) throws Exception {
        return userName + " is existed!";
    }
}
