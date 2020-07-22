package com.hliedu.bs.mapper;

import com.hliedu.bs.domain.BsCity;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/13
*/
@Repository
public interface BsCityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(BsCity record);

    int insertSelective(BsCity record);

    int count(Map<String, Object> parameters);

    List<BsCity> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    BsCity getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<BsCity> list);

    BsCity selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(BsCity record);

    int updateByPrimaryKey(BsCity record);
}