package com.example.common.service;

import com.example.common.entity.SysLog;
import org.springframework.stereotype.Service;

@Service
public interface ISysLogService {
    void save(SysLog sysLog);
}
