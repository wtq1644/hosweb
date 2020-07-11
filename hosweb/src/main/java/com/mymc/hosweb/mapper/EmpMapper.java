package com.mymc.hosweb.mapper;

import com.mymc.hosweb.domain.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface EmpMapper {
    List<Emp> queryEmp();

    int addEmp(Emp emp);
}
