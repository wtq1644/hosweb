package com.hliedu.msg.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: msg_mns_email
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgMnsEmail {
    /**
     * ID
     *
     * Column:    MSG_ID
     * Nullable:  false
     */
    private Integer msgId;

    /**
     * 消息CODE
     *
     * Column:    MSG_CODE
     * Nullable:  true
     */
    private String msgCode;

    /**
     * 类型（1-短信、2-邮箱）
     *
     * Column:    MSG_TYPE
     * Nullable:  true
     */
    private Integer msgType;

    /**
     * 标题
     *
     * Column:    MSG_TITLE
     * Nullable:  true
     */
    private String msgTitle;

    /**
     * 内容
     *
     * Column:    CONTENT
     * Nullable:  true
     */
    private String content;

    /**
     * 接收者（手机号，邮箱）
     *
     * Column:    RECIVER
     * Nullable:  true
     */
    private String reciver;

    /**
     * 发送时间
     *
     * Column:    SEND_TIME
     * Nullable:  true
     */
    private Date sendTime;

    /**
     * 发送状态(1新增、2发送中、3已发送)
     *
     * Column:    SEND_STATUS
     * Nullable:  true
     */
    private Date sendStatus;

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
}