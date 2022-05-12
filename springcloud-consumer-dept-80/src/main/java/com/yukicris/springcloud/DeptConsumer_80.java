package com.yukicris.springcloud;

import com.yukicris.myrule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

// Ribben和Eureka整合以后,客户端可以直接调用,不用关心ip地址和端口号

@SpringBootApplication
@EnableEurekaClient  //'Eureka
// 按需加载,加载负载均衡服务以及配置,在微服务启动的时候就能加载自定义的Ribbon类
@RibbonClient(name = "SPRINGCLOUD-PROVIDER-DEPT",configuration = MyRule.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
