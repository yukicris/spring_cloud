package com.yukicris.springcloud.controller;

import com.yukicris.springcloud.pojo.Dept;
import com.yukicris.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;


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

}
