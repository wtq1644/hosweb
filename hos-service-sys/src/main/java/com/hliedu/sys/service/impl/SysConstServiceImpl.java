package com.hliedu.sys.service.impl;

import com.hliedu.sys.mapper.SysConstMapper;
import com.hliedu.sys.domain.SysConst;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.sys.service.SysConstService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConstServiceImpl  extends BaseServiceImpl implements SysConstService{

	public static final String SYS_CODE="SysConstServiceImpl.";

	@Autowired
	private SysConstMapper sysConstMapper;

	@Override
	public String saveConst(SysConst sysConst) throws Exception{
		//1.检测
		String msg=checkConst(sysConst);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setConstDefault(sysConst);
		//3.保存
		saveConstModel(sysConst);
		return sysConst.getConstCode();
	}

	@Override
	public boolean updateConstState(Integer constId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateConstModel(constId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateConst(SysConst sysConst) throws Exception {
		//1.检测
		String msg=checkConst(sysConst);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		SysConst oldSysConst=getConstModelById(sysConst.getConstId());
		if(null == oldSysConst){
			throw new Exception("数据为空");
		}

		//3.默认值
		setConstUpdataDefault(sysConst);
		//4.保存
		updateConstModel(sysConst);
		return true;
	}

	@Override
	public SysConst getConst(Integer constId) throws Exception{
		return getConstModelById(constId);
	}

	@Override
	public boolean deleteConst(Integer constId) throws Exception {
		int i = deleteConstModel(constId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<SysConst> queryConstPage(Map<String, Object> map) throws Exception{
		List<SysConst> sysConstList = queryConstModelPage(map);
		QueryResult<SysConst> queryResult = new QueryResult<SysConst>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countConst(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(sysConstList);
		return queryResult;
	}
	
	@Override
	public SysConst getConstByCode(Map<String, Object> map) throws Exception{
		return getConstModelByCode(map);
	}

	@Override
	public boolean delConstByCode(Map<String, Object> map) throws Exception {
		int i = delConstModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测系统常量配置参数
	 * @param sysConst
	 * @return
	 */
	private String checkConst(SysConst sysConst){
		if(null==sysConst){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(sysConst.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(sysConst.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置系统常量配置新增默认值
	 * @param sysConst
	 */
	private void setConstDefault(SysConst sysConst){
		if(null == sysConst)return;
		if(null == sysConst.getDataState())sysConst.setDataState(0);
		if(null == sysConst.getGmtCreate())sysConst.setGmtCreate(new Date());
	sysConst.setGmtModified(new Date());
		if(StringUtils.isBlank(sysConst.getConstCode())){
	sysConst.setConstCode(createUUIDString());
		}
	}

	/**
	 * 设置系统常量配置修改默认值
	 * @param sysConst
	 */
	private void setConstUpdataDefault(SysConst sysConst){
		if(null==sysConst)return;
	sysConst.setGmtModified(new Date());
	}

	/**
	 * 保存系统常量配置对象
	 * @param sysConst
	 */
	private int saveConstModel(SysConst sysConst){
		if(null==sysConst)return 0;
		return sysConstMapper.insert(sysConst);
	}

	/**
	 * 获取系统常量配置信息
	 * @param constId
	 * @return SysConst
	 */
	private SysConst getConstModelById(Integer constId){
		if(null==constId)return null;
		return sysConstMapper.selectByPrimaryKey(constId);
	}

	/**
	 * 获取系统常量配置信息
	 * @param map<constCode>
	 * @return SysConst
	 */
	private SysConst getConstModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return sysConstMapper.getByCode(map);
	}


	/**
	 * 删除系统常量配置信息
	 * @param map<constCode>
	 */
	private int delConstModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return sysConstMapper.delByCode(map);
	}


	/**
	 * 删除系统常量配置信息
	 * @param constId
	 */
	private int deleteConstModel(Integer constId){
		if(null==constId)return 0;
		return sysConstMapper.deleteByPrimaryKey(constId);
	}

	/**
	 * 更新系统常量配置对象
	 * @param sysConst
	 */
	private int updateConstModel(SysConst sysConst){
		if(null==sysConst)return 0;
		return sysConstMapper.updateByPrimaryKeySelective(sysConst);
	}

	/**
	 * 更新系统常量配置状态
	 * @param constId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateConstModel(Integer constId, Integer dataState, Integer oldDataState){
		if(null==constId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("constId", constId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return sysConstMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询系统常量配置
	 * @param paramMap
	 * @return SysConst
	 */
	private List<SysConst> queryConstModelPage(Map<String, Object> paramMap) {
		try{
			return sysConstMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryConstModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countConst(Map<String, Object> map) {
		int i = 0;
		try {
			i = sysConstMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countConst", e);
		}
		return i;
	}

}
