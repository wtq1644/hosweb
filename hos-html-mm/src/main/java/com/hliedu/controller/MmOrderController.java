package com.hliedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 我的预约
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/mm/order")
public class MmOrderController {
    /**
     * 我的预约列表
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("appoints")
    public String appoints(ModelMap modelMap) throws Exception {
        System.out.println("我的预约列表");
        modelMap.addAttribute("activeYy" , "_active");
        return "my_appointment";
    }

    /**
     * 我的预约详情
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("appointDetail")
    public String appointDetail(ModelMap modelMap) throws Exception {
        System.out.println("我的预约详情");
        modelMap.addAttribute("activeYy" , "_active");
        return "my_appointment_details";
    }
}
