package com.hliedu.bs.mapper;

import com.hliedu.bs.domain.BsArea;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
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