package com.hliedu.interceptor;

import com.hliedu.core.bean.ResultBean;
import com.hliedu.tools.JsonUtil;
import com.hliedu.tools.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static String SESSION_KEY = "USER_LOGIN";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Object userInfo = request.getSession().getAttribute(SESSION_KEY);
        if(userInfo == null){
            //用户未登录
            System.out.println("拦截器：用户未登录");
            if(StringUtils.isNotBlank(request.getHeader("X-Requested-With"))){
                //JSON以流形式返回
                jsonRedirect(request ,response , "-1" , "登录失败" , "/sys/login");
            }else{
                //跳转至登录页面
                request.getRequestDispatcher("/sys/login").forward(request , response);
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param errCode 错误代码
     * @param errMsg 提示信息
     * @param uri 重定向路径
     */
    protected void jsonRedirect(HttpServletRequest request, HttpServletResponse response, String errCode, String errMsg, String uri) {
        //跨域
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("text/json; charset=" + request.getCharacterEncoding());
        // 输出String
        try {
            response.getWriter().print(JsonUtil.getInstance().toJson(new ResultBean(errCode, errMsg, uri)));
        } catch (Exception e) {
        }
    }
}
