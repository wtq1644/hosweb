package com.hliedu.plugin.generation;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.sql.Types;
import java.util.List;

/**
 * Mapper.xml中的查询追加
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public class SqlMapAddElementHandler {

    protected IntrospectedTable introspectedTable;

    public SqlMapAddElementHandler(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }


    /**
     * 模糊查询日期段
     */
    protected static final String fuzzyStartDateColumnSql = " AND (%1$s &gt;= #{%2$s}) ";
    protected static final String fuzzyEndDateColumnSql = " AND (%1$s &lt;= #{%2$s}) ";

    /**
     * 动态SQL条件判断处理
     */
    protected static final String testSql = "%1$s != null";
    protected static final String testSqlVarchar = "%1$s != null and %1$s.trim().length() != 0";

    /**
     * 查询返回的列名字段
     */
    protected static final String fuzzyColumnSql = " AND (%1$s like concat('%%',#{%2$s},'%%')) ";
    protected static final String normalColumnSql = "AND (%1$s = #{%2$s})";

    /**
     * 条件查询片段命名
     */
    protected static final String fuzzyQueryId = "%1$s_query_fuzzy_condition";
    protected static final String normalQueryId = "%1$s_query_condition";


    /**
     * 1 新增Count方法
     * @return 返回XML属性节点
     */
    protected XmlElement generateSelectCount() {
        XmlElement countElement = new XmlElement("select");
        countElement.addAttribute(new Attribute("id", "count"));
        countElement.addAttribute(new Attribute("resultType", "int"));
        TextElement selectText = new TextElement("select count(*) from " +
                introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ");
        countElement.addElement(selectText);
        //追加动态条件的语句
        XmlElement normalInc = new XmlElement("include");
        normalInc.addAttribute(new Attribute("refid", String.format(normalQueryId,
                introspectedTable.getFullyQualifiedTable().getIntrospectedTableName())));
        //模糊情况
        countElement.addElement(generateTestFuzzy(introspectedTable));
        //非模糊情况生成
        countElement.addElement(generateTestNotFuzzy(normalInc));
        return countElement;
    }

    /**
     * 1 新增query方法
     * @return  返回XML属性节点
     */
    protected XmlElement generateSelectQuery() {
        XmlElement selectElement = new XmlElement("select");
        selectElement.addAttribute(new Attribute("id", "query"));
        selectElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        //分页开始
//        XmlElement paginationStart = new XmlElement("include");
//        paginationStart.addAttribute(new Attribute("refid", paginationStartId));
//        selectElement.addElement(paginationStart);
        //创建查询statement
        TextElement selectText = new TextElement("select ");
        selectElement.addElement(selectText);
        //引入需要返回列名
        XmlElement columnListElement = new XmlElement("include");
        columnListElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        selectElement.addElement(columnListElement);
        //创建from table文本
        TextElement formText = new TextElement(" from " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ");
        selectElement.addElement(formText);
        //创建条件引入区
        XmlElement normalInc = new XmlElement("include");
        normalInc.addAttribute(new Attribute("refid", String.format(normalQueryId,
                introspectedTable.getFullyQualifiedTable().getIntrospectedTableName())));
        //fuzzy condition
//        if (StatementAddPlugin.isFuzzy) {
        //模糊情况
        selectElement.addElement(generateTestFuzzy(introspectedTable));
        //非模糊情况生成
        selectElement.addElement(generateTestNotFuzzy(normalInc));
//        } else {
//            selectElement.addElement(normalInc);
//        }
        //排序生成
        XmlElement testOrder = new XmlElement("if");
        testOrder.addAttribute(new Attribute("test", "order and orderStr == null"));
        testOrder.addElement(new TextElement("order by " + StatementAddPlugin.orderbyColumns));
        selectElement.addElement(testOrder);
        XmlElement testOrderStr = new XmlElement("if");
        testOrderStr.addAttribute(new Attribute("test", "order and orderStr != null and orderStr.trim().length() != 0"));
        testOrderStr.addElement(new TextElement("order by ${orderStr}"));
        selectElement.addElement(testOrderStr);
//        //分页结束
//        XmlElement paginationEnd = new XmlElement("include");
//        paginationEnd.addAttribute(new Attribute("refid", paginationEndId));
//        selectElement.addElement(paginationEnd);
        return selectElement;
    }

    /**
     * 添加模糊查询的条件
     *      <if test="fuzzy" >
     *          <include refid="um_user_query_fuzzy_condition" />
     *      </if>
     * @param introspectedTable
     * @return
     */
    private XmlElement generateTestFuzzy(IntrospectedTable introspectedTable) {
        XmlElement testFuzzy = new XmlElement("if");
        testFuzzy.addAttribute(new Attribute("test", "fuzzy"));
        XmlElement fuzzyInc = new XmlElement("include");
        fuzzyInc.addAttribute(new Attribute("refid", String.format(fuzzyQueryId,
                introspectedTable.getFullyQualifiedTable().getIntrospectedTableName())));
        testFuzzy.addElement(fuzzyInc);
        return testFuzzy;
    }

    /**
     * 添加非模糊查询的条件
     *     <if test="!fuzzy" >
     *       <include refid="um_user_query_condition" />
     *     </if>
     * @param normalInc
     * @return
     */
    private XmlElement generateTestNotFuzzy(XmlElement normalInc) {
        XmlElement testNotFuzzy = new XmlElement("if");
        testNotFuzzy.addAttribute(new Attribute("test", "!fuzzy"));
        testNotFuzzy.addElement(normalInc);
        return testNotFuzzy;
    }

    /**
     * 生成updateState的SQL语句
     * @return
     */
    protected XmlElement generateUpdateState() {
        XmlElement updateElement = new XmlElement("update");
        updateElement.addAttribute(new Attribute("id", "updateStateByPrimaryKey"));
        updateElement.addAttribute(new Attribute("parameterType", "Map"));
        TextElement updateText = new TextElement("update " +
                introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ");
        updateElement.addElement(updateText);
        TextElement setText = new TextElement(" set DATA_STATE = #{dataState,jdbcType=INTEGER},GMT_MODIFIED=SYSDATE() ");
        updateElement.addElement(setText);
        XmlElement settestFuzzy = new XmlElement("if");
        settestFuzzy.addAttribute(new Attribute("test", "memo != null"));
        TextElement setifText = new TextElement(" , MEMO = #{memo,jdbcType=VARCHAR} ");
        settestFuzzy.addElement(setifText);
        updateElement.addElement(settestFuzzy);
        String whereSql = "where ";
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
            whereSql += " " + introspectedColumn.getActualColumnName() + " = #{" + introspectedColumn.getJavaProperty() + ",jdbcType=" + introspectedColumn.getJdbcTypeName() + "}";
        }
        TextElement whereText = new TextElement(whereSql);
        updateElement.addElement(whereText);
        XmlElement testFuzzy = new XmlElement("if");
        testFuzzy.addAttribute(new Attribute("test", "oldDataState != null"));
        TextElement whereandText = new TextElement(" and DATA_STATE = #{oldDataState,jdbcType=INTEGER} ");
        testFuzzy.addElement(whereandText);
        updateElement.addElement(testFuzzy);
        return updateElement;
    }

    /**
     * 创建根据Code删除的SQL语句
     * @return
     */
    protected XmlElement generateDelByCode() {
        XmlElement selectElement = new XmlElement("delete");
        selectElement.addAttribute(new Attribute("id", "delByCode"));
        selectElement.addAttribute(new Attribute("parameterType", "Map"));
        //form table
        TextElement formText = new TextElement(" delete from " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ");
        selectElement.addElement(formText);
        //where
        TextElement whereText = new TextElement(" where  " + introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN) + " = #{" + getColumn(introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN)) + ",jdbcType=VARCHAR} ");
        selectElement.addElement(whereText);

//        XmlElement settestFuzzy = new XmlElement("if");
//        settestFuzzy.addAttribute(new Attribute("test", "tenantCode != null and tenantCode.trim().length() != 0"));
//        TextElement setifText = new TextElement(" and TENANT_CODE = #{tenantCode,jdbcType=VARCHAR} ");
//        settestFuzzy.addElement(setifText);
//        selectElement.addElement(settestFuzzy);

        return selectElement;
    }

    /**
     * 创建批量插入的SQL语句
     * @return
     */
    protected XmlElement generateBatchInsert() {
        XmlElement insertElement = new XmlElement("insert");
        insertElement.addAttribute(new Attribute("id", "insertBatch"));
        insertElement.addAttribute(new Attribute("useGeneratedKeys", "true"));
        insertElement.addAttribute(new Attribute("parameterType", "java.util.List"));
        TextElement intoText = new TextElement("insert into " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ( ");
        insertElement.addElement(intoText);
        //columnList
        String acls = "";
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (!"".equals(acls)) acls += ",";
            acls += introspectedColumn.getActualColumnName();
        }
        TextElement valuesText = new TextElement(acls + " ) values ");
        insertElement.addElement(valuesText);
        //values
        XmlElement valuesOrder = new XmlElement("foreach");
        valuesOrder.addAttribute(new Attribute("collection", "list"));
        valuesOrder.addAttribute(new Attribute("item", "item"));
        valuesOrder.addAttribute(new Attribute("index", "index"));
        valuesOrder.addAttribute(new Attribute("separator", ","));
        String vls = " ( ";
        String cls = "";
        for (IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
            if (!"".equals(cls)) cls += ",";
            cls += "#{item." + introspectedColumn.getJavaProperty() + ",jdbcType=" + introspectedColumn.getJdbcTypeName() + "}";
        }
        vls += cls + " ) ";
        valuesOrder.addElement(new TextElement(vls));
        insertElement.addElement(valuesOrder);
        return insertElement;
    }


    /**
     * 创建条件区域
     * @param fuzzy
     * @return
     */
    protected XmlElement generateCondition(boolean fuzzy) {
        XmlElement queryCondition = new XmlElement("sql");
        if (fuzzy) {
            queryCondition.addAttribute(new Attribute("id", String.format(fuzzyQueryId,
                    introspectedTable.getFullyQualifiedTable().getIntrospectedTableName())));
        } else {
            queryCondition.addAttribute(new Attribute("id", String.format(normalQueryId,
                    introspectedTable.getFullyQualifiedTable().getIntrospectedTableName())));
        }
        XmlElement trimElement = generateTrim();
        //遍历varchar字段
        List<IntrospectedColumn> columns = introspectedTable.getBaseColumns();
        for (IntrospectedColumn column : columns) {
            if (column.getActualColumnName().equals("GMT_CREATE")) {
                XmlElement columnElement = new XmlElement("if");
                columnElement.addAttribute(new Attribute("test", String.format(testSqlVarchar, "startDate")));
                columnElement.addElement(new TextElement(String.format(fuzzyStartDateColumnSql, column.getActualColumnName(),
                        "startDate")));
                trimElement.addElement(columnElement);
                columnElement = new XmlElement("if");
                columnElement.addAttribute(new Attribute("test", String.format(testSqlVarchar, "endDate")));
                columnElement.addElement(new TextElement(String.format(fuzzyEndDateColumnSql, column.getActualColumnName(),
                        "endDate")));
                trimElement.addElement(columnElement);
            } else {
                XmlElement columnElement = new XmlElement("if");
                if (column.getJdbcType() == Types.VARCHAR) {
                    //                System.out.println(column.getActualColumnName() + " jdbc type is "+column.getJdbcType()+",generate testSqlVarchar.");
                    columnElement.addAttribute(new Attribute("test", String.format(testSqlVarchar, column.getJavaProperty())));
                } else {
                    //                System.out.println(column.getActualColumnName() + " jdbc type is "+column.getJdbcType());
                    columnElement.addAttribute(new Attribute("test", String.format(testSql, column.getJavaProperty())));
                }
                //            System.out.println(column.getActualColumnName() + " columnElement:"+columnElement.getFormattedContent(0));
                if (column.getJdbcType() == Types.VARCHAR && fuzzy) {

                    columnElement.addElement(new TextElement(String.format(fuzzyColumnSql, column.getActualColumnName(),
                            column.getJavaProperty())));
                } else {
                    columnElement.addElement(new TextElement(String.format(normalColumnSql, column.getActualColumnName(), column.getJavaProperty())));
                }
                //            System.out.println(column.getActualColumnName() + " columnElement:"+columnElement.getFormattedContent(0));
                trimElement.addElement(columnElement);
            }
        }
        queryCondition.addElement(trimElement);
        return queryCondition;
    }

    /**
     * 创建根据Code查询的SQL语句
     * @return
     */
    protected XmlElement generateSelectByCode() {
        XmlElement selectElement = new XmlElement("select");
        selectElement.addAttribute(new Attribute("id", "getByCode"));
        if (introspectedTable.getBLOBColumns() != null && !introspectedTable.getBLOBColumns().isEmpty()) {
            selectElement.addAttribute(new Attribute("resultMap", "ResultMapWithBLOBs"));
        } else {
            selectElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));
        }
        //select
        TextElement selectText = new TextElement("select ");
        selectElement.addElement(selectText);
        //columnList
        XmlElement columnListElement = new XmlElement("include");
        columnListElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        selectElement.addElement(columnListElement);
        if (introspectedTable.getBLOBColumns() != null && !introspectedTable.getBLOBColumns().isEmpty()) {
            selectElement.addElement(new TextElement(","));
            XmlElement bcolumnListElement = new XmlElement("include");
            bcolumnListElement.addAttribute(new Attribute("refid", "Blob_Column_List"));
            selectElement.addElement(bcolumnListElement);
        }
        //form table
        TextElement formText = new TextElement(" from " + introspectedTable.getFullyQualifiedTable().getIntrospectedTableName() + " ");
        selectElement.addElement(formText);
        //where
        TextElement whereText = new TextElement(" where  " + introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN) + " = #{" + getColumn(introspectedTable.getTableConfigurationProperty(PluginProperty.DOMAIN_CODECOLUMN)) + ",jdbcType=VARCHAR} ");
        selectElement.addElement(whereText);

//        XmlElement settestFuzzy = new XmlElement("if");
//        settestFuzzy.addAttribute(new Attribute("test", "tenantCode != null and tenantCode.trim().length() != 0"));
//        TextElement setifText = new TextElement(" and TENANT_CODE = #{tenantCode,jdbcType=VARCHAR} ");
//        settestFuzzy.addElement(setifText);
//        selectElement.addElement(settestFuzzy);

        return selectElement;
    }

    public String getColumn(String tableName) {
        String[] tabs = tableName.split("_");
        String codeColumn = "";
        for (int i = 0; i < tabs.length; i++) {
            tabs[i] = toUpperCaseFirstOne(tabs[i].toLowerCase());
            codeColumn += tabs[i];
        }
        return toLowerCaseFirstOne(codeColumn);
    }

    //首字母转大写
    public String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    //首字母转小写
    public String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    private XmlElement generateTrim() {
        XmlElement ret = new XmlElement("trim");
        ret.addAttribute(new Attribute("prefix", "WHERE"));
        ret.addAttribute(new Attribute("prefixOverrides", "AND |OR "));
        return ret;
    }
}
