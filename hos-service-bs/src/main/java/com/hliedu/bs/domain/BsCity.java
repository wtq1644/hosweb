package com.hliedu.bs.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: bs_city
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BsCity {
    /**
     * 自增列
     *
     * Column:    CITY_ID
     * Nullable:  false
     */
    private Integer cityId;

    /**
     * 市代码
     *
     * Column:    CITY_CODE
     * Nullable:  false
     */
    private String cityCode;

    /**
     * 市名称
     *
     * Column:    CITY_NAME
     * Nullable:  false
     */
    private String cityName;

    /**
     * 简称
     *
     * Column:    SHORT_NAME
     * Nullable:  false
     */
    private String shortName;

    /**
     * 省代码
     *
     * Column:    PROVINCE_CODE
     * Nullable:  true
     */
    private String provinceCode;

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