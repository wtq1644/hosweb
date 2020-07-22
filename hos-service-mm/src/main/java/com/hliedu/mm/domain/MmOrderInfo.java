package com.hliedu.mm.domain;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: mm_order_info
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmOrderInfo {
    /**
     * ID
     *
     * Column:    ORDER_ID
     * Nullable:  false
     */
    private Integer orderId;

    /**
     * 预约单号
     *
     * Column:    ORDER_CODE
     * Nullable:  true
     */
    private String orderCode;

    /**
     * 预约医师CODE
     *
     * Column:    DOC_CODE
     * Nullable:  true
     */
    private String docCode;

    /**
     * 预约人CODE
     *
     * Column:    MERBER_CODE
     * Nullable:  true
     */
    private String merberCode;

    /**
     * 就诊人CODE
     *
     * Column:    PATIENT_CODE
     * Nullable:  true
     */
    private String patientCode;

    /**
     * 医师排期CODE
     *
     * Column:    PLAN_CODE
     * Nullable:  true
     */
    private String planCode;

    /**
     * 预约类型(1-专家门诊)
     *
     * Column:    ORDER_TYPE
     * Nullable:  true
     */
    private Integer orderType;

    /**
     * 费用
     *
     * Column:    PRICE
     * Nullable:  true
     */
    private BigDecimal price;

    /**
     * 初/复诊(1-初诊、2-复诊)
     *
     * Column:    ORDER_COUNT
     * Nullable:  true
     */
    private Integer orderCount;

    /**
     * 就诊日期
     *
     * Column:    TREAT_DATE
     * Nullable:  true
     */
    private Date treatDate;

    /**
     * 就诊状态(1新建、2待就诊、3已就诊、4取消预约)
     *
     * Column:    TREAT_STATE
     * Nullable:  true
     */
    private Integer treatState;

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
     * 备注
     *
     * Column:    MEMO
     * Nullable:  true
     */
    private String memo;

    /**
     * 系统状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;
}