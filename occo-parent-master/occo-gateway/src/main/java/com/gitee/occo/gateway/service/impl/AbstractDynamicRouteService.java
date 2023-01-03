package com.gitee.occo.gateway.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.gitee.occo.gateway.service.DynamicRouteService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态路由配置抽象类<br>
 *
 * @date: 2020/12/23 <br>
 * @author: xiaolinlin <br>
 * @since: 1.0 <br>
 * @version: 1.0 <br>
 */
@Slf4j
public abstract class AbstractDynamicRouteService implements DynamicRouteService, ApplicationEventPublisherAware,
        DisposableBean {

    @Autowired
    protected RouteDefinitionWriter routeDefinitionWriter;

    protected ApplicationEventPublisher applicationEventPublisher;


    /**
     * 路由列表  TODO  分布式配置
     */
    protected static final List<String> ROUTE_LIST = new ArrayList<>();

    /**
     * 新增一个route定义
     *
     * @param routeDefinition :
     * @param manual： 是否手动配置
     * @return java.lang.String
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    @Override
    public String addRoute(RouteDefinition routeDefinition, boolean manual) {
        try {
            log.debug("新增route配置：{}", JSONObject.toJSONString(routeDefinition));
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            ROUTE_LIST.add(routeDefinition.getId());
            if (manual) {
                // 手动发布，每添加一次更新一次
                publish();
            }
        } catch (Exception e) {
            log.error("添加路由失败，当前路由信息：{}，错误详情：{}", JSONObject.toJSONString(routeDefinition), e);
        }
        return "success";
    }

    /**
     * 更新一个route定义
     *
     * @param routeDefinition :
     * @return java.lang.String
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    @Override
    public String updateRoute(RouteDefinition routeDefinition) {
        if (null == routeDefinition) {
            return "fail";
        }
        log.debug("更新route配置:{}", JSONObject.toJSONString(routeDefinition));
        String id = routeDefinition.getId();
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            this.routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            publish();
        } catch (Exception e) {
            log.error("更新路由信息失败，当前ID：{}，错误信息：{}", id, e);
            return "fail";
        }
        return "success";
    }

    /**
     * 通过ID删除一个route定义
     *
     * @param id :
     * @return java.lang.String
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    @Override
    public String deleteRoute(String id) {
        if (StringUtils.isEmpty(id)) {
            return "fail";
        }
        log.debug("触发了删除route配置，路由ID: {}", id);
        try {
            this.routeDefinitionWriter.delete(Mono.just(id))
                    .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
                    .onErrorResume(t -> t instanceof NotFoundException, t -> Mono.just(ResponseEntity.notFound().build()))
                    .subscribe();
            return "success";
        } catch (Exception e) {
            log.error("删除路由失败，当前路由ID：{}，异常原因：{}", id, e);
            return "fail";
        }
    }

    /**
     * 初始化路由配置
     *
     * @param config :
     * @return void
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    protected void initConfig(String config) {
        if (StringUtils.isEmpty(config)) {
            return;
        }
        List<RouteDefinition> gatewayRouteDefinitions = JSONObject
                .parseArray(config, RouteDefinition.class);
        for (RouteDefinition routeDefinition : gatewayRouteDefinitions) {
            addRoute(routeDefinition, false);
        }
        // 重新发布更新
        publish();
    }

    /**
     * 发布重新更新
     *
     * @return void
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    protected void publish() {
        log.info("当前网关路径节点：{}", JSONObject.toJSONString(ROUTE_LIST));
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this.routeDefinitionWriter));
    }

    /**
     * clearRoute
     *
     * @author xiaolinlin
     * @date 2020/12/7
     **/
    protected void clearRoute() {
        for (String id : ROUTE_LIST) {
            this.deleteRoute(id);
        }
        ROUTE_LIST.clear();
    }

    /**
     * Set the ApplicationEventPublisher that this object runs in.
     * <p>Invoked after population of normal bean properties but before an init
     * callback like InitializingBean's afterPropertiesSet or a custom init-method. Invoked before ApplicationContextAware's
     * setApplicationContext.
     *
     * @param applicationEventPublisher event publisher to be used by this object
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void destroy() throws Exception {
        ROUTE_LIST.clear();
    }
}

