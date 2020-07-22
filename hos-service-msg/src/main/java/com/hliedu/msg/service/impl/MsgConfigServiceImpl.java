package com.hliedu.msg.service.impl;

import com.hliedu.msg.mapper.MsgConfigMapper;
import com.hliedu.msg.domain.MsgConfig;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.msg.service.MsgConfigService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgConfigServiceImpl  extends BaseServiceImpl implements MsgConfigService{

	public static final String SYS_CODE="MsgConfigServiceImpl.";

	@Autowired
	private MsgConfigMapper msgConfigMapper;

	@Override
	public String saveConfig(MsgConfig msgConfig) throws Exception{
		//1.检测
		String msg=checkConfig(msgConfig);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setConfigDefault(msgConfig);
		//3.保存
		saveConfigModel(msgConfig);
		return msgConfig.getConfigCode();
	}

	@Override
	public boolean updateConfigState(Integer configId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateConfigModel(configId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateConfig(MsgConfig msgConfig) throws Exception {
		//1.检测
		String msg=checkConfig(msgConfig);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MsgConfig oldMsgConfig=getConfigModelById(msgConfig.getConfigId());
		if(null == oldMsgConfig){
			throw new Exception("数据为空");
		}

		//3.默认值
		setConfigUpdataDefault(msgConfig);
		//4.保存
		updateConfigModel(msgConfig);
		return true;
	}

	@Override
	public MsgConfig getConfig(Integer configId) throws Exception{
		return getConfigModelById(configId);
	}

	@Override
	public boolean deleteConfig(Integer configId) throws Exception {
		int i = deleteConfigModel(configId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MsgConfig> queryConfigPage(Map<String, Object> map) throws Exception{
		List<MsgConfig> msgConfigList = queryConfigModelPage(map);
		QueryResult<MsgConfig> queryResult = new QueryResult<MsgConfig>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countConfig(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(msgConfigList);
		return queryResult;
	}
	
	@Override
	public MsgConfig getConfigByCode(Map<String, Object> map) throws Exception{
		return getConfigModelByCode(map);
	}

	@Override
	public boolean delConfigByCode(Map<String, Object> map) throws Exception {
		int i = delConfigModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测消息配置信息参数
	 * @param msgConfig
	 * @return
	 */
	private String checkConfig(MsgConfig msgConfig){
		if(null==msgConfig){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(msgConfig.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(msgConfig.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置消息配置信息新增默认值
	 * @param msgConfig
	 */
	private void setConfigDefault(MsgConfig msgConfig){
		if(null == msgConfig)return;
		if(null == msgConfig.getDataState())msgConfig.setDataState(0);
		if(null == msgConfig.getGmtCreate())msgConfig.setGmtCreate(new Date());
	msgConfig.setGmtModified(new Date());
		if(StringUtils.isBlank(msgConfig.getConfigCode())){
	msgConfig.setConfigCode(createUUIDString());
		}
	}

	/**
	 * 设置消息配置信息修改默认值
	 * @param msgConfig
	 */
	private void setConfigUpdataDefault(MsgConfig msgConfig){
		if(null==msgConfig)return;
	msgConfig.setGmtModified(new Date());
	}

	/**
	 * 保存消息配置信息对象
	 * @param msgConfig
	 */
	private int saveConfigModel(MsgConfig msgConfig){
		if(null==msgConfig)return 0;
		return msgConfigMapper.insert(msgConfig);
	}

	/**
	 * 获取消息配置信息信息
	 * @param configId
	 * @return MsgConfig
	 */
	private MsgConfig getConfigModelById(Integer configId){
		if(null==configId)return null;
		return msgConfigMapper.selectByPrimaryKey(configId);
	}

	/**
	 * 获取消息配置信息信息
	 * @param map<configCode>
	 * @return MsgConfig
	 */
	private MsgConfig getConfigModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return msgConfigMapper.getByCode(map);
	}


	/**
	 * 删除消息配置信息信息
	 * @param map<configCode>
	 */
	private int delConfigModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return msgConfigMapper.delByCode(map);
	}


	/**
	 * 删除消息配置信息信息
	 * @param configId
	 */
	private int deleteConfigModel(Integer configId){
		if(null==configId)return 0;
		return msgConfigMapper.deleteByPrimaryKey(configId);
	}

	/**
	 * 更新消息配置信息对象
	 * @param msgConfig
	 */
	private int updateConfigModel(MsgConfig msgConfig){
		if(null==msgConfig)return 0;
		return msgConfigMapper.updateByPrimaryKeySelective(msgConfig);
	}

	/**
	 * 更新消息配置信息状态
	 * @param configId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateConfigModel(Integer configId, Integer dataState, Integer oldDataState){
		if(null==configId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("configId", configId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return msgConfigMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询消息配置信息
	 * @param paramMap
	 * @return MsgConfig
	 */
	private List<MsgConfig> queryConfigModelPage(Map<String, Object> paramMap) {
		try{
			return msgConfigMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryConfigModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countConfig(Map<String, Object> map) {
		int i = 0;
		try {
			i = msgConfigMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countConfig", e);
		}
		return i;
	}

}
