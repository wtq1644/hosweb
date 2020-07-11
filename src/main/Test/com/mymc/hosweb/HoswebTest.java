package com.mymc.hosweb;

import com.mymc.hosweb.service.EmpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HoswebTest {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        EmpService empService = (EmpService)context.getBean("empService");
        System.out.println(empService.addEmp());
    }

}