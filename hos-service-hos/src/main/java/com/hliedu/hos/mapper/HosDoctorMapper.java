package com.hliedu.hos.mapper;

import com.hliedu.hos.domain.HosDoctor;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface HosDoctorMapper {
    int deleteByPrimaryKey(Integer docId);

    int insert(HosDoctor record);

    int insertSelective(HosDoctor record);

    int count(Map<String, Object> parameters);

    List<HosDoctor> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    HosDoctor getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<HosDoctor> list);

    HosDoctor selectByPrimaryKey(Integer docId);

    int updateByPrimaryKeySelective(HosDoctor record);

    int updateByPrimaryKey(HosDoctor record);

    //根据排期、部门编号查询医师信息
    List<Map<String,Object>> queryDoctorByDeptAndPlan(Map<String, Object> map);
}