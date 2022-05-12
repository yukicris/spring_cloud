package com.yukicris.springcloud.controller;

import com.yukicris.springcloud.pojo.Dept;
import com.yukicris.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptConsumerController {


    //把微服务的名字绑定到注解上面
    //且封装了RestTemplate



    @Autowired
    private DeptClientService deptClientService = null;

    // 从地址add
    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept) {
        return this.deptClientService.addDept(dept);
    }


    // 从地址get
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list() {
        return this.deptClientService.queryAll();
    }

    // 从地址get
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return this.deptClientService.queryById(id);
    }


    ////测试
    //http://localhost/consumer/dept/list     只有80端口可以省
}
