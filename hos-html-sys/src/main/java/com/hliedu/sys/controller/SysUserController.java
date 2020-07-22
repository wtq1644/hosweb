package com.hliedu.sys.controller;

import com.hliedu.controller.BaseController;
import com.hliedu.core.bean.ResultBean;
import com.hliedu.core.bean.UserSession;
import com.hliedu.interceptor.LoginInterceptor;
import com.hliedu.mybatis.page.QueryResult;
import com.hliedu.sys.domain.SysUser;
import com.hliedu.sys.service.SysUserService;
import com.hliedu.tools.ListUtil;
import com.hliedu.tools.MD5Util;
import com.hliedu.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/sys")
@SessionAttributes("USER_LOGIN")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService userService;

    /**
     * 找到登录界面
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.GET)
    public String login(ModelMap modelMap , HttpServletRequest request){
        modelMap.put("ctx" , request.getContextPath());
        return "login_admin";
    }

    /**
     * Json请求
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "login" , method = RequestMethod.POST)
    @ResponseBody
    public ResultBean login(HttpServletRequest request, String userName , String password) throws Exception {

        //判断参数是否为空
        if(StringUtils.isBlankLoop(userName , password)){
            //返回响应信息给客户端
            //响应吗，消息内容，数据，所以需要设计一个Bean
            return new ResultBean(ResultBean.ERRORCODE, "用户名密码为空");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userName" , userName);
        map.put("userStatus" , 1);
        QueryResult<SysUser> userResult = userService.queryUserPage(map);

        if(userResult != null && ListUtil.isNotEmpty(userResult.getList())){
            //密文
            SysUser user = userResult.getList().get(0);
            String dbPwd = user.getPassword();
            //明文，带盐值的校验
            if(MD5Util.saltMD5Verify(password , dbPwd)){

                //存进session，必须配合Session
                request.getSession().setAttribute(LoginInterceptor.SESSION_KEY, getUserSession(user));
                //modelMap.addAttribute(LoginInterceptor.SESSION_KEY, getUserSession(user));
                return new ResultBean("登录成功");
            }else{
                return new ResultBean(ResultBean.ERRORCODE , "户名密码错误");
            }
        }else{
            return new ResultBean(ResultBean.ERRORCODE , "户名不存在");
        }
    }

    //封装userSession
    private UserSession getUserSession(SysUser user){
        UserSession userSession = new UserSession();
        userSession.setUserId(user.getUserId());
        userSession.setUserCode(user.getUserCode());
        userSession.setUserName(user.getUserName());
        userSession.setUserStatus(user.getUserStatus());
        userSession.setUserType(user.getUserType());
        return userSession;
    }

}
