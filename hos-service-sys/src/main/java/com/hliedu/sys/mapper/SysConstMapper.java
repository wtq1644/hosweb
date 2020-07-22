package com.hliedu.sys.mapper;

import com.hliedu.mybatis.mapper.BaseMapper;
import com.hliedu.sys.domain.SysConst;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface SysConstMapper extends BaseMapper {
    int deleteByPrimaryKey(Integer constId);

    int insert(SysConst record);

    int insertSelective(SysConst record);

    int count(Map<String, Object> parameters);

    List<SysConst> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    SysConst getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<SysConst> list);

    SysConst selectByPrimaryKey(Integer constId);

    int updateByPrimaryKeySelective(SysConst record);

    int updateByPrimaryKey(SysConst record);
}