package com.example.nacosc.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacosserver")
public class NacosServerController {
    //回显服务
    @RequestMapping(value = "/{string}", method = RequestMethod.GET)
    @SentinelResource(value = "echo",fallback = "handlerFallback",blockHandler = "blockHandler"/*, exceptionsToIgnore = {IllegalArgumentException.class}*/)
    public String echo(@PathVariable String string) {
        return "echo: " + string;
    }

    //fallback
    public String handlerFallback(@PathVariable  Long id,Throwable e) {
        return "兜底异常handlerFallback,exception内容  "+e.getMessage();
    }

    //blockHandler
    public String blockHandler(@PathVariable  Long id, BlockException blockException) {
        return "blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage();
    }

}
