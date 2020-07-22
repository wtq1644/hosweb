package com.mymc.mybatis.mapper;

import com.mymc.mybatis.BaseMapper;
import com.mymc.mybatis.domain.BbsZone;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2020/07/13
*/
@Repository
public interface BbsZoneMapper {
    int deleteByPrimaryKey(Integer zoneId);

    int insert(BbsZone record);

    int insertSelective(BbsZone record);

    int count(Map<String, Object> parameters);

    List<BbsZone> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    BbsZone getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<BbsZone> list);

    BbsZone selectByPrimaryKey(Integer zoneId);

    int updateByPrimaryKeySelective(BbsZone record);

    int updateByPrimaryKey(BbsZone record);
}