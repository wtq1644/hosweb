package com.hliedu.mm.mapper;

import com.hliedu.mm.domain.MmOrderInfo;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MmOrderInfoMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(MmOrderInfo record);

    int insertSelective(MmOrderInfo record);

    int count(Map<String, Object> parameters);

    List<MmOrderInfo> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MmOrderInfo getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MmOrderInfo> list);

    MmOrderInfo selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(MmOrderInfo record);

    int updateByPrimaryKey(MmOrderInfo record);
}