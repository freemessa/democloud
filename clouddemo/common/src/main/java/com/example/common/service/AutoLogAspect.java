package com.example.common.service;

import com.alibaba.fastjson.JSONObject;
import com.example.common.entity.LoginUser;
import com.example.common.entity.SysLog;
import com.example.common.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志，切面处理类
 *
 */
@Aspect
@Component
public class AutoLogAspect {
    private final static String SCAN_PACKAGE_NAME = "com.example";
    @Autowired
    private ISysLogService sysLogService;

    @Pointcut("@annotation(com.example.common.service.AutoLog)")
    public void logPointCut() {

    }

    //@AfterReturning
    //public Object afterReturing() {
    //
    //}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch sw = new StopWatch("");
        sw.start("logPointCut");
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);
        sw.stop();
        //long swTime = sw.getTotalTimeMillis();
        //sw.start("任务12");
        //sw.stop();
        System.out.println( sw.prettyPrint());

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if (syslog != null) {
            //注解上的描述,操作日志内容
            sysLog.setLogContent(syslog.value());
            sysLog.setLogType(syslog.logType());
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //设置操作类型
        if (sysLog.getLogType() == CommonConstant.LOG_TYPE_2) {
            sysLog.setOperateType(getOperateType(methodName, syslog.operateType()));
        }

        //请求的参数
        getRequestInfo(joinPoint, sysLog);
        //获取登录用户信息
        LoginUser sysUser = null;
        try {
            sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        } catch (Exception e) {

        }
        if (sysUser != null) {
            sysLog.setUserId(sysUser.getId());
            sysLog.setUserName(sysUser.getUserName());
            sysLog.setRealName(sysUser.getRealName());
            sysLog.setOrgId(sysUser.getNowOrgId());
            sysLog.setOrgName(sysUser.getNowOrgName());
        }
        //耗时
        sysLog.setCostTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);
    }

    /**
     * 获取操作类型
     */
    private int getOperateType(String methodName, int operateType) {
        if (operateType > 0) {
            return operateType;
        }
        if (methodName.startsWith("list")) {
            return CommonConstant.OPERATE_TYPE_LT2_1;
        }
        if (methodName.startsWith("add")) {
            return CommonConstant.OPERATE_TYPE_LT2_2;
        }
        if (methodName.startsWith("edit")) {
            return CommonConstant.OPERATE_TYPE_LT2_3;
        }
        if (methodName.startsWith("delete")) {
            return CommonConstant.OPERATE_TYPE_LT2_4;
        }
        if (methodName.startsWith("import")) {
            return CommonConstant.OPERATE_TYPE_LT2_5;
        }
        if (methodName.startsWith("export")) {
            return CommonConstant.OPERATE_TYPE_LT2_6;
        }
        return CommonConstant.OPERATE_TYPE_LT2_1;
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();

        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        String rootExceptionName = ex.getClass().getName();

        StringBuilder resultContent = new StringBuilder("异常类：" + rootExceptionName);

        int count = 0;
        int maxTrace = 3;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (stackTraceElement.getClassName().contains(SCAN_PACKAGE_NAME) && count < maxTrace) {
                resultContent.append("\n出现于").append(stackTraceElement.getClassName())
                        .append("类中的").append(stackTraceElement.getMethodName())
                        .append("方法中 位于该类文件的第").append(stackTraceElement.getLineNumber())
                        .append("行)");
                count++;
                if (count == maxTrace) {
                    break;
                }
            }
        }
        sysLog.setExceptionContent(resultContent.toString());

        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if (syslog != null) {
            //注解上的描述,操作日志内容
            sysLog.setLogContent(syslog.value() + "出现异常");
            sysLog.setLogType(CommonConstant.LOG_TYPE_4);
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");


        //设置操作类型
        sysLog.setOperateType(CommonConstant.OPERATE_TYPE_LT4_1);

        //请求的参数
        getRequestInfo(joinPoint, sysLog);
        try {
            //获取登录用户信息
            LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            if (sysUser != null) {
                sysLog.setUserId(sysUser.getId());
                sysLog.setUserName(sysUser.getUserName());
                sysLog.setRealName(sysUser.getRealName());
                sysLog.setOrgId(sysUser.getNowOrgId());
                sysLog.setOrgName(sysUser.getNowOrgName());

            }
        } catch (Exception e) {
        }
        //保存系统日志
        sysLogService.save(sysLog);
    }

    private void getRequestInfo(JoinPoint joinPoint, SysLog sysLog) {
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONObject.toJSONString(args);
            sysLog.setRequestParam(params);
        } catch (Exception e) {

        }
        try {
            //获取request
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(SpringContextUtils.getIpAddr(request));
        } catch (Exception e) {

        }
    }
}

