package com.hliedu.sys.mapper;

import com.hliedu.mybatis.mapper.BaseMapper;
import com.hliedu.sys.domain.SysDd;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface SysDdMapper extends BaseMapper {
    int deleteByPrimaryKey(Integer ddId);

    int insert(SysDd record);

    int insertSelective(SysDd record);

    int count(Map<String, Object> parameters);

    List<SysDd> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    SysDd getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<SysDd> list);

    SysDd selectByPrimaryKey(Integer ddId);

    int updateByPrimaryKeySelective(SysDd record);

    int updateByPrimaryKey(SysDd record);
}