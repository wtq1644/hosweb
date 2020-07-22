package com.hliedu.sys.controller;

import com.hliedu.bs.service.BsAreaService;
import com.hliedu.bs.service.BsCityService;
import com.hliedu.bs.service.BsProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * BS模块缓存刷新
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/bs")
public class BsCacheController {

    @Autowired
    private BsProvinceService provinceService;
    @Autowired
    private BsCityService cityService;
    @Autowired
    private BsAreaService areaService;

    @RequestMapping("flushCityCache")
    @ResponseBody
    public String flushCityCache(){

        provinceService.cacheProvince();
        cityService.cacheCityByProvince();
        areaService.cacheAreaByCity();

        return "ok";
    }
}
