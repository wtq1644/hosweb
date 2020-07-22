<#assign serviceConfig={}>
<#if data??>
	<#if data.serviceConfig??>
			<#assign serviceConfig=data.serviceConfig>
	</#if>
</#if>
<#if serviceConfig.servicePkg??>
package ${serviceConfig.servicePkg}.service.impl;
</#if>

<#if serviceConfig.importslist??>
<#list serviceConfig.importslist as importClass>
<#if importClass??>
import ${importClass};
</#if>
</#list>
</#if>

@Service
public class ${serviceConfig.interfaceStr}Impl  extends BaseServiceImpl <#if serviceConfig.interfaceStr??>implements ${serviceConfig.interfaceStr}</#if>{

	public static final String SYS_CODE="${serviceConfig.interfaceStr}Impl.";

<#list serviceConfig.proList as pro>
	@Autowired
	private ${pro.mapper} ${pro.ldao};
</#list>

<#list serviceConfig.proList as pro>
	@Override
	public String save${pro.nick}(${pro.modelName} ${pro.lname}) throws Exception{
		//1.检测
		String msg=check${pro.nick}(${pro.lname});
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		set${pro.nick}Default(${pro.lname});
		//3.保存
		save${pro.nick}Model(${pro.lname});
		return ${pro.lname}.get${pro.uCodeName}();
	}

	@Override
	public boolean update${pro.nick}State(Integer ${pro.idName}, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateState${pro.nick}Model(${pro.idName}, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean update${pro.nick}(${pro.modelName} ${pro.lname}) throws Exception {
		//1.检测
		String msg=check${pro.nick}(${pro.lname});
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		${pro.modelName} old${pro.modelName}=get${pro.nick}ModelById(${pro.lname}.${pro.getidName}());
		if(null == old${pro.modelName}){
			throw new Exception("数据为空");
		}

		//3.默认值
		set${pro.nick}UpdataDefault(${pro.lname});
		//4.保存
		update${pro.nick}Model(${pro.lname});
		return true;
	}

	@Override
	public ${pro.modelName} get${pro.nick}(Integer ${pro.idName}) throws Exception{
		return get${pro.nick}ModelById(${pro.idName});
	}

	@Override
	public boolean delete${pro.nick}(Integer ${pro.idName}) throws Exception {
		int i = delete${pro.nick}Model(${pro.idName});
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<${pro.modelName}> query${pro.nick}Page(Map<String, Object> map) throws Exception{
		List<${pro.modelName}> ${pro.lname}List = query${pro.nick}ModelPage(map);
		QueryResult<${pro.modelName}> queryResult = new QueryResult<${pro.modelName}>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(count${pro.nick}(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(${pro.lname}List);
		return queryResult;
	}
	
	@Override
	public ${pro.modelName} get${pro.nick}ByCode(Map<String, Object> map) throws Exception{
		return get${pro.nick}ModelByCode(map);
	}

	@Override
	public boolean del${pro.nick}ByCode(Map<String, Object> map) throws Exception {
		int i = del${pro.nick}ModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

</#list>


<#list serviceConfig.proList as pro>
	/**
	 * 检测${pro.mapperDesc}参数
	 * @param ${pro.lname}
	 * @return
	 */
	private String check${pro.nick}(${pro.modelName} ${pro.lname}){
		if(null==${pro.lname}){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(${pro.lname}.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(${pro.lname}.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置${pro.mapperDesc}新增默认值
	 * @param ${pro.lname}
	 */
	private void set${pro.nick}Default(${pro.modelName} ${pro.lname}){
		if(null == ${pro.lname})return;
		if(null == ${pro.lname}.getDataState())${pro.lname}.setDataState(0);
		if(null == ${pro.lname}.getGmtCreate())${pro.lname}.setGmtCreate(new Date());
	${pro.lname}.setGmtModified(new Date());
		if(StringUtils.isBlank(${pro.lname}.get${pro.uCodeName}())){
	${pro.lname}.set${pro.uCodeName}(createUUIDString());
		}
	}

	/**
	 * 设置${pro.mapperDesc}修改默认值
	 * @param ${pro.lname}
	 */
	private void set${pro.nick}UpdataDefault(${pro.modelName} ${pro.lname}){
		if(null==${pro.lname})return;
	${pro.lname}.setGmtModified(new Date());
	}

	/**
	 * 保存${pro.mapperDesc}对象
	 * @param ${pro.lname}
	 */
	private int save${pro.nick}Model(${pro.modelName} ${pro.lname}){
		if(null==${pro.lname})return 0;
		return ${pro.ldao}.insert(${pro.lname});
	}

	/**
	 * 获取${pro.mapperDesc}信息
	 * @param ${pro.idName}
	 * @return ${pro.modelName}
	 */
	private ${pro.modelName} get${pro.nick}ModelById(Integer ${pro.idName}){
		if(null==${pro.idName})return null;
		return ${pro.ldao}.selectByPrimaryKey(${pro.idName});
	}

	/**
	 * 获取${pro.mapperDesc}信息
	 * @param map<${pro.codeName}>
	 * @return ${pro.modelName}
	 */
	private ${pro.modelName} get${pro.nick}ModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return ${pro.ldao}.getByCode(map);
	}


	/**
	 * 删除${pro.mapperDesc}信息
	 * @param map<${pro.codeName}>
	 */
	private int del${pro.nick}ModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return ${pro.ldao}.delByCode(map);
	}


	/**
	 * 删除${pro.mapperDesc}信息
	 * @param ${pro.idName}
	 */
	private int delete${pro.nick}Model(Integer ${pro.idName}){
		if(null==${pro.idName})return 0;
		return ${pro.ldao}.deleteByPrimaryKey(${pro.idName});
	}

	/**
	 * 更新${pro.mapperDesc}对象
	 * @param ${pro.lname}
	 */
	private int update${pro.nick}Model(${pro.modelName} ${pro.lname}){
		if(null==${pro.lname})return 0;
		return ${pro.ldao}.updateByPrimaryKeySelective(${pro.lname});
	}

	/**
	 * 更新${pro.mapperDesc}状态
	 * @param ${pro.idName}
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateState${pro.nick}Model(Integer ${pro.idName}, Integer dataState, Integer oldDataState){
		if(null==${pro.idName}||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("${pro.idName}", ${pro.idName});
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return ${pro.ldao}.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询${pro.mapperDesc}
	 * @param paramMap
	 * @return ${pro.modelName}
	 */
	private List<${pro.modelName}> query${pro.nick}ModelPage(Map<String, Object> paramMap) {
		try{
			return ${pro.ldao}.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".query${pro.nick}Model", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int count${pro.nick}(Map<String, Object> map) {
		int i = 0;
		try {
			i = ${pro.ldao}.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".count${pro.nick}", e);
		}
		return i;
	}
</#list>

}
