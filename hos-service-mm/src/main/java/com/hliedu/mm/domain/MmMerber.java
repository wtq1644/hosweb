package com.hliedu.mm.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: mm_merber
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MmMerber {
    /**
     * ID
     *
     * Column:    MERBER_ID
     * Nullable:  false
     */
    private Integer merberId;

    /**
     * 会员代码
     *
     * Column:    MERBER_CODE
     * Nullable:  true
     */
    private String merberCode;

    /**
     * 手机号
     *
     * Column:    USER_PHONE
     * Nullable:  true
     */
    private String userPhone;

    /**
     * 登录名
     *
     * Column:    LOGIN_NAME
     * Nullable:  true
     */
    private String loginName;

    /**
     * 密码
     *
     * Column:    PASSWORD
     * Nullable:  true
     */
    private String password;

    /**
     * 昵称
     *
     * Column:    NICK_NAME
     * Nullable:  true
     */
    private String nickName;

    /**
     * 头像
     *
     * Column:    USER_ICON
     * Nullable:  true
     */
    private String userIcon;

    /**
     * 微信ID
     *
     * Column:    WX_OPEN_ID
     * Nullable:  true
     */
    private String wxOpenId;

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