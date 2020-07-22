package com.hliedu.msg.service.impl;

import com.hliedu.msg.mapper.MsgMnsEmailMapper;
import com.hliedu.msg.domain.MsgMnsEmail;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.msg.service.MsgMnsEmailService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgMnsEmailServiceImpl  extends BaseServiceImpl implements MsgMnsEmailService{

	public static final String SYS_CODE="MsgMnsEmailServiceImpl.";

	@Autowired
	private MsgMnsEmailMapper msgMnsEmailMapper;

	@Override
	public String saveMnsEmail(MsgMnsEmail msgMnsEmail) throws Exception{
		//1.检测
		String msg=checkMnsEmail(msgMnsEmail);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setMnsEmailDefault(msgMnsEmail);
		//3.保存
		saveMnsEmailModel(msgMnsEmail);
		return msgMnsEmail.getMsgCode();
	}

	@Override
	public boolean updateMnsEmailState(Integer msgId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateMnsEmailModel(msgId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateMnsEmail(MsgMnsEmail msgMnsEmail) throws Exception {
		//1.检测
		String msg=checkMnsEmail(msgMnsEmail);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MsgMnsEmail oldMsgMnsEmail=getMnsEmailModelById(msgMnsEmail.getMsgId());
		if(null == oldMsgMnsEmail){
			throw new Exception("数据为空");
		}

		//3.默认值
		setMnsEmailUpdataDefault(msgMnsEmail);
		//4.保存
		updateMnsEmailModel(msgMnsEmail);
		return true;
	}

	@Override
	public MsgMnsEmail getMnsEmail(Integer msgId) throws Exception{
		return getMnsEmailModelById(msgId);
	}

	@Override
	public boolean deleteMnsEmail(Integer msgId) throws Exception {
		int i = deleteMnsEmailModel(msgId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MsgMnsEmail> queryMnsEmailPage(Map<String, Object> map) throws Exception{
		List<MsgMnsEmail> msgMnsEmailList = queryMnsEmailModelPage(map);
		QueryResult<MsgMnsEmail> queryResult = new QueryResult<MsgMnsEmail>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countMnsEmail(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(msgMnsEmailList);
		return queryResult;
	}
	
	@Override
	public MsgMnsEmail getMnsEmailByCode(Map<String, Object> map) throws Exception{
		return getMnsEmailModelByCode(map);
	}

	@Override
	public boolean delMnsEmailByCode(Map<String, Object> map) throws Exception {
		int i = delMnsEmailModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测短信邮箱记录参数
	 * @param msgMnsEmail
	 * @return
	 */
	private String checkMnsEmail(MsgMnsEmail msgMnsEmail){
		if(null==msgMnsEmail){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(msgMnsEmail.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(msgMnsEmail.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置短信邮箱记录新增默认值
	 * @param msgMnsEmail
	 */
	private void setMnsEmailDefault(MsgMnsEmail msgMnsEmail){
		if(null == msgMnsEmail)return;
		if(null == msgMnsEmail.getDataState())msgMnsEmail.setDataState(0);
		if(null == msgMnsEmail.getGmtCreate())msgMnsEmail.setGmtCreate(new Date());
	msgMnsEmail.setGmtModified(new Date());
		if(StringUtils.isBlank(msgMnsEmail.getMsgCode())){
	msgMnsEmail.setMsgCode(createUUIDString());
		}
	}

	/**
	 * 设置短信邮箱记录修改默认值
	 * @param msgMnsEmail
	 */
	private void setMnsEmailUpdataDefault(MsgMnsEmail msgMnsEmail){
		if(null==msgMnsEmail)return;
	msgMnsEmail.setGmtModified(new Date());
	}

	/**
	 * 保存短信邮箱记录对象
	 * @param msgMnsEmail
	 */
	private int saveMnsEmailModel(MsgMnsEmail msgMnsEmail){
		if(null==msgMnsEmail)return 0;
		return msgMnsEmailMapper.insert(msgMnsEmail);
	}

	/**
	 * 获取短信邮箱记录信息
	 * @param msgId
	 * @return MsgMnsEmail
	 */
	private MsgMnsEmail getMnsEmailModelById(Integer msgId){
		if(null==msgId)return null;
		return msgMnsEmailMapper.selectByPrimaryKey(msgId);
	}

	/**
	 * 获取短信邮箱记录信息
	 * @param map<msgCode>
	 * @return MsgMnsEmail
	 */
	private MsgMnsEmail getMnsEmailModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return msgMnsEmailMapper.getByCode(map);
	}


	/**
	 * 删除短信邮箱记录信息
	 * @param map<msgCode>
	 */
	private int delMnsEmailModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return msgMnsEmailMapper.delByCode(map);
	}


	/**
	 * 删除短信邮箱记录信息
	 * @param msgId
	 */
	private int deleteMnsEmailModel(Integer msgId){
		if(null==msgId)return 0;
		return msgMnsEmailMapper.deleteByPrimaryKey(msgId);
	}

	/**
	 * 更新短信邮箱记录对象
	 * @param msgMnsEmail
	 */
	private int updateMnsEmailModel(MsgMnsEmail msgMnsEmail){
		if(null==msgMnsEmail)return 0;
		return msgMnsEmailMapper.updateByPrimaryKeySelective(msgMnsEmail);
	}

	/**
	 * 更新短信邮箱记录状态
	 * @param msgId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateMnsEmailModel(Integer msgId, Integer dataState, Integer oldDataState){
		if(null==msgId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("msgId", msgId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return msgMnsEmailMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询短信邮箱记录
	 * @param paramMap
	 * @return MsgMnsEmail
	 */
	private List<MsgMnsEmail> queryMnsEmailModelPage(Map<String, Object> paramMap) {
		try{
			return msgMnsEmailMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryMnsEmailModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countMnsEmail(Map<String, Object> map) {
		int i = 0;
		try {
			i = msgMnsEmailMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countMnsEmail", e);
		}
		return i;
	}

}
