package com.hliedu.ee;

import com.hliedu.ee.redis.RedisHashUtil;
import com.hliedu.ee.redis.RedisValueUtil;
import com.hliedu.test.domain.Emp;
import com.hliedu.test.service.EmpService;
import com.hliedu.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * -
 * <p>
 * - 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
@RequestMapping("/empCon")
@Controller
public class TestController {


    private final EmpService empService;

    @Autowired
    public TestController(EmpService empService) {
        this.empService = empService;
    }

    @Autowired
    private RedisHashUtil redisHashUtil;
    @Autowired
    private RedisValueUtil redisValueUtil;

    @RequestMapping("query1")
    public String query(ModelMap modelMap){
//        Map<String,Object> empMap = new HashMap<>();
//        empMap.put("zs" , new Emp(1001 , "zs" , "开发"));
//        empMap.put("ls" , new Emp(1002 , "ls" , "测试"));
//        //保存Map对象
//        redisHashUtil.addMapAll("emp" , empMap);
//
//        //取出Map对象
//        Emp mapVal = (Emp)redisHashUtil.getMapVal("emp", "zs");
//        System.out.println("获取zs：" + mapVal.getEname());
//
//        //保存Map为JSON字符串
//        redisValueUtil.set("zsStr" , JsonUtil.getInstance().toJson(empMap));
//        String zsStr = redisValueUtil.get("zsStr");
//        Map<String, Emp> stringEmpMap = JsonUtil.getInstance().jsonToMap(zsStr, String.class, Emp.class);
//        System.out.println("lsStr：" + stringEmpMap.get("ls").getEname());
//


        //以后在工作中，多人开发项目时，提交之前，先更新
        System.out.println("123");
        List<Emp> emps = empService.queryEmp();
        modelMap.addAttribute("emps" , emps);
        modelMap.addAttribute("abc" , 123);

        //单值取法
//        modelMap.addAttribute("intVar", 100);
        modelMap.addAttribute("longVar", 2000000000000l);
        modelMap.addAttribute("stringVar", "你好啊");
        modelMap.addAttribute("doubleVar", 100.10);
//        modelMap.addAttribute("pojoVar", new Emp(1001 ,"张三" , "工程师"));
        //时间日期类型
        modelMap.addAttribute("dateUtilVar", new java.util.Date());
        modelMap.addAttribute("dateSqlVar", new java.sql.Date(new java.util.Date().getTime()));
        modelMap.addAttribute("booleanVar", true);
        modelMap.addAttribute("nullVar", null);
        /*字符串：当做html输出*/
        modelMap.addAttribute("strHtmlVar", "<font color='red'>我的肚子好饿，要吃饭</font>");

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("eid" , 1001);
        map.put("ename" , "zs");

        modelMap.addAttribute("title" , "医师管理");
        return "doctors_edit";
    }
}
