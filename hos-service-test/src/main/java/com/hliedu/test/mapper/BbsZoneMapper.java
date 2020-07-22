package com.hliedu.test.mapper;

import com.hliedu.test.domain.BbsZone;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


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