package com.example.feginnacos.controller;

import com.example.feginnacos.domain.TaskRepresentation;
import com.example.feginnacos.fegin.FlowableFegin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flowable")
@Api(tags="FlowableApi")
public class FlowableController {
    @Autowired
    private FlowableFegin flowableFegin;

    /**
     * register
     *
     * @return string
     **/
    @ApiOperation("启动流程")
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public void process() {
        flowableFegin.process();
    }

    @ApiOperation("查询流程")
    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        return flowableFegin.getTasks(assignee);
    }
}
