package com.mymc.hosweb.service;

import com.mymc.hosweb.domain.Emp;

import java.util.List;


public interface EmpService {
    List<Emp> queryEmp();
    boolean addEmp();
}
