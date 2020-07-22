package com.hliedu.test.service.impl;

import com.hliedu.test.domain.Emp;
import com.hliedu.test.mapper.EmpMapper;
import com.hliedu.test.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * -
 * <p>
 * - 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
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
        Emp emp1 = new Emp(1009 , "zs" , "程序员");
        empMapper.addEmp(emp1);

        //可能会出现异常的行
//        System.out.println(10/0);

        Emp emp2 = new Emp(1010 , "ls" , "项目经理");
        empMapper.addEmp(emp2);
        return false;
    }
}
