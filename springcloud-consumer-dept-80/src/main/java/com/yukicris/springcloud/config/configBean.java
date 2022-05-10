package com.yukicris.springcloud.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class configBean {
    //@Configuration -- 相当于spring中的applicationContext.xml

    //配置bean

    @Bean
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }
}
