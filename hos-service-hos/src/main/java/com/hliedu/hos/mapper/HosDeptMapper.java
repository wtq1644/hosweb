package com.hliedu.hos.mapper;

import com.hliedu.hos.domain.HosDept;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface HosDeptMapper {
    int deleteByPrimaryKey(Integer deptId);

    int insert(HosDept record);

    int insertSelective(HosDept record);

    int count(Map<String, Object> parameters);

    List<HosDept> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    HosDept getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<HosDept> list);

    HosDept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(HosDept record);

    int updateByPrimaryKey(HosDept record);

    List<HosDept> queryDeptByHosIdModel(Integer hosId);
}