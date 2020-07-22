package com.hliedu.mm.mapper;

import com.hliedu.mm.domain.MmPatient;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
* Created by Mybatis Generator on 2019/06/15
*/
@Repository
public interface MmPatientMapper {
    int deleteByPrimaryKey(Integer patientId);

    int insert(MmPatient record);

    int insertSelective(MmPatient record);

    int count(Map<String, Object> parameters);

    List<MmPatient> query(Map<String, Object> parameters);

    int updateStateByPrimaryKey(Map<String, Object> map);

    MmPatient getByCode(Map<String, Object> map);

    int delByCode(Map<String, Object> map);

    void insertBatch(List<MmPatient> list);

    MmPatient selectByPrimaryKey(Integer patientId);

    int updateByPrimaryKeySelective(MmPatient record);

    int updateByPrimaryKey(MmPatient record);
}