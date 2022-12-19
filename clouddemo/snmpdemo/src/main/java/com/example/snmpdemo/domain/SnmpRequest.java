package com.example.snmpdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

import lombok.NoArgsConstructor;
import org.snmp4j.PDU;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SnmpRequest {
    private List<String> oidList;
    public PDU getSnmpRequest() {
        // 创建请求pdu,获取mib
        PDU request = new PDU();
        for (String oid : oidList) {
            // 调用的add方法绑定要查询的OID
            request.add(new VariableBinding(new OID(oid)));
            // 调用setType()方法来确定该pdu的类型--外部决定类型
            // request.setType(PDU.GETNEXT);
        }
        return request;
    }
}
