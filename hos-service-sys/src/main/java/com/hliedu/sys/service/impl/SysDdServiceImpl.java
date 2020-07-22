package com.hliedu.sys.service.impl;

import com.hliedu.sys.mapper.SysDdMapper;
import com.hliedu.sys.domain.SysDd;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.sys.service.SysDdService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDdServiceImpl  extends BaseServiceImpl implements SysDdService{

	public static final String SYS_CODE="SysDdServiceImpl.";

	@Autowired
	private SysDdMapper sysDdMapper;

	@Override
	public String saveDd(SysDd sysDd) throws Exception{
		//1.检测
		String msg=checkDd(sysDd);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setDdDefault(sysDd);
		//3.保存
		saveDdModel(sysDd);
		return sysDd.getDdCode();
	}

	@Override
	public boolean updateDdState(Integer ddId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateDdModel(ddId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateDd(SysDd sysDd) throws Exception {
		//1.检测
		String msg=checkDd(sysDd);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		SysDd oldSysDd=getDdModelById(sysDd.getDdId());
		if(null == oldSysDd){
			throw new Exception("数据为空");
		}

		//3.默认值
		setDdUpdataDefault(sysDd);
		//4.保存
		updateDdModel(sysDd);
		return true;
	}

	@Override
	public SysDd getDd(Integer ddId) throws Exception{
		return getDdModelById(ddId);
	}

	@Override
	public boolean deleteDd(Integer ddId) throws Exception {
		int i = deleteDdModel(ddId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<SysDd> queryDdPage(Map<String, Object> map) throws Exception{
		List<SysDd> sysDdList = queryDdModelPage(map);
		QueryResult<SysDd> queryResult = new QueryResult<SysDd>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countDd(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(sysDdList);
		return queryResult;
	}
	
	@Override
	public SysDd getDdByCode(Map<String, Object> map) throws Exception{
		return getDdModelByCode(map);
	}

	@Override
	public boolean delDdByCode(Map<String, Object> map) throws Exception {
		int i = delDdModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测数据字典配置参数
	 * @param sysDd
	 * @return
	 */
	private String checkDd(SysDd sysDd){
		if(null==sysDd){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(sysDd.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(sysDd.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置数据字典配置新增默认值
	 * @param sysDd
	 */
	private void setDdDefault(SysDd sysDd){
		if(null == sysDd)return;
		if(null == sysDd.getDataState())sysDd.setDataState(0);
		if(null == sysDd.getGmtCreate())sysDd.setGmtCreate(new Date());
	sysDd.setGmtModified(new Date());
		if(StringUtils.isBlank(sysDd.getDdCode())){
	sysDd.setDdCode(createUUIDString());
		}
	}

	/**
	 * 设置数据字典配置修改默认值
	 * @param sysDd
	 */
	private void setDdUpdataDefault(SysDd sysDd){
		if(null==sysDd)return;
	sysDd.setGmtModified(new Date());
	}

	/**
	 * 保存数据字典配置对象
	 * @param sysDd
	 */
	private int saveDdModel(SysDd sysDd){
		if(null==sysDd)return 0;
		return sysDdMapper.insert(sysDd);
	}

	/**
	 * 获取数据字典配置信息
	 * @param ddId
	 * @return SysDd
	 */
	private SysDd getDdModelById(Integer ddId){
		if(null==ddId)return null;
		return sysDdMapper.selectByPrimaryKey(ddId);
	}

	/**
	 * 获取数据字典配置信息
	 * @param map<ddCode>
	 * @return SysDd
	 */
	private SysDd getDdModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return sysDdMapper.getByCode(map);
	}


	/**
	 * 删除数据字典配置信息
	 * @param map<ddCode>
	 */
	private int delDdModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return sysDdMapper.delByCode(map);
	}


	/**
	 * 删除数据字典配置信息
	 * @param ddId
	 */
	private int deleteDdModel(Integer ddId){
		if(null==ddId)return 0;
		return sysDdMapper.deleteByPrimaryKey(ddId);
	}

	/**
	 * 更新数据字典配置对象
	 * @param sysDd
	 */
	private int updateDdModel(SysDd sysDd){
		if(null==sysDd)return 0;
		return sysDdMapper.updateByPrimaryKeySelective(sysDd);
	}

	/**
	 * 更新数据字典配置状态
	 * @param ddId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateDdModel(Integer ddId, Integer dataState, Integer oldDataState){
		if(null==ddId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("ddId", ddId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return sysDdMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询数据字典配置
	 * @param paramMap
	 * @return SysDd
	 */
	private List<SysDd> queryDdModelPage(Map<String, Object> paramMap) {
		try{
			return sysDdMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryDdModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countDd(Map<String, Object> map) {
		int i = 0;
		try {
			i = sysDdMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countDd", e);
		}
		return i;
	}

}
