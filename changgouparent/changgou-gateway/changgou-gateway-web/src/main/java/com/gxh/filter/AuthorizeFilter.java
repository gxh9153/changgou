package com.gxh.filter;

import com.gxh.utils.JwtUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器 :用于鉴权(获取令牌 解析 判断)
 * @Author:gxh
 * @Date: 2021/1/13 10:12
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //请求头中是否含有令牌
        boolean hasToken = true;

        //从请求头中获取token
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        //从请求参数中获取token
        if(StringUtils.isEmpty(token)){
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }
        //从cookie中获取token
        if (StringUtils.isEmpty(token)) {
            HttpCookie cookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if(cookie != null){
                token = cookie.getValue();
            }
        }

        //如果没有令牌则拦截
        //如果有令牌则校验
       /* try {
            JwtUtil.parseJWT(token);

        } catch (Exception e) {
            //解析不了
            //设置没有权限状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            response.setComplete();

            e.printStackTrace();
        }*/
       if(StringUtils.isEmpty(token)){
           //设置没有权限状态码 401
           response.setStatusCode(HttpStatus.UNAUTHORIZED);
           //响应空数据
           response.setComplete();
       }else{
           if (hasToken){
               if(!token.startsWith("bearer ") && !token.startsWith("Bearer ")){
                   token = "Bearer "+token;
               }
               //将令牌放入请求头中
               request.mutate().header(AUTHORIZE_TOKEN,token);
           }
       }
        //有效，放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
