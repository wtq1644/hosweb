package com.mymc.hosweb;

import com.mymc.hosweb.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class HoswebJunitTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void test() {
        EmpService empService = (EmpService)ctx.getBean("empService");
        System.out.println(empService.queryEmp());
    }
}