package com.example.flowable.listener.task;


import com.example.common.entity.MailInfo;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @date 2022/7/28 9:43
 * 任务监听器，必须要实现TaskListener
*/
@Component
@Slf4j
public class UserTaskListener implements TaskListener, ApplicationContextAware {

    private static  ApplicationContext applicationContext;

    @Override
    public void notify(DelegateTask delegateTask) {

        HistoryService historyService = applicationContext.getBean(HistoryService.class);
        //TechAbilityService techAbilityService = applicationContext.getBean(TechAbilityService.class);

        // 自定义代码，可以进行发消息  发邮件操作

        // 以下做一个监听实例，开发者根据自己的业务情景定制不同的监听业务处理。
        // 监听条件:这里的业务情景是 ： 如果是总监审批了，则发邮件给发起人（只对 发起该流程定义下的任务 有效）
        String zj_A = "400111111"; // 总监-A
        String hr_A = "100111111"; // HR-A
        if((delegateTask.getAssignee().equals(zj_A) || delegateTask.getAssignee().equals(hr_A))
            && (delegateTask.getName().equals("总监审批") || delegateTask.getName().equals("HR审批"))){
            // 流程实例id
            String processInstanceId = delegateTask.getProcessInstanceId();
            // 发起人工号
            String startUserId = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult()
                    .getStartUserId();
            // 根据工号查人员邮箱,此步骤省略
            String toMail = "a@b.c";
            List<MailInfo> mailInfos = new ArrayList<>();
            MailInfo mail = new MailInfo();
            mail.setToEmail(toMail);
            mail.setText("邮件被总监 审批");
            mail.setSubject("邮件被总监 审批");
            mailInfos.add(mail);
            // 发送邮件
            log.info(mailInfos.toString());

        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext1) throws BeansException {
        applicationContext = applicationContext1;
    }
}
