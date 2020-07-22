

package com.hliedu.sys.service;

import com.hliedu.sys.domain.SysDd;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 数据字典配置
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface SysDdService extends BaseService{

	/**
     * 数据字典配置新增
     *
     * @param sysDd
     * @return
     */
	public String saveDd(SysDd sysDd) throws Exception;

	/**
     * 数据字典配置状态更新
	 *
     * @param ddId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateDdState(Integer ddId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 数据字典配置修改
	 *
     * @param sysDd POJO
     * @return
     */
	public boolean updateDd(SysDd sysDd) throws Exception;

	/**
     * 根据ID获取数据字典配置
	 *
     * @param ddId id
     * @return
     */
	public SysDd getDd(Integer ddId) throws Exception;

	/**
     * 根据ID删除数据字典配置
	 *
     * @param ddId id
     * @return
     */
	public boolean deleteDd(Integer ddId) throws Exception;

	/**
     * 数据字典配置分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<SysDd> queryDdPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取数据字典配置
	 *
     * @param map
     * @return
     */
	public SysDd getDdByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除数据字典配置ByCode
	 *
     * @param map
     * @return
     */
	public boolean delDdByCode(Map<String, Object> map) throws Exception;
}
