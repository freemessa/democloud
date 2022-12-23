package com.example.feginnacos.fegin;

import com.example.feginnacos.domain.TaskRepresentation;
import com.example.feginnacos.fegin.hystrix.FlowableHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="flowable-service",fallback = FlowableHystrix.class)
public interface FlowableFegin {
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    void process();

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<TaskRepresentation> getTasks(@RequestParam String assignee);
}
