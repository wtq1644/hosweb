package com.hliedu.controller;

import com.hliedu.core.bean.UserSession;
import com.hliedu.interceptor.LoginInterceptor;
import com.hliedu.tools.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 所有Controller类的父类
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class BaseController {

    public UserSession getUserSession(HttpServletRequest request) {
        if (null == request || null == request.getSession())
            return null;
        return (UserSession) request.getSession().getAttribute(LoginInterceptor.SESSION_KEY);
    }

    /**
     * 返回工程目录
     * @param request
     * @return
     */
    public String getRequsetPath(HttpServletRequest request) {
        int port = request.getServerPort();
        //从数据库获取
        String schemeStr = "http";
        schemeStr = StringUtils.isBlank(schemeStr) ? "http" : schemeStr;
        String str = schemeStr + "://" + request.getServerName();
        if (port != 80) {
            str += ":" + port;
        }
        str = str += "/" + request.getContextPath();
        return str;
    }

    private Object UUIDLock = new Object();

    protected String createUUIDString() {
        synchronized (UUIDLock) {
            return UUID.randomUUID().toString().replace("-", "");
        }
    }
}