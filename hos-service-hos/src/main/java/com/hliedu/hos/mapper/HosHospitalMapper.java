package com.hliedu.hos.mapper;

import com.hliedu.hos.domain.HosHospital;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface HosHospitalMapper {
    int deleteByPrimaryKey(Integer hosId);

    int insert(HosHospital record);

    int insertSelective(HosHospital record);

    int count(Map<String, Object> parameters);

    List<HosHospital> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    HosHospital getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<HosHospital> list);

    HosHospital selectByPrimaryKey(Integer hosId);

    int updateByPrimaryKeySelective(HosHospital record);

    int updateByPrimaryKey(HosHospital record);

    int insertHosDeptRefBatch(List<Map<String,Object>> refs);
}