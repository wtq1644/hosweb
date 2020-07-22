package com.mymc.plugin.service;

import java.util.List;

/**
 * 接口生成所需信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class ServiceConfig {

    //Service接口名称，如：UserService
    private String interfaceStr;

    //服务包根名，不包括.service，如：com.hliedu.ee.user
    private String servicePkg;

    //实现类需导入的包
    private List<String> importslist;

    //service接口需导入的包
    private List<String> importsilist;

    //Service中业务对象的定义，可以多个
    private List<ServiceProConfig> proList;

    //接口描述
    private String description;

    public String getInterfaceStr() {
        return interfaceStr;
    }

    public void setInterfaceStr(String interfaceStr) {
        this.interfaceStr = interfaceStr;
    }

    public String getServicePkg() {
        return servicePkg;
    }

    public void setServicePkg(String servicePkg) {
        this.servicePkg = servicePkg;
    }

    public List<String> getImportslist() {
        return importslist;
    }

    public void setImportslist(List<String> importslist) {
        this.importslist = importslist;
    }

    public List<String> getImportsilist() {
        return importsilist;
    }

    public void setImportsilist(List<String> importsilist) {
        this.importsilist = importsilist;
    }

    public List<ServiceProConfig> getProList() {
        return proList;
    }

    public void setProList(List<ServiceProConfig> proList) {
        this.proList = proList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
