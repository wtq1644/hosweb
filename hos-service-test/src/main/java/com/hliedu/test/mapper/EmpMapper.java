package com.hliedu.test.mapper;

import com.hliedu.test.domain.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * -
 * <p>
 * - 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */

@Repository
public interface EmpMapper {
    List<Emp> queryEmp();

    int addEmp(Emp emp);
}
