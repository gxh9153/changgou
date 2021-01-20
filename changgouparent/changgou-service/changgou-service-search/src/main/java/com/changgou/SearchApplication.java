package com.changgou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Author:gxh
 * @Date: 2021/1/415:20
 */
//排斥禁用数据库加载
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableElasticsearchRepositories(basePackages = "com.changgou.search.dao")
@EnableFeignClients(basePackages = "com.changgou.goods.feign")
public class SearchApplication {
    public static void main(String[] args) {

        /**
         * springboot整合ElasticSearch在项目启动前设置一下属性，防止报错
         * 解决netty冲突后初始化client时还会抛异常
         */
        System.setProperty("es.set.netty.runtime.available.processors","false");
        SpringApplication.run(SearchApplication.class,args);
    }
}
