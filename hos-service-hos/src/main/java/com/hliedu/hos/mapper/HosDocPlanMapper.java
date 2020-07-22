package com.hliedu.hos.mapper;

import com.hliedu.hos.domain.HosDocPlan;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface HosDocPlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(HosDocPlan record);

    int insertSelective(HosDocPlan record);

    int count(Map<String, Object> parameters);

    List<HosDocPlan> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    HosDocPlan getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<HosDocPlan> list);

    HosDocPlan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(HosDocPlan record);

    int updateByPrimaryKey(HosDocPlan record);
}