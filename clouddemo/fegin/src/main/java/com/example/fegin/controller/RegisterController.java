package com.example.fegin.controller;

import com.example.fegin.feign.RegisterFegin;
import com.example.fegin.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Api(tags="RegisterApi")
public class RegisterController {
    @Autowired
    private RegisterFegin registerFegin;

    @Autowired
    private RegisterService registerService;


    /**
     * register
     *
     * @return string
     **/
    @ApiOperation("注册")
    @GetMapping("/reg")
    public String register() {
        return registerFegin.reg();

    }

    /**
     * register
     *
     * @return string
     **/
    @ApiOperation("注册2")
    @GetMapping("/regb")
    public String registerB() {
        return registerService.reg();
    }

}
