package com.example.webflux.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class AuthorizeFilter { //implements GlobalFilter, Ordered
    /*
    UserRedisCollection userRedisCollection;
    private boolean checkWhiteList(String uri) {
        boolean access = false;
        if (uri.contains("/login")|| uri.contains("/v2/api-docs") {
            access = true;
            if (uri.contains("logout")) {
                access = false;
            }
        }
        if (uri.contains("/open-api")) {
            access = true;
        }
        return access;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String uri = request.getURI().getPath();
        //...
        return


    }

     */
}
