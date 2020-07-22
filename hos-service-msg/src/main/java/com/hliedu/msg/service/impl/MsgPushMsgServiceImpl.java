package com.hliedu.msg.service.impl;

import com.hliedu.msg.mapper.MsgPushMsgMapper;
import com.hliedu.msg.domain.MsgPushMsg;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.msg.service.MsgPushMsgService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsgPushMsgServiceImpl  extends BaseServiceImpl implements MsgPushMsgService{

	public static final String SYS_CODE="MsgPushMsgServiceImpl.";

	@Autowired
	private MsgPushMsgMapper msgPushMsgMapper;

	@Override
	public String savePushMsg(MsgPushMsg msgPushMsg) throws Exception{
		//1.检测
		String msg=checkPushMsg(msgPushMsg);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setPushMsgDefault(msgPushMsg);
		//3.保存
		savePushMsgModel(msgPushMsg);
		return msgPushMsg.getMsgCode();
	}

	@Override
	public boolean updatePushMsgState(Integer msgId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStatePushMsgModel(msgId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updatePushMsg(MsgPushMsg msgPushMsg) throws Exception {
		//1.检测
		String msg=checkPushMsg(msgPushMsg);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MsgPushMsg oldMsgPushMsg=getPushMsgModelById(msgPushMsg.getMsgId());
		if(null == oldMsgPushMsg){
			throw new Exception("数据为空");
		}

		//3.默认值
		setPushMsgUpdataDefault(msgPushMsg);
		//4.保存
		updatePushMsgModel(msgPushMsg);
		return true;
	}

	@Override
	public MsgPushMsg getPushMsg(Integer msgId) throws Exception{
		return getPushMsgModelById(msgId);
	}

	@Override
	public boolean deletePushMsg(Integer msgId) throws Exception {
		int i = deletePushMsgModel(msgId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MsgPushMsg> queryPushMsgPage(Map<String, Object> map) throws Exception{
		List<MsgPushMsg> msgPushMsgList = queryPushMsgModelPage(map);
		QueryResult<MsgPushMsg> queryResult = new QueryResult<MsgPushMsg>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countPushMsg(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(msgPushMsgList);
		return queryResult;
	}
	
	@Override
	public MsgPushMsg getPushMsgByCode(Map<String, Object> map) throws Exception{
		return getPushMsgModelByCode(map);
	}

	@Override
	public boolean delPushMsgByCode(Map<String, Object> map) throws Exception {
		int i = delPushMsgModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测站内信消息参数
	 * @param msgPushMsg
	 * @return
	 */
	private String checkPushMsg(MsgPushMsg msgPushMsg){
		if(null==msgPushMsg){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(msgPushMsg.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(msgPushMsg.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置站内信消息新增默认值
	 * @param msgPushMsg
	 */
	private void setPushMsgDefault(MsgPushMsg msgPushMsg){
		if(null == msgPushMsg)return;
		if(null == msgPushMsg.getDataState())msgPushMsg.setDataState(0);
		if(null == msgPushMsg.getGmtCreate())msgPushMsg.setGmtCreate(new Date());
	msgPushMsg.setGmtModified(new Date());
		if(StringUtils.isBlank(msgPushMsg.getMsgCode())){
	msgPushMsg.setMsgCode(createUUIDString());
		}
	}

	/**
	 * 设置站内信消息修改默认值
	 * @param msgPushMsg
	 */
	private void setPushMsgUpdataDefault(MsgPushMsg msgPushMsg){
		if(null==msgPushMsg)return;
	msgPushMsg.setGmtModified(new Date());
	}

	/**
	 * 保存站内信消息对象
	 * @param msgPushMsg
	 */
	private int savePushMsgModel(MsgPushMsg msgPushMsg){
		if(null==msgPushMsg)return 0;
		return msgPushMsgMapper.insert(msgPushMsg);
	}

	/**
	 * 获取站内信消息信息
	 * @param msgId
	 * @return MsgPushMsg
	 */
	private MsgPushMsg getPushMsgModelById(Integer msgId){
		if(null==msgId)return null;
		return msgPushMsgMapper.selectByPrimaryKey(msgId);
	}

	/**
	 * 获取站内信消息信息
	 * @param map<msgCode>
	 * @return MsgPushMsg
	 */
	private MsgPushMsg getPushMsgModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return msgPushMsgMapper.getByCode(map);
	}


	/**
	 * 删除站内信消息信息
	 * @param map<msgCode>
	 */
	private int delPushMsgModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return msgPushMsgMapper.delByCode(map);
	}


	/**
	 * 删除站内信消息信息
	 * @param msgId
	 */
	private int deletePushMsgModel(Integer msgId){
		if(null==msgId)return 0;
		return msgPushMsgMapper.deleteByPrimaryKey(msgId);
	}

	/**
	 * 更新站内信消息对象
	 * @param msgPushMsg
	 */
	private int updatePushMsgModel(MsgPushMsg msgPushMsg){
		if(null==msgPushMsg)return 0;
		return msgPushMsgMapper.updateByPrimaryKeySelective(msgPushMsg);
	}

	/**
	 * 更新站内信消息状态
	 * @param msgId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStatePushMsgModel(Integer msgId, Integer dataState, Integer oldDataState){
		if(null==msgId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("msgId", msgId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return msgPushMsgMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询站内信消息
	 * @param paramMap
	 * @return MsgPushMsg
	 */
	private List<MsgPushMsg> queryPushMsgModelPage(Map<String, Object> paramMap) {
		try{
			return msgPushMsgMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryPushMsgModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countPushMsg(Map<String, Object> map) {
		int i = 0;
		try {
			i = msgPushMsgMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countPushMsg", e);
		}
		return i;
	}

}
