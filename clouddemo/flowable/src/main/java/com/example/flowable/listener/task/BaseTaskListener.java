package com.example.flowable.listener.task;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 任务监听器，必须要实现TaskListener
 * 节点可配置相应监听类
 * 监听类型：assignment-分配办理人,create-任务已创建，complete-任务已完成，delete-任务即将被删除
 *
 * @author zheng shaobo
 * @since  2023/1/13 9:43
 */
@Component(value = "baseTaskListener")
@Slf4j
public class BaseTaskListener implements TaskListener, ApplicationContextAware {
    private static  ApplicationContext applicationContext;

    @Override
    public void notify(DelegateTask delegateTask) {
        HistoryService historyService = applicationContext.getBean(HistoryService.class);
        // 流程实例id
        String processInstanceId = delegateTask.getProcessInstanceId();
        String taskName = delegateTask.getName();
        String eventName = delegateTask.getEventName();
        Map<String, Object> map = delegateTask.getVariables();
        // 发起人工号
        String startUserId = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult()
                .getStartUserId();

        log.info("pid:{} taskName:{} eventName:{} uid:{}",processInstanceId, taskName, eventName, startUserId);
        log.info(map.toString());

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext = applicationContext1;
    }

}
