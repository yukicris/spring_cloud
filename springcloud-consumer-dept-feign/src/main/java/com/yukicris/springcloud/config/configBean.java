package com.yukicris.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class configBean {
    //@Configuration -- 相当于spring中的applicationContext.xml

    //配置bean
    //配置负载均衡实现RestTemplate
    // Irule
    // AvailabilityFilteringRule  先过滤掉崩溃(跳闸/访问故障)的服务,对剩下的进行轮询
    // RoundRobinRule 轮询
    // Weighted 权重
    // RetryRule  先按照轮询获取服务,如果服务获取失败,则会在指定时间内进行重试
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }





}
