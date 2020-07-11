package com.mymc.hosweb.controller;

import com.mymc.hosweb.entity.Emp;
import com.mymc.hosweb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/emp")
@Controller
public class EmpController {
    @Autowired
    private EmpService empService;

    @RequestMapping("query")
    public String query(ModelMap modelMap){
        List<Emp> emps = empService.queryEmp();
        modelMap.addAttribute("emps" , emps);
        return "empList";
    }
}