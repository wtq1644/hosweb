package com.hliedu.msg.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: msg_config
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgConfig {
    /**
     * ID
     *
     * Column:    CONFIG_ID
     * Nullable:  false
     */
    private Integer configId;

    /**
     * 配置CODE
     *
     * Column:    CONFIG_CODE
     * Nullable:  true
     */
    private String configCode;

    /**
     * 配置描述
     *
     * Column:    CONFIG_DESC
     * Nullable:  true
     */
    private String configDesc;

    /**
     * 配置类型（1短信、2邮箱、3站内信、4微信）
     *
     * Column:    CONFIG_TYPE
     * Nullable:  true
     */
    private String configType;

    /**
     * 具体业务类型
     *
     * Column:    BUS_TYPE
     * Nullable:  true
     */
    private String busType;

    /**
     * 模板属性(JSON格式)
     *
     * Column:    TEMP_PROPERTY
     * Nullable:  true
     */
    private String tempProperty;

    /**
     * 应用名称(1-WEB、2-H5、3-APP)
     *
     * Column:    APP_NAME
     * Nullable:  true
     */
    private String appName;

    /**
     * 是否即时推送(1-是)
     *
     * Column:    HAS_NOW_PUSH
     * Nullable:  true
     */
    private Integer hasNowPush;

    /**
     * 有效时间(分钟)，-1无限制
     *
     * Column:    VALID_TIME
     * Nullable:  true
     */
    private Integer validTime;

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
     * 数据状态
     *
     * Column:    DATA_STATE
     * Nullable:  true
     */
    private Integer dataState;

    /**
     * 模板内容
     *
     * Column:    TEMP_CONTENT
     * Nullable:  true
     */
    private String tempContent;
}