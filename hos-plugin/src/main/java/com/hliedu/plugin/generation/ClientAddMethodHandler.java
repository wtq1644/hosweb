package com.hliedu.plugin.generation;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.HashSet;
import java.util.Set;

/**
 * 扩展Mapper方法实现
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class ClientAddMethodHandler {

    private Interface interfaze;
    private IntrospectedTable introspectedTable;

    public ClientAddMethodHandler(Interface interfaze, IntrospectedTable introspectedTable) {
        this.interfaze = interfaze;
        this.introspectedTable = introspectedTable;
    }

    private FullyQualifiedJavaType implparameterType = FullyQualifiedJavaType.getNewMapInstance();
    //参数类型为Map
    private FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.Map<String,Object>");

    private FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();

    /**
     * 1 新增Count方法
     */
    protected void addCountMethod() {

        Method countMethod = new Method("count");
        //添加参数
        countMethod.addParameter(new Parameter(parameterType, "parameters"));
        //添加返回类型
        countMethod.setReturnType(new FullyQualifiedJavaType("int"));
        interfaze.addMethod(countMethod);
    }

    /**
     * 2 增加Query方法
     */
    protected void addQueryMethod() {
        Method newMethod = new Method("query");
        //该query查询采用Map作为参数
        newMethod.addParameter(new Parameter(parameterType, "parameters"));
        //参数的导包类型为java.util.Map
        interfaze.addImportedType(implparameterType);

        //返回值类型为List<XXXDomain>类型
        FullyQualifiedJavaType returnListType = new FullyQualifiedJavaType("java.util.List<" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ">");
        newMethod.setReturnType(returnListType);
        //返回值的导包类型为java.util.List
        interfaze.addImportedType(returnType);

        //接口新增该query方法
        interfaze.addMethod(newMethod);
    }

    /**
     * 3 增加更改数据状态的方法
     */
    protected void addUpdateStateByPrimaryKeyMethod() {
        Method sdataMethod = new Method("updateStateByPrimaryKey");
        sdataMethod.addParameter(new Parameter(parameterType, "map"));
        sdataMethod.setReturnType(new FullyQualifiedJavaType("int"));
        interfaze.addMethod(sdataMethod);

    }

    /**
     * 4 增加根据Code查询的方法
     */
    protected void addGetByCode(){
        Method getMethod = new Method("getByCode");
        getMethod.addParameter(new Parameter(parameterType, "map"));
        getMethod.setReturnType(new FullyQualifiedJavaType(introspectedTable.getFullyQualifiedTable().getDomainObjectName()));
        interfaze.addMethod(getMethod);
    }

    /**
     * 5 增加根据Code删除的方法
     */
    protected void delByCode(){
        Method delMethod = new Method("delByCode");
        delMethod.addParameter(new Parameter(parameterType, "map"));
        delMethod.setReturnType(new FullyQualifiedJavaType("int"));
        interfaze.addMethod(delMethod);
    }

    /**
     * 6 增加批量插入方法
     */
    protected void addInsertBatchMethod(){
        Method insertBatchMethod = new Method("insertBatch");
        //声明参数类型
        FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType("java.util.List<" + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + ">");
        insertBatchMethod.addParameter(new Parameter(parameterType,"list"));
        interfaze.addMethod(insertBatchMethod);

        //声明需要导包的类型
        Set<FullyQualifiedJavaType> importedTypes=new HashSet<FullyQualifiedJavaType>();
        importedTypes.add(new FullyQualifiedJavaType("org.springframework.stereotype.Repository"));
        interfaze.addImportedTypes(importedTypes);

        //声明需要给该类加入的注解
        interfaze.addAnnotation("@Repository");

        if(!interfaze.getSuperInterfaceTypes().isEmpty()){
            interfaze.getSuperInterfaceTypes().clear();
            //加入基础类，下行代码可在generatorCOnfig.xml中的javaClientGenerator标签中加入rootInterface
            //interfaze.getSuperInterfaceTypes().add(new FullyQualifiedJavaType("BaseMapper"));
        }
    }
}
