package com.hliedu.bs.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: bs_province
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BsProvince {
    /**
     * 自增列
     *
     * Column:    PROVINCE_ID
     * Nullable:  false
     */
    private Integer provinceId;

    /**
     * 省份代码
     *
     * Column:    PROVINCE_CODE
     * Nullable:  false
     */
    private String provinceCode;

    /**
     * 省份名称
     *
     * Column:    PROVINCE_NAME
     * Nullable:  false
     */
    private String provinceName;

    /**
     * 简称
     *
     * Column:    SHORT_NAME
     * Nullable:  false
     */
    private String shortName;

    /**
     * 经度
     *
     * Column:    LNG
     * Nullable:  true
     */
    private String lng;

    /**
     * 纬度
     *
     * Column:    LAT
     * Nullable:  true
     */
    private String lat;

    /**
     * 排序
     *
     * Column:    SORT
     * Nullable:  true
     */
    private Integer sort;

    /**
     * 创建时间
     *
     * Column:    GMT_CREATE
     * Nullable:  true
     */
    private Date gmtCreate;

    /**
     * 修改时间
     *
     * Column:    GMT_MODIFIED
     * Nullable:  true
     */
    private Date gmtModified;

    /**
     * 备注
     *
     * Column:    MEMO
     * Nullable:  true
     */
    private String memo;

    /**
     * 状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;
}