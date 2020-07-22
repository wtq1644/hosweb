package com.hliedu.mybatis.mapper;

import com.hliedu.mybatis.domain.BsArea;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/20
*/
@Repository
public interface BsAreaMapper {
    int deleteByPrimaryKey(Integer areaId);

    int insert(BsArea record);

    int insertSelective(BsArea record);

    int count(Map<String, Object> parameters);

    List<BsArea> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    BsArea getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<BsArea> list);

    BsArea selectByPrimaryKey(Integer areaId);

    int updateByPrimaryKeySelective(BsArea record);

    int updateByPrimaryKey(BsArea record);
}