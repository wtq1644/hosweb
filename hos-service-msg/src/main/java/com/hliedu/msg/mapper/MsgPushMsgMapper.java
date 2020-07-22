package com.hliedu.msg.mapper;

import com.hliedu.msg.domain.MsgPushMsg;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MsgPushMsgMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(MsgPushMsg record);

    int insertSelective(MsgPushMsg record);

    int count(Map<String, Object> parameters);

    List<MsgPushMsg> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MsgPushMsg getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MsgPushMsg> list);

    MsgPushMsg selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(MsgPushMsg record);

    int updateByPrimaryKeyWithBLOBs(MsgPushMsg record);

    int updateByPrimaryKey(MsgPushMsg record);
}