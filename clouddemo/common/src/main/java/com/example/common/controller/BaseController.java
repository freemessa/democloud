package com.example.common.controller;

import com.example.common.api.ResultEntity;
import com.example.common.service.AutoLog;
import com.example.common.service.CommonConstant;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BaseController {
    @AutoLog(value = "sss",logType = CommonConstant.LOG_TYPE_3, operateType = 2)
    public ResultEntity selectTest() {
        return ResultEntity.success();
    }
}
