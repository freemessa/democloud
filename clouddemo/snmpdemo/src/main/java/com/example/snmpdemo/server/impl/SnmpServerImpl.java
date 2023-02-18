package com.example.snmpdemo.server.impl;

import com.example.snmpdemo.domain.ComTarget;
import com.example.snmpdemo.domain.SnmpRequest;
import com.example.snmpdemo.server.SnmpServer;

import lombok.extern.slf4j.Slf4j;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SnmpServerImpl implements SnmpServer {

    //基础查询 单个IP///多ip //单个oid查询/多oid查询
    //范围扫描
    @Override
    public PDU snmpSpider(ComTarget target, SnmpRequest snmpRequest) {
        try {
            CommunityTarget myTarget = target.getTarget();
            PDU request = snmpRequest.getSnmpRequest();
            // 调用setType()方法来确定该pdu的类型
            request.setType(PDU.GETNEXT);

            // 设定采取的协议 // 设定传输协议为UDP
            TransportMapping transport = new DefaultUdpTransportMapping();
            // 调用TransportMapping中的listen()方法，启动监听进程，接收消息，由于该监听进程是守护进程，最后应调用close()方法来释放该进程
            transport.listen();
            // 创建SNMP对象，用于发送请求PDU
            Snmp protocol = new Snmp(transport);

            // 调用 send(PDU pdu,Target target)发送pdu，返回一个ResponseEvent对象
            ResponseEvent responseEvent = protocol.send(request, myTarget);
            // 通过ResponseEvent对象来获得SNMP请求的应答pdu，方法：public PDU getResponse()
            PDU response = responseEvent.getResponse();
            // 输出
            if (response != null) {
                // 调用close()方法释放该进程
                transport.close();
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void snmpScan() {
        // 设备范围集合
        List<ComTarget> targetList = new ArrayList<>();

        ComTarget comTarget = new ComTarget();
        comTarget.setCommunity("public");
        comTarget.setVersion(SnmpConstants.version2c);
        comTarget.setIpAddress("192.168.137.156");
        comTarget.setRetries(2);
        comTarget.setTimeout(3*60);
        targetList.add(comTarget);

        ComTarget comTarget2 = new ComTarget();
        comTarget2.setCommunity("public");
        comTarget2.setVersion(SnmpConstants.version2c);
        comTarget2.setIpAddress("192.168.137.7");
        comTarget2.setRetries(2);
        comTarget2.setTimeout(3*60);
        targetList.add(comTarget2);

        ComTarget comTarget3 = new ComTarget();
        comTarget3.setCommunity("public");
        comTarget3.setVersion(SnmpConstants.version2c);
        comTarget3.setIpAddress("192.168.137.9");
        comTarget3.setRetries(2);
        comTarget3.setTimeout(3*60);
        targetList.add(comTarget3);

        // 请求内容
        SnmpRequest snmpRequest = new SnmpRequest();
        List<String> oidList = new ArrayList<>();
        oidList.add(".1.3.6.1.2.1.1.1");
        oidList.add(".1.3.6.1.2.1.1.2");
        oidList.add(".1.3.6.1.2.1.1.3");
        oidList.add(".1.3.6.1.2.1.1.4");
        oidList.add(".1.3.6.1.2.1.1.5");
        oidList.add(".1.3.6.1.2.1.1.6");
        oidList.add(".1.3.6.1.2.1.1.7");
        oidList.add(".1.3.6.1.4.1.2021.4.5.0");
        snmpRequest.setOidList(oidList);
        // 遍历扫描设备ip
        for (ComTarget target:targetList) {
            PDU response = snmpSpider(target, snmpRequest);
            // 输出
            if (response != null) {
                log.info("{} response.size()={}",target.getIpAddress(),response.size());
                // 通过应答pdu获得mib信息（之前绑定的OID的值），方法：VaribleBinding get(int index)
                /// report data to server
                for (int i = 0; i < response.size(); i++) {
                    VariableBinding vb1 = response.get(i);
                    //System.out.println(vb1);
                    log.info("data {}",vb1);
                }
            } else {
                log.warn("no response:{}",target.getIpAddress());
            }

        }

    }
}
