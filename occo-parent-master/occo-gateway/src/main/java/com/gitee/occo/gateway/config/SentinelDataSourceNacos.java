package com.gitee.occo.gateway.config;

import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.nacos.api.PropertyKeyConst;

/**
 * sentinel数据持久化到nacos，配置限流规则
 * 
 * @see https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95
 * @author kinbug
 */
@Component
public class SentinelDataSourceNacos implements InitFunc{

	
	@Value("${spring.cloud.sentinel.datasource.ds1.nacos.server-addr}")
	private String serverAddr;
	
	@Value("${spring.cloud.sentinel.datasource.ds1.nacos.groupId}")
	private String groupId;
	
	@Value("${spring.cloud.sentinel.datasource.ds1.nacos.dataId}")
	private String dataId;
	
	private boolean isDemoNamespace = true;
	
	@Value("${spring.cloud.sentinel.datasource.ds1.nacos.namespace}")
	private String NACOS_NAMESPACE_ID = "${namespace}";

	
	@Override
	public void init() throws Exception {
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