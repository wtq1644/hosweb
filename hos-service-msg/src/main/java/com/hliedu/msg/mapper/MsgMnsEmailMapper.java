package com.hliedu.msg.mapper;

import com.hliedu.msg.domain.MsgMnsEmail;
import com.hliedu.mybatis.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MsgMnsEmailMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(MsgMnsEmail record);

    int insertSelective(MsgMnsEmail record);

    int count(Map<String, Object> parameters);

    List<MsgMnsEmail> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MsgMnsEmail getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MsgMnsEmail> list);

    MsgMnsEmail selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(MsgMnsEmail record);

    int updateByPrimaryKey(MsgMnsEmail record);
}