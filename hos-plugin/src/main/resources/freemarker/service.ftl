<#assign serviceConfig={}>

<#if data??>
	<#if data.serviceConfig??>
			<#assign serviceConfig=data.serviceConfig>
	</#if>
</#if>

<#if serviceConfig.servicePkg??>
package ${serviceConfig.servicePkg}.service;
</#if>

<#if serviceConfig.importsilist??>
    <#list serviceConfig.importsilist as importClass>
        <#if importClass??>
import ${importClass};
        </#if>
    </#list>
</#if>

/**
 * ${serviceConfig.description}
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface ${serviceConfig.interfaceStr} extends BaseService{
<#list serviceConfig.proList as pro>

	/**
     * ${pro.mapperDesc}新增
     *
     * @param ${pro.lname}
     * @return
     */
	public String save${pro.nick}(${pro.modelName} ${pro.lname}) throws Exception;

	/**
     * ${pro.mapperDesc}状态更新
	 *
     * @param ${pro.idName} id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean update${pro.nick}State(Integer ${pro.idName}, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * ${pro.mapperDesc}修改
	 *
     * @param ${pro.lname} POJO
     * @return
     */
	public boolean update${pro.nick}(${pro.modelName} ${pro.lname}) throws Exception;

	/**
     * 根据ID获取${pro.mapperDesc}
	 *
     * @param ${pro.idName} id
     * @return
     */
	public ${pro.modelName} get${pro.nick}(Integer ${pro.idName}) throws Exception;

	/**
     * 根据ID删除${pro.mapperDesc}
	 *
     * @param ${pro.idName} id
     * @return
     */
	public boolean delete${pro.nick}(Integer ${pro.idName}) throws Exception;

	/**
     * ${pro.mapperDesc}分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<${pro.modelName}> query${pro.nick}Page(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取${pro.mapperDesc}
	 *
     * @param map
     * @return
     */
	public ${pro.modelName} get${pro.nick}ByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除${pro.mapperDesc}ByCode
	 *
     * @param map
     * @return
     */
	public boolean del${pro.nick}ByCode(Map<String, Object> map) throws Exception;
</#list>
}
