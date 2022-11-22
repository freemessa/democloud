package com.example.snmpdemo.task;

import com.example.snmpdemo.server.SnmpServer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * SNMP服务启动器
 *
 * @author : demo
 * @version : 1.0
 * @since : 2022/11/3 10:15
 */
@Component
@Slf4j
public class SnmpTask {
    @Resource
    private SnmpServer snmpServer;

    //每10分钟执行一次
    //@Scheduled(cron="30 0/2 * * * ?")
    @Scheduled(cron="${schedules.runSnmpScan}")
    public void runSnmpTask() {
        log.info("runSnmpTask start");
        snmpServer.snmpScan();
        log.info("runSnmpTask end");
    }
}
