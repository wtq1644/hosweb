package com.hliedu.plugin.service;

import com.hliedu.tools.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service生成程序
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class ServiceGenerator {

    public static String outputDir = "src\\main\\java";
    //解析工具
    private FtlTranslationService ftlTranslationService = new FtlTranslationService();

    /**
     * 创建Service
     * @param serviceConfig
     */
    public void createService(ServiceConfig serviceConfig) {
        if (!check(serviceConfig)) {
            return;
        }
        //模板初始化
        ftlTranslationService.init();

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("serviceConfig", serviceConfig);
        String filePath = serviceConfig.getServicePkg().replace(".", "/");
        try {
            //service接口生成路径
            String serviceOutPut = outputDir + "/" + filePath + "/service" + "/" + serviceConfig.getInterfaceStr() + ".java";
            ftlTranslationService.translate(data, "service.ftl",serviceOutPut);

            //impl实现生成路径
            String implOutputPath = outputDir + "/" + filePath + "/service/impl" + "/" + serviceConfig.getInterfaceStr() + "Impl.java";
            ftlTranslationService.translate(data, "serviceImpl.ftl",implOutputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 参数校验
     * 补充默认导包
     * @param serviceConfig
     * @return
     */
    public static boolean check(ServiceConfig serviceConfig) {
        //Service参数校验
        if (null == serviceConfig) return false;
        boolean flag = true;
        if (checkObjectNull(serviceConfig.getInterfaceStr())) {
            System.out.println("InterfaceStr为空");
            flag = false;
        }
        if (checkObjectNull(serviceConfig.getServicePkg())) {
            System.out.println("servicePkg为空");
            flag = false;
        }
        List<ServiceProConfig> proList = serviceConfig.getProList();
        if (null == proList || proList.isEmpty()) {
            System.out.println("proList为空");
            flag = false;
        }

        //Mapper属性校验
        boolean listflag = true;
        for (ServiceProConfig serverProConfig : proList) {
            if (checkObjectNull(serverProConfig.getMapper())) {
                System.out.println("proList:" + "Mapper为空");
                listflag = false;
            }
            if (checkObjectNull(serverProConfig.getMapperDesc())) {
                System.out.println("proList:" + "MapperDesc为空");
                listflag = false;
            }
            if (checkObjectNull(serverProConfig.getIdName())) {
                System.out.println("proList:" + "IdName为空");
                listflag = false;
            }

            if (checkObjectNull(serverProConfig.getModelName())) {
                System.out.println("proList:" + "Name为空");
                listflag = false;
            }
            if (checkObjectNull(serverProConfig.getNick())) {
                System.out.println("proList:" + "Nick为空");
                listflag = false;
            }

            //默认导包处理
            if (listflag) {
                //首字母转小写
                String ldao = StringUtils.toLowerCaseFirstOne(serverProConfig.getMapper());
                serverProConfig.setLdao(ldao);

                String lname = StringUtils.toLowerCaseFirstOne(serverProConfig.getModelName());
                serverProConfig.setLname(lname);

                String idName = StringUtils.toUpperCaseFirstOne(serverProConfig.getIdName());
                serverProConfig.setGetidName("get" + idName);

                List<String> ilist = serviceConfig.getImportsilist();
                if (null == ilist) {
                    ilist = new ArrayList<String>();
                }

                List<String> list = serviceConfig.getImportslist();
                if (null == list) {
                    list = new ArrayList<String>();
                }
                String servicePkg = serviceConfig.getServicePkg();
                ilist.add(servicePkg + ".domain." + serverProConfig.getModelName());
                list.add(servicePkg + ".mapper." + serverProConfig.getMapper());
                list.add(servicePkg + ".domain." + serverProConfig.getModelName());

                serviceConfig.setImportsilist(ilist);
                serviceConfig.setImportslist(list);
            }
        }

        if (!listflag) flag = false;

        if (flag) {
            //处理默认imPL
            List<String> list = serviceConfig.getImportslist();
            if (null == list) {
                list = new ArrayList<String>();
                serviceConfig.setImportslist(list);
            }
            list.add("com.hliedu.mybatis.page.PageTools");
            list.add("com.hliedu.mybatis.service.BaseServiceImpl");
            list.add("com.hliedu.tools.BeanUtils");
            list.add("com.hliedu.tools.StringUtils");
            list.add(serviceConfig.getServicePkg() + ".service." + serviceConfig.getInterfaceStr());
            list.add("java.util.Date");
            list.add("java.util.HashMap");
            list.add("java.util.List");
            list.add("java.util.Map");
            list.add("com.hliedu.mybatis.page.QueryResult");
            list.add("org.springframework.beans.factory.annotation.Autowired");
            list.add("org.springframework.stereotype.Service");

            //接口需要导入的包
            List<String> ilist = serviceConfig.getImportsilist();
            if (null == ilist) {
                ilist = new ArrayList<String>();
                serviceConfig.setImportsilist(ilist);
            }
            ilist.add("com.hliedu.mybatis.service.BaseService");
            ilist.add("com.hliedu.mybatis.page.QueryResult");
            ilist.add("java.util.Map");
        }
        return flag;
    }


    public static boolean checkObjectNull(Object obj) {
        if (null == obj) {
            return true;
        }
        if ("".equals(String.valueOf(obj))) {
            return true;
        }
        return false;
    }
}
