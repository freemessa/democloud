package com.example.flowable.listener.exec;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component(value = "subdivisionExecutionListener")
@Slf4j
public class SubdivExecListener implements ExecutionListener {
    //@Autowired
    //ReqManageService reqManageService;

    private static SubdivExecListener subdivisionExecListener;

    @PostConstruct
    public void init(){
        subdivisionExecListener = this;
        //subdivisionExecListener.reqManageService = this.reqManageService;
    }

    @Override
    public void notify(DelegateExecution execution) {
        String processInstanceId = execution.getProcessInstanceId();
        String businessKey = execution.getProcessInstanceBusinessKey();
        String eventName = execution.getEventName();
        log.info("pid:{} bizKey:{} eventName:{}", processInstanceId, businessKey, eventName);
        Map<String, Object> map = execution.getVariables();
        map.put("processInstanceId", processInstanceId);
        switch (eventName) {
            case "start":
                //subdivisionExecutionListener.reqManageService.addReqCollectSubdivision(map);
                break;
            case "end":
                if (StringUtils.equals("删除",map.get("approved")+"")){
                    // todo删除
                    log.info("[{}] end event",processInstanceId);
                } else{
                    //subdivisionExecutionListener.reqManageService.addReqCollectSubdivision(map);
                }
                break;
            default:
                log.info("[{}] end event",processInstanceId);
                break;
        }
    }
}
