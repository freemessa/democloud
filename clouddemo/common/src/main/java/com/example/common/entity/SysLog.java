package com.example.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {
    String logContent;
    int logType;
    String method;
    int operateType;
    String requestParam;
    String userId;
    String userName;
    String orgId;
    String orgName;
    String realName;
    long costTime;
    Date createTime;
    String exceptionContent;
    String ip;
}
