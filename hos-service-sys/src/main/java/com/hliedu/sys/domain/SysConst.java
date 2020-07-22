package com.hliedu.sys.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: sys_const
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConst {
    /**
     * ID
     *
     * Column:    CONST_ID
     * Nullable:  false
     */
    private Integer constId;

    /**
     * 代码
     *
     * Column:    CONST_CODE
     * Nullable:  true
     */
    private String constCode;

    /**
     * 终端类型
     *
     * Column:    CONST_TYPE
     * Nullable:  true
     */
    private String constType;

    /**
     * 说明/内容
     *
     * Column:    CONST_INFO
     * Nullable:  true
     */
    private String constInfo;

    /**
     * 附加属性1(预留)
     *
     * Column:    PROPERTY1
     * Nullable:  true
     */
    private String property1;

    /**
     * 附加属性2(预留)
     *
     * Column:    PROPERTY2
     * Nullable:  true
     */
    private String property2;

    /**
     * 附加属性3(预留)
     *
     * Column:    PROPERTY3
     * Nullable:  true
     */
    private String property3;

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