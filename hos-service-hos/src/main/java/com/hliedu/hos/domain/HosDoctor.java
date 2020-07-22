package com.hliedu.hos.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: hos_doctor
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HosDoctor {
    /**
     * 医师ID,自增
     *
     * Column:    DOC_ID
     * Nullable:  false
     */
    private Integer docId;

    /**
     * 医师CODE
     *
     * Column:    DOC_CODE
     * Nullable:  true
     */
    private String docCode;

    /**
     * 医师姓名
     *
     * Column:    DOC_NAME
     * Nullable:  true
     */
    private String docName;

    /**
     * 医师头像
     *
     * Column:    DOC_ICON
     * Nullable:  true
     */
    private String docIcon;

    /**
     * 职称
     *
     * Column:    DOC_TITLE
     * Nullable:  true
     */
    private String docTitle;

    /**
     * 医师类型(1-医师、2-助理)
     *
     * Column:    DOC_TYPE
     * Nullable:  true
     */
    private Integer docType;

    /**
     * 所属医馆ID
     *
     * Column:    HOS_ID
     * Nullable:  true
     */
    private Integer hosId;

    /**
     * 所属科室ID
     *
     * Column:    DEPT_ID
     * Nullable:  true
     */
    private Integer deptId;

    /**
     * 简介
     *
     * Column:    INTRODUCE
     * Nullable:  true
     */
    private String introduce;

    /**
     * 擅长
     *
     * Column:    EXPERT_DESC
     * Nullable:  true
     */
    private String expertDesc;

    /**
     * 预约费用
     *
     * Column:    ORDER_PRICE
     * Nullable:  true
     */
    private BigDecimal orderPrice;

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

    private String deptName;
}