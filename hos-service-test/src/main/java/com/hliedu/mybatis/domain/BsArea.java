package com.hliedu.mybatis.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: bs_area
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BsArea {
    /**
     * 自增列
     *
     * Column:    AREA_ID
     * Nullable:  false
     */
    private Integer areaId;

    /**
     * 区代码
     *
     * Column:    AREA_CODE
     * Nullable:  false
     */
    private String areaCode;

    /**
     * 父级市代码
     *
     * Column:    CITY_CODE
     * Nullable:  true
     */
    private String cityCode;

    /**
     * 市名称
     *
     * Column:    AREA_NAME
     * Nullable:  false
     */
    private String areaName;

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