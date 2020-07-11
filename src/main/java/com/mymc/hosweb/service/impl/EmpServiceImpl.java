package com.mymc.hosweb.service.impl;

import com.mymc.hosweb.entity.Emp;
import com.mymc.hosweb.mapper.EmpMapper;
import com.mymc.hosweb.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Emp> queryEmp() {
        return empMapper.queryEmp();
    }

    @Transactional
    @Override
    public boolean addEmp() {
        Emp emp1 = new Emp(1006 , "zs" , "程序员");
        empMapper.addEmp(emp1);

//        //可能会出现异常的行
//        System.out.println(10/0);

        Emp emp2 = new Emp(1007 , "ls" , "项目经理");
        empMapper.addEmp(emp2);
        return false;
    }
}
