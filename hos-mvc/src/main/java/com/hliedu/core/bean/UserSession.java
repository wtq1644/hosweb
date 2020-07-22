package com.hliedu.core.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * UserSession缓存类
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Setter
@Getter
public class UserSession {

    //用户ID
    private Integer userId;

    //用户Code
    private String userCode;

    //用户名
    private String userName;

    //昵称
    private String nickName;

    //邮箱
    private String userEmail;

    //电话
    private String userPhone;

    //头像
    private String userIcon;

    //用户类型，后台使用(1-超级管理员、2-普通管理员)
    private Integer userType;

    //用户状态：1-启用、-1-禁用
    private Integer userStatus;

    //微信ID
    private String wxOpenId;
}
