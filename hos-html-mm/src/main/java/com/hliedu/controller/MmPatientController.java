package com.hliedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/mm/painent")
public class MmPatientController {

    /**
     * 就诊人列表
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("patients")
    public String patients(ModelMap modelMap) throws Exception {
        System.out.println("就诊人列表");
        modelMap.addAttribute("activeYy" , "_active");
        return "my_appointment_details";
    }
}
