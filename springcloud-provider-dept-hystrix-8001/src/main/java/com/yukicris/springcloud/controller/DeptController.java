package com.yukicris.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.yukicris.springcloud.pojo.Dept;
import com.yukicris.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


   @GetMapping("/dept/get/{id}")
   @HystrixCommand(fallbackMethod = "hystrixGet")
   public Dept get(@PathVariable Long id) {
       Dept dept = deptService.queryById(id);
       if (dept==null){
           throw new RuntimeException("id->"+id +"不存在该用户");
       }
       return dept;
   }

    //备选方案
    public Dept hystrixGet(@PathVariable("id") Long id){
       // lombok和链式写法失效需要去idea 的plugin里面安装lombok插件

       return new Dept().setDeptno(id).setDname("id="+id+"没有对应信息").setDb_source("no this database in MYsql");

    }

}
