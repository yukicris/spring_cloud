package com.yukicris.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {
    // 自定义负载均衡策略
    // 放在这一级以免被启动类所扫描到,扫描到的话会被RibbonClient共享,造成其他客户端配置问题
    // 启动类扫描机制,从启动类所在包开始,扫描当前包以及子级包下的所有文件

    @Bean
    public IRule yukicrisRule() {
        return new yukicrisRandom();
    }
}
