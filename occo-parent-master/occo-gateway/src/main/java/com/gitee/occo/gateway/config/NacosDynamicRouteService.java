package com.gitee.occo.gateway.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import com.gitee.occo.gateway.service.impl.AbstractDynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import org.springframework.stereotype.Service;

/**
 * 动态路由配置
 * 以JSON的格式，但是要在gateway启动后在nacos发版配置，才能路由
 * 
 * 需要删除gateway_router.yml的配置方式二选一
 * 
 * @author kinbug
 */
@Service
@RefreshScope
@Slf4j
public class NacosDynamicRouteService extends AbstractDynamicRouteService {//ApplicationEventPublisherAware {

	@Value("${spring.cloud.nacos.config.server-addr}")
	private String serverAddr;

	@Value("${spring.cloud.nacos.config.group}")
	private String group;

	@Value("${spring.cloud.nacos.config.namespace}")
	private String namespace;

	private String dataId = "gateway-routes";

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;
	
	private ApplicationEventPublisher applicationEventPublisher;

	private static final List<String> ROUTE_LIST = new ArrayList<>();

	@PostConstruct
	public void dynamicRouteByNacosListener() {
		try {
			Properties properties = new Properties();
			properties.put("serverAddr", serverAddr);
			properties.put("namespace", namespace);

			ConfigService configService = NacosFactory.createConfigService(properties);
			// 程序首次启动, 并加载初始化路由配置
			String config = configService.getConfig(dataId, group, 5000);
			// 初次初始化
			initConfig(config);
			configService.addListener(dataId, group, new Listener() {
				@Override
				public void receiveConfigInfo(String configInfo) {
					clearRoute();
					try {
						System.err.println(configInfo);
						initConfig(configInfo);
						/*
						List<RouteDefinition> gatewayRouteDefinitions = JSONObject.parseArray(configInfo,
								RouteDefinition.class);
						for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
							addRoute(routeDefinition);
						}
						publish();
						 */
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public Executor getExecutor() {
					return null;
				}
			});
		} catch (NacosException e) {
			e.printStackTrace();
		}
	}
/*
	private void clearRoute() {
		for (String id : ROUTE_LIST) {
			this.routeDefinitionWriter.delete(Mono.just(id)).subscribe();
		}
		ROUTE_LIST.clear();
	}



	private void addRoute(RouteDefinition definition) {
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			ROUTE_LIST.add(definition.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void publish() {
		this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
 */
}
