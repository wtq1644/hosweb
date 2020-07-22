package com.hliedu.msg.mapper;

import com.hliedu.msg.domain.MsgConfig;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MsgConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(MsgConfig record);

    int insertSelective(MsgConfig record);

    int count(Map<String, Object> parameters);

    List<MsgConfig> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MsgConfig getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MsgConfig> list);

    MsgConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(MsgConfig record);

    int updateByPrimaryKeyWithBLOBs(MsgConfig record);

    int updateByPrimaryKey(MsgConfig record);
}