package com.changgou.orders;

import com.changgou.entity.FeignRequestInterceptor;
import com.changgou.entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author:gxh
 * @Date: 2021/1/18 13:30
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.changgou.orders.dao")
@EnableFeignClients(basePackages = "com.changgou.goods.feign")
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class,args);
    }

    /**
     * 将Feign拦截器注入到容器中
     * @return
     */
    @Bean
    public FeignRequestInterceptor get(){
        return new FeignRequestInterceptor();
    }
    @Bean
    public IdWorker getIdWord(){
        return new IdWorker();
    }
}
