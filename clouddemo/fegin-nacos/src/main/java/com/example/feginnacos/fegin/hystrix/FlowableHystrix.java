package com.example.feginnacos.fegin.hystrix;

import com.example.feginnacos.domain.TaskRepresentation;
import com.example.feginnacos.fegin.FlowableFegin;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlowableHystrix implements FlowableFegin {
    @Override
    public void process() {

    }
    @Override
    public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
        return  new ArrayList<>();
    }
}
