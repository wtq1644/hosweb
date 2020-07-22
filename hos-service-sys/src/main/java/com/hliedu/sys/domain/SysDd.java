package com.hliedu.sys.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: sys_dd
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDd {
    /**
     * Column:    DD_ID
     * Nullable:  false
     */
    private Integer ddId;

    /**
     * 对应的TABLE
     *
     * Column:    DD_TABLE
     * Nullable:  true
     */
    private String ddTable;

    /**
     * TABLE中COLUMN
     *
     * Column:    DD_COLUMN
     * Nullable:  true
     */
    private String ddColumn;

    /**
     * 代码
     *
     * Column:    DD_CODE
     * Nullable:  true
     */
    private String ddCode;

    /**
     * 代码对应的显示的值
     *
     * Column:    DD_VALUE
     * Nullable:  true
     */
    private String ddValue;

    /**
     * 说明
     *
     * Column:    DD_REMARK
     * Nullable:  true
     */
    private String ddRemark;

    /**
     * 排序
     *
     * Column:    DD_ORDER
     * Nullable:  true
     */
    private Integer ddOrder;

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