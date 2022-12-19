package com.example.snmpdemo.controller;

import com.example.snmpdemo.domain.ComTarget;
import com.example.snmpdemo.server.ComTargetService;

import com.example.snmpdemo.utils.Result;
import com.example.snmpdemo.utils.TargetUtil;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
@RequestMapping("/comTarget")
public class ComTargetController {
    // 注入mapper类
    @Resource
    private ComTargetService comTargetService;

    @RequestMapping(value="{id}", method= RequestMethod.GET, produces="application/json")
    public ComTarget getComTarget(@PathVariable long id) {

        ComTarget comTarget = this.comTargetService.getComTargetById(id);

        return comTarget;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<Result> AddComTarget(
            @Valid @RequestBody ComTarget comTarget,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            //rfc4918 - 11.2. 422: Unprocessable Entity
            //res.setStatus(422);
            //res.setMessage("输入错误");
            //res.putData("fieldErrors", bindingResult.getFieldErrors());

            Result res1 = TargetUtil.getValidateError(bindingResult);
            return new ResponseEntity<Result>(res1, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        this.comTargetService.addComTarget(comTarget);

        Result res = new Result(200, "ok");
        return ResponseEntity.ok(res);
    }
}
