package com.hliedu.bs.mapper;

import com.hliedu.bs.domain.BsProvince;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface BsProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(BsProvince record);

    int insertSelective(BsProvince record);

    int count(Map<String, Object> parameters);

    List<BsProvince> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    BsProvince getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<BsProvince> list);

    BsProvince selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(BsProvince record);

    int updateByPrimaryKey(BsProvince record);
}