package com.hliedu.msg.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Table: msg_push_msg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgPushMsg {
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
     * 类型（1-站内信、2-微信）
     *
     * Column:    MSG_TYPE
     * Nullable:  true
     */
    private Integer msgType;

    /**
     * 发送者CODE
     *
     * Column:    SENDER_CODE
     * Nullable:  true
     */
    private String senderCode;

    /**
     * 发送者姓名
     *
     * Column:    SENDER_NAME
     * Nullable:  true
     */
    private String senderName;

    /**
     * 接收者CODE
     *
     * Column:    RECIVER_CODE
     * Nullable:  true
     */
    private String reciverCode;

    /**
     * 接收者姓名
     *
     * Column:    RECIVER_NAME
     * Nullable:  true
     */
    private String reciverName;

    /**
     * 发送时间
     *
     * Column:    SEND_TIME
     * Nullable:  true
     */
    private Date sendTime;

    /**
     * 发送状态(1未发送、2发生中、3已发送)
     *
     * Column:    SEND_STATUS
     * Nullable:  true
     */
    private Integer sendStatus;

    /**
     * 读取状态(1未读、2已读)
     *
     * Column:    READ_STATUS
     * Nullable:  true
     */
    private Integer readStatus;

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
     * 内容
     *
     * Column:    CONTENT
     * Nullable:  true
     */
    private String content;
}