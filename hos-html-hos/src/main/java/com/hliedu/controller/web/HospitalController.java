package com.hliedu.controller.web;

import com.hliedu.bean.HospitalBean;
import com.hliedu.bs.domain.BsArea;
import com.hliedu.bs.domain.BsCity;
import com.hliedu.bs.domain.BsProvince;
import com.hliedu.controller.BaseController;
import com.hliedu.core.bean.ResultBean;
import com.hliedu.ee.redis.RedisHashUtil;
import com.hliedu.ee.redis.RedisListUtil;
import com.hliedu.hos.domain.HosDept;
import com.hliedu.hos.domain.HosHospital;
import com.hliedu.hos.service.HosDeptService;
import com.hliedu.hos.service.HosHospitalService;
import com.hliedu.mybatis.page.QueryResult;
import com.hliedu.tools.DateUtils;
import com.hliedu.tools.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/web/hos")
public class HospitalController extends BaseController {

    @Autowired
    private HosHospitalService hospitalService;
    @Autowired
    private HosDeptService deptService;

    @RequestMapping("list")
    public String list(ModelMap modelMap,Integer rows ,Integer page,
                       String hosName ,String hosAddr) throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("rows" , rows);
        map.put("page" , page);
        if(hosName != null){
            map.put("hosName" , hosName);
            modelMap.addAttribute("hosName" , hosName);
        }

        if (hosAddr != null) {
            map.put("hosAddr" , hosAddr);
            modelMap.addAttribute("hosAddr" , hosAddr);
        }
        //开启模糊查询
        map.put("fuzzy" , true);
        //开启排序
        map.put("order" , true);

        QueryResult<HosHospital> hospitalPage = hospitalService.queryHospitalPage(map);

        //返回前端集合
        modelMap.addAttribute("hospitalList" , hospitalPage.getList());

        //返回前端需要的分页数据
        modelMap.addAttribute("pageTools" , hospitalPage.getPageTools());

        //获取省份信息
        modelMap.addAttribute("provinces" , getProvince().getDataObj());
        modelMap.addAttribute("ctx" , "/hosweb");

        //将科室的信息发送到前台
        QueryResult<HosDept> deptPage = deptService.queryDeptPage(new HashMap<>());
        modelMap.addAttribute("depts" , deptPage.getList());

        return "hospital_management";
    }

    @RequestMapping("add")
    private String add(HospitalBean hospital) throws Exception {
        if(hospital == null){
            System.out.println("获取添加数据为空");
        }else{
            //解析出科室是数组或集合
            String depts = hospital.getDepts();
            //逗号开头的移除掉
            if(depts.startsWith(","))depts=depts.substring(1, depts.length());
            String[] deptArray = depts.split(",");

            //保存医馆信息，并返回ID
            HosHospital hosHospital = wrapperHospital(hospital, deptArray.length);
            Integer hosId = hospitalService.saveHospital(hosHospital);

            //拿到保存的ID，新增医馆和科室的关系
            List<Map<String,Object>> hosDeptRefs = new ArrayList<>();
            for (String temp : deptArray) {
                Map<String,Object> hosDept = new HashMap<>();
                hosDept.put("hosId" , hosId);
                hosDept.put("deptId" , Integer.valueOf(temp));
                hosDeptRefs.add(hosDept);
            }
            Boolean flag = hospitalService.saveHosDeptRefBatch(hosDeptRefs);
            if(flag){
                System.out.println("保存成功");
            }
        }
        return "redirect:list";
    }

    @Autowired
    private RedisListUtil redisListUtil;

    @RequestMapping("getProvince")
    @ResponseBody
    private ResultBean getProvince(){
        List<Object> provinceList = redisListUtil.getList("province_list", 0, -1);
        return new ResultBean(provinceList);
    }

    @Autowired
    private RedisHashUtil redisHashUtil;

    @RequestMapping("getCityByProvince")
    @ResponseBody
    private ResultBean getCityByProvince(String provinceCode){
        Object cityList = redisHashUtil.getMapVal("city_list", provinceCode);
        return new ResultBean(cityList);
    }

    @RequestMapping("getAreaByCity")
    @ResponseBody
    private ResultBean getAreaByCity(String cityCode){
        Object areaList = redisHashUtil.getMapVal("area_list", cityCode);
        return new ResultBean(areaList);
    }

    /**
     * 根据省代码获取名称
     * @param provinceCode
     * @return
     */
    private String getProvinceName(String provinceCode){
        ResultBean province = getProvince();
        Object dataObj = province.getDataObj();
        if(dataObj instanceof List){
            List<BsProvince> provinces = (List<BsProvince>)dataObj;
            for (BsProvince temp : provinces) {
                if(temp.getProvinceCode().equals(provinceCode)){
                    return temp.getProvinceName();
                }
            }
        }
        System.out.println("未根据provinceCode获取到对应的名称：" + provinceCode);
        return "";
    }

    /**
     * 获取城市名称
     * @param provinceCode
     * @param cityCode
     * @return
     */
    private String getCityName(String provinceCode , String cityCode){
        Object dataObj = getCityByProvince(provinceCode).getDataObj();
        if(dataObj instanceof List){
            List<BsCity> citys = (List<BsCity>)dataObj;
            for (BsCity temp : citys) {
                if(temp.getCityCode().equals(cityCode)){
                    return temp.getCityName();
                }
            }
        }
        System.out.println("未根据cityCode获取到对应的名称：" + cityCode);
        return "";
    }

    /**
     * 获取区县名称
     * @param cityCode
     * @param areaCode
     * @return
     */
    private String getAreaName(String cityCode , String areaCode){
        Object dataObj = getAreaByCity(cityCode).getDataObj();
        if(dataObj instanceof List){
            List<BsArea> areas = (List<BsArea>)dataObj;
            for (BsArea temp : areas) {
                if(temp.getAreaCode().equals(areaCode)){
                    return temp.getAreaName();
                }
            }
        }
        System.out.println("未根据areaCode获取到对应的名称：" + areaCode);
        return "";
    }

    /**
     * 封装待保存医馆的实体
     * @param hospital
     * @param deptNum
     * @return
     */
    private HosHospital wrapperHospital(HospitalBean hospital , Integer deptNum){
        HosHospital hosHospital = new HosHospital();
        String hosCode = DateUtils.getDateString(new Date() , DateUtils.DATETIMESTOREFORMAT)
                + RandomUtils.generateRandom(4, 0);
        String provinceCode = hospital.getProvinceCode();
        String cityCode = hospital.getCityCode();
        String areaCode = hospital.getAreaCode();

        hosHospital.setHosCode(hosCode);
        hosHospital.setHosName(hospital.getHosName());
        hosHospital.setHosAddr(hospital.getHosAddr());
        hosHospital.setProvinceCode(provinceCode);
        hosHospital.setCityCode(cityCode);
        hosHospital.setAreaCode(areaCode);
        hosHospital.setProvinceName(getProvinceName(provinceCode));
        hosHospital.setCityName(getCityName(provinceCode , cityCode));
        hosHospital.setAreaName(getAreaName(cityCode ,areaCode));
        hosHospital.setDeptNum(deptNum);
        hosHospital.setDoctorNum(0);
        hosHospital.setDataState(1);
        return hosHospital;
    }
}

