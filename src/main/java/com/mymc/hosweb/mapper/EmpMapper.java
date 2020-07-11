package com.mymc.hosweb.mapper;

import java.util.List;
import com.mymc.hosweb.entity.Emp;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpMapper {

    List<Emp> queryEmp();

    int addEmp(Emp emp);
}
