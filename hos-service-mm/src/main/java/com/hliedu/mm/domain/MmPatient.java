package com.hliedu.mm.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: mm_patient
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmPatient {
    /**
     * ID
     *
     * Column:    PATIENT_ID
     * Nullable:  false
     */
    private Integer patientId;

    /**
     * 就诊人CODE
     *
     * Column:    PATIENT_CODE
     * Nullable:  true
     */
    private String patientCode;

    /**
     * 姓名
     *
     * Column:    PATIENT_NAME
     * Nullable:  true
     */
    private String patientName;

    /**
     * 手机号
     *
     * Column:    PHONE
     * Nullable:  true
     */
    private String phone;

    /**
     * 身份证号
     *
     * Column:    ID_CARD
     * Nullable:  true
     */
    private String idCard;

    /**
     * 出生日期
     *
     * Column:    BIRTHDAY
     * Nullable:  true
     */
    private Date birthday;

    /**
     * 性别
     *
     * Column:    SEX
     * Nullable:  true
     */
    private String sex;

    /**
     * 备注
     *
     * Column:    MEMO
     * Nullable:  true
     */
    private String memo;

    /**
     * 创建时间
     *
     * Column:    GMT_CREATE
     * Nullable:  true
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     *
     * Column:    GMT_MODIFIED
     * Nullable:  true
     */
    private Date gmtModified;

    /**
     * 系统状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;
}