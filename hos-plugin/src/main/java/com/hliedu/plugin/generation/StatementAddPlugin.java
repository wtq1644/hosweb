package com.hliedu.plugin.generation;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis查询扩展，自定义插件
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class StatementAddPlugin extends PluginAdapter {

    //排序标志
    protected static boolean needOrderby = false;
    //默认排序SQL
    protected static String orderbyColumns = "GMT_CREATE desc";

    @Override
    public boolean validate(List<String> warnings) {
        String needOrderStr = properties.getProperty(PluginProperty.QUERY_FUZZY);
        if (StringUtility.stringHasValue(needOrderStr)) {
            needOrderby = true;
        }

        String orderbyColumnStr = properties.getProperty(PluginProperty.QUERY_ORDERBY_COLUMNS);
        if (StringUtility.stringHasValue(orderbyColumnStr)) {
            orderbyColumns = orderbyColumnStr;
        }
        //一定要返回true
        return true;
    }
    //最先被加载的方法是以clientXXX开头的，也就是说我们可以找准这个切入点，去织入一些代码


    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3) {
            copyAndAddMethod(method, interfaze,introspectedTable);
        }
        return true;
    }

    /**
     * Mapper 扩展的方法生成
     * @param method
     * @param interfaze
     * @param introspectedTable
     */
    private void copyAndAddMethod(Method method, Interface interfaze,IntrospectedTable introspectedTable) {
        ClientAddMethodHandler addMethodHandler = new ClientAddMethodHandler(interfaze, introspectedTable);
        //1统计方法
        addMethodHandler.addCountMethod();
        //2查询方法
        addMethodHandler.addQueryMethod();
        //3修改状态方法
        addMethodHandler.addUpdateStateByPrimaryKeyMethod();
        //根据CODE操作
        String codeColumn = introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN);
        if (!"".equals(codeColumn)) {
            //4根据Code查询的方法
            addMethodHandler.addGetByCode();
            //5根据Code删除的方法
            addMethodHandler.delByCode();
        }
        //6批量新增的方法
        addMethodHandler.addInsertBatchMethod();
    }

    //XML存储
    private List<XmlElement> xmlElementsToAdd;

    /**
     * sqlMap扩展，用于添加Mapper.xml配置
     * @param element
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (introspectedTable.getTargetRuntime() == IntrospectedTable.TargetRuntime.MYBATIS3) {
            try{
                this.xmlElementsToAdd = copyAndSaveElement(introspectedTable);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
        return true;
    }

    private List<XmlElement> copyAndSaveElement(IntrospectedTable introspectedTable) {
        SqlMapAddElementHandler sqlMapAddElementHandler = new SqlMapAddElementHandler(introspectedTable);
        List<XmlElement> xmlElementsToAdd = new ArrayList<XmlElement>();
        //模糊查询条件
        XmlElement fuzzyCondition = null;
        fuzzyCondition = sqlMapAddElementHandler.generateCondition(true);
        xmlElementsToAdd.add(fuzzyCondition);
        //普通查询条件
        XmlElement normalCondition = sqlMapAddElementHandler.generateCondition(false);
        xmlElementsToAdd.add(normalCondition);
        //select语句
        XmlElement selectElement = sqlMapAddElementHandler.generateSelectQuery();
        xmlElementsToAdd.add(selectElement);
        //batchinsert语句
        XmlElement batchinsertElement = sqlMapAddElementHandler.generateBatchInsert();
        xmlElementsToAdd.add(batchinsertElement);
        //count语句
        xmlElementsToAdd.add(sqlMapAddElementHandler.generateSelectCount());
        //状态更新语句
        xmlElementsToAdd.add(sqlMapAddElementHandler.generateUpdateState());

        String codeColumn = introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN);
        if(codeColumn != null && !"".equals(codeColumn)){
            //根据code查询语句
            xmlElementsToAdd.add(sqlMapAddElementHandler.generateSelectByCode());
            //根据code删除语句
            xmlElementsToAdd.add(sqlMapAddElementHandler.generateDelByCode());

        }
        return xmlElementsToAdd;
    }

    /**
     * xml文档追加操作
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        List<XmlElement> elements = this.xmlElementsToAdd;
        if (elements != null) {
            for (XmlElement element : elements) {
                document.getRootElement().addElement(element);
            }
        }
        return true;
    }

}
