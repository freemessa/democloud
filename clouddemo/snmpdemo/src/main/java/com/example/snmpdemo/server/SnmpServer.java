package com.example.snmpdemo.server;


import com.example.snmpdemo.domain.ComTarget;
import com.example.snmpdemo.domain.SnmpRequest;

import org.snmp4j.PDU;

import org.springframework.stereotype.Service;

/**
 * SNMP服务接口
 *
 * @author : demo
 * @version : 1.0
 * @since  : 2022/11/2 10:04
 */
@Service
public interface SnmpServer {
    /**
     * 获取SNMP目标oid信息
     *
     * @param target 待获取目标信息
     * @param snmpRequest 请求内容
     * @return SNMP响应Pdu
     */
    PDU snmpSpider(ComTarget target, SnmpRequest snmpRequest);

    /**
     * 扫描具体范围内SNMP目标oid信息
     */
    void snmpScan();

}
