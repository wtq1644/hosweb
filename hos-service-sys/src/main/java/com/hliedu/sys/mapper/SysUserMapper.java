package com.hliedu.sys.mapper;

import com.hliedu.mybatis.mapper.BaseMapper;
import com.hliedu.sys.domain.SysUser;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface SysUserMapper extends BaseMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    int count(Map<String, Object> parameters);

    List<SysUser> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    SysUser getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<SysUser> list);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
}