

package com.hliedu.msg.service;

import com.hliedu.msg.domain.MsgPushMsg;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 站内信消息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MsgPushMsgService extends BaseService{

	/**
     * 站内信消息新增
     *
     * @param msgPushMsg
     * @return
     */
	public String savePushMsg(MsgPushMsg msgPushMsg) throws Exception;

	/**
     * 站内信消息状态更新
	 *
     * @param msgId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updatePushMsgState(Integer msgId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 站内信消息修改
	 *
     * @param msgPushMsg POJO
     * @return
     */
	public boolean updatePushMsg(MsgPushMsg msgPushMsg) throws Exception;

	/**
     * 根据ID获取站内信消息
	 *
     * @param msgId id
     * @return
     */
	public MsgPushMsg getPushMsg(Integer msgId) throws Exception;

	/**
     * 根据ID删除站内信消息
	 *
     * @param msgId id
     * @return
     */
	public boolean deletePushMsg(Integer msgId) throws Exception;

	/**
     * 站内信消息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MsgPushMsg> queryPushMsgPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取站内信消息
	 *
     * @param map
     * @return
     */
	public MsgPushMsg getPushMsgByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除站内信消息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delPushMsgByCode(Map<String, Object> map) throws Exception;
}
