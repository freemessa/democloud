package com.example.webflux.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * sentinel数据持久化到nacos
 * @see //github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95
 * @author kinbug
 */
@Component
public class SentinelDataSourceNacos {

    // nacos server addr
    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String serverAddr;

    // nacos group
    private static final String groupId = "DEFAULT_GROUP";
    // nacos dataId
    private static final String dataId = "minio-server-demo-flow"; //gateway-sentinel

    // if change to true, should be config NACOS_NAMESPACE_ID
    private static boolean isDemoNamespace = true;//false;
    // fill your namespace id,if you want to use namespace. for example:
    // 0f5c7314-4983-4022-ad5a-347de1d1057d,you can get it on nacos's console
    private static final String NACOS_NAMESPACE_ID = "dev";//"${namespace}";

    @PostConstruct
    public void initRules() {
        if (isDemoNamespace) {
            loadMyNamespaceRules();
        } else {
            loadRules();
        }
    }

    private void loadRules() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(serverAddr, groupId,
                dataId, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private void loadMyNamespaceRules() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, NACOS_NAMESPACE_ID);

        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, groupId,
                dataId, source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
        }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }


}
