package com.hliedu.hos.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: hos_doc_plan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HosDocPlan {
    /**
     * ID
     *
     * Column:    PLAN_ID
     * Nullable:  false
     */
    private Integer planId;

    /**
     * 排期CODE
     *
     * Column:    PLAN_CODE
     * Nullable:  true
     */
    private String planCode;

    /**
     * 医师CODE
     *
     * Column:    DOC_CODE
     * Nullable:  true
     */
    private String docCode;

    /**
     * 日期
     *
     * Column:    PLAN_DATE
     * Nullable:  true
     */
    private String planDate;

    /**
     * 周几
     *
     * Column:    PLAN_WEEK
     * Nullable:  true
     */
    private String planWeek;

    /**
     * 接待人数
     *
     * Column:    ASK_NUM
     * Nullable:  true
     */
    private Integer askNum;

    /**
     * 已预约人数
     *
     * Column:    SUBSCRIBE_NUM
     * Nullable:  true
     */
    private Integer subscribeNum;

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
     * 状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;
}