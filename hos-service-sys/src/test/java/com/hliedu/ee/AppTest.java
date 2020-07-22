package com.hliedu.ee;

import com.hliedu.plugin.service.ServiceConfig;
import com.hliedu.plugin.service.ServiceGenerator;
import com.hliedu.plugin.service.ServiceProConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppTest {


    public static void main(String[] args) throws IOException {
        //使用前，需要调整程序的working directory为本项目，否则默认加载到根工程下
        createFile();
    }

    public static void createFile() {
        ServiceConfig serverConfig = new ServiceConfig();
        //接口描述
        serverConfig.setDescription("后台用户信息");
        //接口名称
        serverConfig.setInterfaceStr("SysUserService");
        //所在package，不包括.service
        serverConfig.setServicePkg("com.hliedu.sys");


        //实现类中的属性（多个）
        List<ServiceProConfig> proList = new ArrayList<ServiceProConfig>();

        //属性A
        ServiceProConfig serviceProConfig = new ServiceProConfig();
        //Mapper接口名
        serviceProConfig.setMapper("SysUserMapper");
        //Model类名
        serviceProConfig.setModelName("SysUser");
        //Mapper描述
        serviceProConfig.setMapperDesc("后台用户信息");
        //Mapper对应主键ID
        serviceProConfig.setIdName("userId");
        //Mapper对应Code字段属性
        serviceProConfig.setCodeName("userCode");
        //业务简称，一般去除Model前缀名称，如BbsZone，则此处为Zone
        serviceProConfig.setNick("User");

        //加入集合
        proList.add(serviceProConfig);

        serverConfig.setProList(proList);
        new ServiceGenerator().createService(serverConfig);
    }

}