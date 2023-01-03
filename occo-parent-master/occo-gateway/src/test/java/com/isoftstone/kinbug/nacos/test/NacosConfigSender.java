package com.isoftstone.kinbug.nacos.test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * Nacos config sender for demo.
 *
 * @author Eric Zhao
 */
public class NacosConfigSender {

    public static void main(String[] args) throws Exception {
        final String remoteAddress = "47.110.252.190:8848";
        final String groupId = "DEFAULT_GROUP";
        final String dataId = "gateway-sentinel";
        final String rule = "[\n"
            + "  {\n"
            + "    \"resource\": \"TestResource\",\n"
            + "    \"controlBehavior\": 0,\n"
            + "    \"count\": 5.0,\n"
            + "    \"grade\": 1,\n"
            + "    \"limitApp\": \"default\",\n"
            + "    \"strategy\": 0\n"
            + "  }\n"
            + "]";
        ConfigService configService = NacosFactory.createConfigService(remoteAddress);
        System.out.println(configService.publishConfig(dataId, groupId, rule));
    }
}
