package com.example.flowable.listener.exec;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *  执行监听器， 必须要实现 ExecutionListener
 *  流程或节点可配置相应监听类
 *  监听类型：start，end，take
 *
 *  @author zheng shaobo
 *  * @since 2023/1/13 11:01
 */
@Component(value = "baseExecutionListener")
@Slf4j
public class BaseExecListener implements ExecutionListener {
    private FixedValue endFieldName;

    @Override
    public void notify(DelegateExecution execution) {
        String processInstanceId = execution.getProcessInstanceId();
        String businessKey = execution.getProcessInstanceBusinessKey();
        String eventName = execution.getEventName();
        log.info("pid:{} bizKey:{} eventName:{}", processInstanceId, businessKey, eventName);
        Map<String, Object> map = execution.getVariables();
        log.info(map.toString());
        switch (eventName) {
            case "start":
                startEvent(processInstanceId);
                break;
            case "end":
                endEvent(processInstanceId);
                break;
            default:
                takeEvent(processInstanceId);
                break;
        }
    }

    /**
     * start开始事件
     */
    private void startEvent(String processInstanceId) {
        log.info("[{}] start event",processInstanceId);
    }

    /**
     * end结束事件
     */
    private void endEvent (String processInstanceId) {
        log.info("[{}] end event",processInstanceId);
    }

    /**
     * take 启用事件
     */
    private void takeEvent (String processInstanceId) {
        log.info("[{}] task event",processInstanceId);

    }
}
