package com.hliedu.controller.h5;

import com.hliedu.controller.BaseController;
import com.hliedu.core.bean.ResultBean;
import com.hliedu.hos.domain.HosDept;
import com.hliedu.hos.domain.HosDocPlan;
import com.hliedu.hos.domain.HosDoctor;
import com.hliedu.hos.service.HosDeptService;
import com.hliedu.hos.service.HosDocPlanService;
import com.hliedu.hos.service.HosDoctorService;
import com.hliedu.hos.service.HosHospitalService;
import com.hliedu.mybatis.page.QueryResult;
import com.hliedu.tools.DateUtils;
import com.hliedu.tools.ListUtil;
import com.hliedu.tools.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 医馆、科室、医师
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/h5/hos")
public class HospitalH5Controller extends BaseController {

    @Autowired
    private HosHospitalService hospitalService;

    @Autowired
    private HosDeptService deptService;

    @Autowired
    private HosDoctorService hosDoctorService;
    @Autowired
    private HosDocPlanService hosDocPlanService;
    /**
     * 首页
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("index")
    public String list(ModelMap modelMap , HttpServletRequest request) throws Exception {
        System.out.println("h5首页跳转");
        modelMap.addAttribute("activeSy" , "_active");

        //默认医馆为1这条数据
        Integer hosId = 1;
        List<HosDept> hosDepts = deptService.queryDeptByHosId(hosId);
        if (hosDepts != null) {
            modelMap.addAttribute("depts"  , hosDepts);
            modelMap.addAttribute("ctx"  , request.getContextPath());
        }
        //把首页的医师列表查出来可不可以，此时不建议跳转界面的时候去拿数据，因为还有分页异步拿数据的情况
        return "index";
    }

    /**
     * 首页搜索
     * @param modelMap
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("search")
    public String search(ModelMap modelMap , HttpServletRequest request) throws Exception {

        //功能未开发，由同学们自行完成
        return "search";
    }
}

