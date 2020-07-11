package com.mymc.hosweb.controller;

import com.mymc.ee.redis.RedisCache;
import com.mymc.hosweb.domain.Emp;
import com.mymc.hosweb.service.EmpService;
import com.mymc.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/emp")
@Controller
public class EmpController {


    private final EmpService empService;

    @Autowired
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @Autowired
    private RedisCache redisManager;

    @RequestMapping("query1")
    public String query(ModelMap modelMap){

        redisManager.set("hahha", "123");
        //以后在工作中，多人开发项目时，提交之前，先更新
        System.out.println("123");
        List<Emp> emps = empService.queryEmp();
        modelMap.addAttribute("emps" , emps);

        return "empList";
    }
}
