package com.hliedu.plugin.service;

import com.hliedu.tools.StringUtils;

/**
 * 接口实现类中的成员属性信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class ServiceProConfig {

    //Mapper接口名称
    private String mapper;

    //Mapper接口描述
    private String mapperDesc;

    //MODEL名称
    private String modelName;

    //系统主键属性
    private String idName;

    //业务主键属性
    private String codeName;

    //首字母小写Mapper名
    private String ldao;

    //首字母小写MODEL名
    private String lname;

    //简称，用于方法名
    private String nick;

    //首字母大写业务主键
    private String uCodeName;
    private String getidName;

    public String getGetidName() {
        return getidName;
    }

    public void setGetidName(String getidName) {
        this.getidName = getidName;
    }

    public String getuCodeName() {
        return uCodeName;
    }
    public void setuCodeName(String uCodeName) {
        this.uCodeName = uCodeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        setuCodeName(StringUtils.toUpperCaseFirstOne(codeName));
        this.codeName = codeName;
    }
    public String getIdName() {
        return idName;
    }
    public void setIdName(String idName) {
        this.idName = idName;
    }
    public String getLdao() {
        return ldao;
    }
    public void setLdao(String ldao) {
        this.ldao = ldao;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMapper() {
        return mapper;
    }

    public void setMapper(String mapper) {
        this.mapper = mapper;
    }

    public String getMapperDesc() {
        return mapperDesc;
    }

    public void setMapperDesc(String mapperDesc) {
        this.mapperDesc = mapperDesc;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
