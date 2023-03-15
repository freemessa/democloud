package com.example.flowable.listener.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;


@Component(value = "subdivisionTaskListener")
@Slf4j
public class SubdivTaskListener implements TaskListener {
    @Autowired
    //ReqManageService reqManageService;

    private static SubdivTaskListener subdivTaskListener;

    @PostConstruct
    public void init() {
        subdivTaskListener = this;
        //subdivisionTaskListener.reqManageService = this.reqManageService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {
        String processInstanceId = delegateTask.getProcessInstanceId();
        //String eventName = delegateTask.getEventName();
        Map<String, Object> map = delegateTask.getVariables();
        map.put("processInstanceId", processInstanceId);
        String approved = (String) (map.get("approved"));
        if (StringUtils.equals("提交", approved)) {
            map.put("status", 2);
        } else if (StringUtils.equals("删除", approved)) {
            // todo删除
            log.info("[{}] end event", processInstanceId);
        } else if (StringUtils.equals("驳回", approved)) {
            map.put("status", 1);
        } else {
            // 审批通过
            map.put("status", 3);
        }
        //subdivisionTaskListener.reqManageService.addReqCollectSubdivision(map);
    }
}
