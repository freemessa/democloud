package com.example.flowable.controller;

import com.example.flowable.domain.User;
import com.example.flowable.mapper.UserMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    // 注入mapper类
    @Resource
    private UserMapper comTargetService;

    @RequestMapping(value="/getuserbyid/{id}", method= RequestMethod.GET, produces="application/json")
    public User getComTarget(@PathVariable String id) {

        User comTarget = this.comTargetService.getUserInfoByUserId(id);

        return comTarget;
    }

    @RequestMapping(value="/echo/{id}", method= RequestMethod.GET, produces="application/json")
    public String echo(@PathVariable String id) {
        return "echo: "+id;
    }
}
