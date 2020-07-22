

package com.hliedu.msg.service;

import com.hliedu.msg.domain.MsgMnsEmail;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 短信邮箱记录
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MsgMnsEmailService extends BaseService{

	/**
     * 短信邮箱记录新增
     *
     * @param msgMnsEmail
     * @return
     */
	public String saveMnsEmail(MsgMnsEmail msgMnsEmail) throws Exception;

	/**
     * 短信邮箱记录状态更新
	 *
     * @param msgId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateMnsEmailState(Integer msgId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 短信邮箱记录修改
	 *
     * @param msgMnsEmail POJO
     * @return
     */
	public boolean updateMnsEmail(MsgMnsEmail msgMnsEmail) throws Exception;

	/**
     * 根据ID获取短信邮箱记录
	 *
     * @param msgId id
     * @return
     */
	public MsgMnsEmail getMnsEmail(Integer msgId) throws Exception;

	/**
     * 根据ID删除短信邮箱记录
	 *
     * @param msgId id
     * @return
     */
	public boolean deleteMnsEmail(Integer msgId) throws Exception;

	/**
     * 短信邮箱记录分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MsgMnsEmail> queryMnsEmailPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取短信邮箱记录
	 *
     * @param map
     * @return
     */
	public MsgMnsEmail getMnsEmailByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除短信邮箱记录ByCode
	 *
     * @param map
     * @return
     */
	public boolean delMnsEmailByCode(Map<String, Object> map) throws Exception;
}
