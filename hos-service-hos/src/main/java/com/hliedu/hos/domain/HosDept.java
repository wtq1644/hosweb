package com.hliedu.hos.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: hos_dept
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HosDept {
    /**
     * ID
     *
     * Column:    DEPT_ID
     * Nullable:  false
     */
    private Integer deptId;

    /**
     * 科室名称
     *
     * Column:    DEPT_NAME
     * Nullable:  true
     */
    private String deptName;

    /**
     * 图片访问地址
     *
     * Column:    IMG_URL
     * Nullable:  true
     */
    private String imgUrl;

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

    /**
     * 科室代码
     *
     * Column:    DEPT_CODE
     * Nullable:  false
     */
    private String deptCode;
}