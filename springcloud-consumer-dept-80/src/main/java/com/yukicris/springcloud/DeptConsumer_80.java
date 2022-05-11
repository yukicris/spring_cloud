package com.yukicris.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Ribben和Eureka整合以后,客户端可以直接调用,不用关心ip地址和端口号
@SpringBootApplication
@EnableEurekaClient  //'Eureka
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class,args);
    }
}
