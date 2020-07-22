package com.mymc.mybatis.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: bbs_zone
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BbsZone {
    /**
     * ID
     *
     * Column:    ZONE_ID
     * Nullable:  false
     */
    private Integer zoneId;

    /**
     * 空间专区CODE
     *
     * Column:    ZONE_CODE
     * Nullable:  true
     */
    private String zoneCode;

    /**
     * 专区名称
     *
     * Column:    ZONE_NAME
     * Nullable:  true
     */
    private String zoneName;

    /**
     * 专区描述
     *
     * Column:    ZONE_REMARK
     * Nullable:  true
     */
    private String zoneRemark;

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