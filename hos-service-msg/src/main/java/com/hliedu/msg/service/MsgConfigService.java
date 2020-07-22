

package com.hliedu.msg.service;

import com.hliedu.msg.domain.MsgConfig;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 消息配置信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MsgConfigService extends BaseService{

	/**
     * 消息配置信息新增
     *
     * @param msgConfig
     * @return
     */
	public String saveConfig(MsgConfig msgConfig) throws Exception;

	/**
     * 消息配置信息状态更新
	 *
     * @param configId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateConfigState(Integer configId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 消息配置信息修改
	 *
     * @param msgConfig POJO
     * @return
     */
	public boolean updateConfig(MsgConfig msgConfig) throws Exception;

	/**
     * 根据ID获取消息配置信息
	 *
     * @param configId id
     * @return
     */
	public MsgConfig getConfig(Integer configId) throws Exception;

	/**
     * 根据ID删除消息配置信息
	 *
     * @param configId id
     * @return
     */
	public boolean deleteConfig(Integer configId) throws Exception;

	/**
     * 消息配置信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MsgConfig> queryConfigPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取消息配置信息
	 *
     * @param map
     * @return
     */
	public MsgConfig getConfigByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除消息配置信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delConfigByCode(Map<String, Object> map) throws Exception;
}
