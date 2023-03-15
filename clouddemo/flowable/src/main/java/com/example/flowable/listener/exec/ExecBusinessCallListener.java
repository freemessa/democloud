package com.example.flowable.listener.exec;


import com.example.common.api.ResultCode;
import com.example.common.utils.ComException;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.flowable.engine.impl.el.FixedValue;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author: WuGaopeng
 * @date: 2022/7/29 11:01
 *  事件监听器， 必须要实现 ExecutionListener
 */
@Component(value = "executionBusinessCallListener")
public class ExecBusinessCallListener implements ExecutionListener {
    /**
     * 服务id
     */
    private FixedValue serviceId;
    /**
     * 访问的url路径
     */
    private FixedValue url;
    /**
     * 参数 多个的话用分号隔开 实例 userCode:00004737;status:1
     */
    private FixedValue params;


    @Override
    public void notify(DelegateExecution execution) {

        String processInstanceId = execution.getProcessInstanceId();

        String businessKey = execution.getProcessInstanceBusinessKey();
        String serviceIdStr = null, urlStr = null, paramsStr = null;
        if (serviceId != null) {
            serviceIdStr = serviceId.getExpressionText();
        }
        if (url != null) {
            urlStr = url.getExpressionText();
        }


        // 自定义业务代码
        // 校验调用其他接口执行
        // 可以进行涉及权限部门的操作

        /**
         * 执行监听器示例：
         *  如果表单只配置了请假开始和结束时间，需要计算出请假的天数day的值
         *  在流程绘图时配置start执行监听器，在网关判断时，设置${day}参数进行互斥判断 ,判断出在互斥网关后流程走哪一条线
        */
        Map<String, Object> variables = execution.getVariables();
        if(null != variables){
            // 需要计算的特殊的表单，做特殊的处理
            String startDay = "startDay";
            String endDay = "endDay";
            String startDayValue = (String) variables.get(startDay);
            String endDayValue = (String) variables.get(endDay);
            if(StringUtils.isEmpty(startDayValue) || StringUtils.isEmpty(endDayValue)){
                // 抛出异常
                ComException.throwEx(ResultCode.ARGUMENT_NOT_VALID);
            }
            // 计算相差天数
            long days = countDay(startDayValue, endDayValue);
            if(!execution.hasVariable("day")){
                execution.setVariable("day", days);
            }
        }

    }
    /**
     * 计算相隔的天数
    */
    public static long countDay(String begin,String end){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date beginDate , endDate;
        long day = 0;
        try {
            beginDate= format.parse(begin);
            endDate= format.parse(end);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(day > 0){
            return day + 1;
        }
        return day;
    }
}