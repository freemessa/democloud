package com.example.common.service.impl;

import com.example.common.entity.SysLog;
import com.example.common.service.ISysLogService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SysLogServiceImpl implements ISysLogService {
    @Override
    public void save(SysLog sysLog) {
        log.info(sysLog.toString());
    }
}
