package com.hliedu.hos.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: hos_hospital
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HosHospital {
    /**
     * 医馆ID,自增
     *
     * Column:    HOS_ID
     * Nullable:  false
     */
    private Integer hosId;

    /**
     * 医馆代码
     *
     * Column:    HOS_CODE
     * Nullable:  false
     */
    private String hosCode;

    /**
     * 医馆名称
     *
     * Column:    HOS_NAME
     * Nullable:  true
     */
    private String hosName;

    /**
     * 所在省代码
     *
     * Column:    PROVINCE_CODE
     * Nullable:  true
     */
    private String provinceCode;

    /**
     * 所在市代码
     *
     * Column:    CITY_CODE
     * Nullable:  true
     */
    private String cityCode;

    /**
     * 所在区代码
     *
     * Column:    AREA_CODE
     * Nullable:  true
     */
    private String areaCode;

    /**
     * 所在省名称
     *
     * Column:    PROVINCE_NAME
     * Nullable:  true
     */
    private String provinceName;

    /**
     * 所在市名称
     *
     * Column:    CITY_NAME
     * Nullable:  true
     */
    private String cityName;

    /**
     * 所在区名称
     *
     * Column:    AREA_NAME
     * Nullable:  true
     */
    private String areaName;

    /**
     * 详细地址
     *
     * Column:    HOS_ADDR
     * Nullable:  true
     */
    private String hosAddr;

    /**
     * 医师总数
     *
     * Column:    DOCTOR_NUM
     * Nullable:  true
     */
    private Integer doctorNum;

    /**
     * 科室总数
     *
     * Column:    DEPT_NUM
     * Nullable:  true
     */
    private Integer deptNum;

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