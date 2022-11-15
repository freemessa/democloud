package com.example.fegin.controller;

import com.example.fegin.feign.RegisterFegin;
import com.example.fegin.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    private RegisterFegin registerFegin;

    @Autowired
    private RegisterService registerService;



    @GetMapping("/reg")
    public String register() {
        return registerFegin.reg();

    }

    @GetMapping("/regb")
    public String registerB() {
        return registerService.reg();
    }

}
