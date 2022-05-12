package com.yukicris.springcloud.controller;

import com.yukicris.springcloud.pojo.Dept;
import com.yukicris.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //获取一些配置信息,得到具体的微服务
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/dept/add")
    public boolean addDept(Dept dept) {
        return  deptService.addDept(dept);
    }


    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id) {
        return  deptService.queryById(id);
    }


    @RequestMapping("/dept/list")
    public List<Dept> queryAll() {
        return  deptService.queryAll();
    }

    //注册进来的微服务,获取一些消息 ,访问http://localhost:8001/dept/discory
    @GetMapping("/dept/discory")
    public Object discory() {
        // 获得微服务列表清单 (用于企业联合合作)
        List<String> services = client.getServices();
        System.out.println("discory"+services);

        //得到一个具体的微服务信息,通过具体的微服务id Applicationname
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
        for (ServiceInstance si:instances) {
            System.out.println(si.getHost()+"\t"+
                                si.getPort()+"\t"+
                                si.getUri()+"\t"+
                                si.getServiceId());
        }
        return this.client;
    }

}
