package com.hliedu.mm.mapper;

import com.hliedu.mm.domain.MmMerber;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MmMerberMapper {
    int deleteByPrimaryKey(Integer merberId);

    int insert(MmMerber record);

    int insertSelective(MmMerber record);

    int count(Map<String, Object> parameters);

    List<MmMerber> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MmMerber getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MmMerber> list);

    MmMerber selectByPrimaryKey(Integer merberId);

    int updateByPrimaryKeySelective(MmMerber record);

    int updateByPrimaryKey(MmMerber record);
}