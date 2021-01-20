package com.changgou.entity;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * @Author:gxh
 * @Date: 2021/1/19 11:38
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    /**
     * 在feign调用之前执行拦截
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        /**
         * 获取用户令牌
         * 将令牌封装到头文件中
         */
        //记录了当前用户请求的所有数据，包含请求头和请求参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        Enumeration<String> headerNames = requestAttributes.getRequest().getHeaderNames();

        while(headerNames.hasMoreElements()){
            //请求头中的key
            String key = headerNames.nextElement();
            //获取请求头的值
            String headerValue = requestAttributes.getRequest().getHeader(key);

            //将请求头封装到头中，使用Feign调用时，会传递给下一个微服务
            requestTemplate.header(key,headerValue);
        }


    }
}
