package com.hliedu.test.service;

import com.hliedu.test.domain.Emp;

import java.util.List;

/**
 * -
 * <p>
 * - 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface EmpService {
    List<Emp> queryEmp();
    boolean addEmp();
}
