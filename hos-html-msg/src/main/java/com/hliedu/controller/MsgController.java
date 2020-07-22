package com.hliedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@Controller
@RequestMapping("/h5/msg")
public class MsgController {

    /**
     * 消息列表
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("news")
    public String news(ModelMap modelMap) throws Exception {
        System.out.println("消息列表");
        modelMap.addAttribute("activeWd" , "_active");
        return "news_list";
    }

    /**
     * 消息详细内容
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping("newsDetail")
    public String newsDetail(ModelMap modelMap) throws Exception {
        System.out.println("消息列表");
        modelMap.addAttribute("activeWd" , "_active");
        return "news_list";
    }
}
