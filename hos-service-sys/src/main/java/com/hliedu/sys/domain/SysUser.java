package com.hliedu.sys.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: sys_user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    /**
     * 用户ID自增
     *
     * Column:    USER_ID
     * Nullable:  false
     */
    private Integer userId;

    /**
     * 用户CODE
     *
     * Column:    USER_CODE
     * Nullable:  true
     */
    private String userCode;

    /**
     * 用户名
     *
     * Column:    USER_NAME
     * Nullable:  true
     */
    private String userName;

    /**
     * 密码
     *
     * Column:    PASSWORD
     * Nullable:  true
     */
    private String password;

    /**
     * 真实姓名
     *
     * Column:    REAL_NAME
     * Nullable:  true
     */
    private String realName;

    /**
     * 用户状态(1-启用、-1-禁用)
     *
     * Column:    USER_STATUS
     * Nullable:  true
     */
    private Integer userStatus;

    /**
     * 用户类型(1-超级管理员、2-普通管理员)
     *
     * Column:    USER_TYPE
     * Nullable:  true
     */
    private Integer userType;

    /**
     * 备注
     *
     * Column:    MEMO
     * Nullable:  true
     */
    private String memo;

    /**
     * 创建日期
     *
     * Column:    GMT_CREATE
     * Nullable:  true
     */
    private Date gmtCreate;

    /**
     * 最后修改日期
     *
     * Column:    GMT_MODIFIED
     * Nullable:  true
     */
    private Date gmtModified;

    /**
     * 数据状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;
}