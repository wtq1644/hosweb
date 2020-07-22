package com.hliedu.controller.h5;

import com.hliedu.controller.BaseController;
import com.hliedu.core.bean.ResultBean;
import com.hliedu.hos.domain.HosDept;
import com.hliedu.hos.domain.HosDocPlan;
import com.hliedu.hos.domain.HosDoctor;
import com.hliedu.hos.domain.HosHospital;
import com.hliedu.hos.service.HosDeptService;
import com.hliedu.hos.service.HosDocPlanService;
import com.hliedu.hos.service.HosDoctorService;
import com.hliedu.hos.service.HosHospitalService;
import com.hliedu.mybatis.page.QueryResult;
import com.hliedu.tools.DateUtils;
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
@RequestMapping("/h5/doc")
public class DoctorH5Controller extends BaseController {

    @Autowired
    private HosHospitalService hospitalService;

    @Autowired
    private HosDeptService deptService;

    @Autowired
    private HosDoctorService hosDoctorService;
    @Autowired
    private HosDocPlanService hosDocPlanService;

    /**
     * 从科室分类点击进入到医师列表
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="doctors" , method = RequestMethod.GET)
    public String doctors(ModelMap modelMap  , HttpServletRequest request , Integer deptId , String deptCode) throws Exception {
        System.out.println("从科室进入医师列表");

        //顶部默认加载有号的日期
        if(StringUtils.isNotBlank(deptCode)){
            Map<String,Object> param = new HashMap<>();
            param.put("deptCode" , deptCode);
            QueryResult<HosDocPlan> docPlanResult = hosDocPlanService.queryPlanPage(param);
            if (docPlanResult != null) {
                List<HosDocPlan> list = docPlanResult.getList();
                //将有号的日期封装，发送至前台页面，必须使用有序Map
                TreeMap<String,Object> planDateMap = new TreeMap<>();
                for (HosDocPlan hosDocPlan : list) {
                    planDateMap.put(hosDocPlan.getPlanDate() , hosDocPlan.getPlanWeek());
                }
                modelMap.addAttribute("planDateMap" , planDateMap);
                //获取当前时间发送至前台，用于显示选中当前状态
                String currentDate = DateUtils.getAfterDate(new Date(), 1, "M/dd");
                modelMap.addAttribute("currentDate" , currentDate);

            }
        }
        modelMap.addAttribute("activeSy" , "_active");
        //将ID发送给界面，由界面异步刷新调用获取数据
        modelMap.addAttribute("deptId" , deptId);

        modelMap.addAttribute("ctx"  , request.getContextPath());
        return "dept";
    }

    /**
     * 获取医师列表
     * @param hosId
     * @param deptId
     * @param dateStr
     * @param page
     * @return
     * @throws Exception
     */
    @RequestMapping(value="doctors" , method = RequestMethod.POST)
    @ResponseBody
    public ResultBean doctors(Integer hosId , Integer deptId ,String dateStr , Integer page) throws Exception {
        //获取排期表信息
        Map<String,Object> param = new HashMap<>();
        param.put("page" , page);
        param.put("dataState" , 1);
        if(deptId != null){
            //按照科室查询
            param.put("deptId" , deptId);
        }else {
            //按照医馆查询
            param.put("hosId" , hosId);
        }
        /*
         * 根据医生排期表过滤掉非值班的医生
         */
        if(StringUtils.isEmpty(dateStr)){
            //如果没有传入排班日期，则默认选择明天作为排班日期
            dateStr = DateUtils.getAfterDate(new Date(), 1, "M/dd");
        }
        param.put("planDate" , dateStr);
        //此处直接连表查询，跟上课时讲的方案不一样，上课讲的是一个表一个表查询
        QueryResult<Map<String, Object>> mapQueryResult = hosDoctorService.queryDoctorByDeptAndPlanPage(param);
        return new ResultBean(mapQueryResult);
    }


    /**
     * 点击预约，进入预约医师详情界面
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="yuyueDoctor" , method = RequestMethod.GET)
    public String yuyueDoctor(ModelMap modelMap , Integer docId , String dateStr) throws Exception {
        //将该医师的详细信息发送到页面
        HosDoctor doctor = hosDoctorService.getDoctor(docId);
        Integer hosId = doctor.getHosId();
        HosHospital hospital = hospitalService.getHospital(hosId);
        modelMap.addAttribute("hospital"  , hospital);
        modelMap.addAttribute("doctor"  , doctor);


        //就诊日期与周几
        if(StringUtils.isEmpty(dateStr)){
            dateStr = DateUtils.getAfterDate(new Date(), 1, "M/dd");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("planDate" , dateStr);
        param.put("docCode" , doctor.getDocCode());
        QueryResult<HosDocPlan> docPlanResult = hosDocPlanService.queryPlanPage(param);
        if (docPlanResult != null) {
            //获取是周几
            String planWeek = docPlanResult.getList().get(0).getPlanWeek();
            modelMap.addAttribute("planWeek"  , planWeek);
            modelMap.addAttribute("planDate"  , dateStr);
        }
        return "doctors_home";
    }

}

