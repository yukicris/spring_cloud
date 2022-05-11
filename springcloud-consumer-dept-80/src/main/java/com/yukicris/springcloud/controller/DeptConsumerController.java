package com.yukicris.springcloud.controller;

import com.yukicris.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {
    // 理解消费者 ,不应该有service层

    //这部分就是调用别人写的接口



    //RestTemplate...这个模板,直接调用,需要注册到spring中
    // (url,实体:Map,Class<T> responseType)
    @Autowired
    private RestTemplate restTemplate;//提供多种便捷访问远程http服务的方法,简单的Restful服务模板

    //使用ribbon时,这个地址应该是个变量,通过服务名来访问
    //private static final String REST_URL_PREFIX = "http://localhost:8001/";
    private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";


    // 从地址add
    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return restTemplate.patchForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }


    // 从地址get
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }

    // 从地址get
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }


    ////测试
    //http://localhost/consumer/dept/list     只有80端口可以省
}
