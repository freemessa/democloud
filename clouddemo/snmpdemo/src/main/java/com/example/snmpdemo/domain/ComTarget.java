package com.example.snmpdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.snmp4j.CommunityTarget;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;

import java.util.Locale;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComTarget {
    //设备IP地址
    private String ipAddress;
    //snmp访问团体
    private String community;
    //snmp版本
    private int version;
    //失败后尝试次数
    private int retries;
    //超时时常（秒）
    private int timeout;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //在输出的Json数据中隐藏密码，只能输入不输出
    //private String password;

    //snmp 端口
    //private int port = 161;
    //snmp 协议
    //private String protocol = "udp";

    public CommunityTarget getTarget() {
        // 设定CommunityTarget
        CommunityTarget comTarget = new CommunityTarget();
        // 定义本机的地址
        String address = String.format(Locale.ROOT, "udp:%s/161", ipAddress);
        Address deviceAdd = GenericAddress.parse(address);
        // 设定主机的地址
        comTarget.setAddress(deviceAdd);
        // 设置snmp共同体
        comTarget.setCommunity(new OctetString(this.community));
        // 设置超时重试次数
        comTarget.setRetries(this.retries);
        // 设置超时的时间
        comTarget.setTimeout(this.timeout);
        // 设置使用的snmp版本
        comTarget.setVersion(this.version);
        return  comTarget;
    }
}
