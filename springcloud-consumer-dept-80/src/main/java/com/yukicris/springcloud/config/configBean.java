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
    @Bean
    @LoadBalanced //Ribbon
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }



}
