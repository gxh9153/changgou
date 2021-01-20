package com.changgou.oauth.interceptor;

import com.changgou.oauth.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:gxh
 * @Date: 2021/1/19 11:38
 */
@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {

    /**
     * 在feign调用之前执行拦截
     * @param requestTemplate
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        /**
         * 从数据库查询查询用户信息
         * 1：没有令牌，Feign调用之前生成令牌（admin）
         * 2：令牌需要携带过去
         * 3：令牌需要存放到Header文件中
         * 4：请求-》Feign调用-》调用拦截器RequestInterceptor->Feign调用之前
         */
        //生成管理员令牌
        String adminToken = AdminToken.getAdminToken();
        //将令牌放入头文件中
        requestTemplate.header("Authorization","Bearer "+adminToken);
    }
}
