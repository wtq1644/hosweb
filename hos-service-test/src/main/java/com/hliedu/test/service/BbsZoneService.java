

package com.hliedu.test.service;

import com.hliedu.test.domain.BbsZone;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 空间信息表描述
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface BbsZoneService extends BaseService{

	/**
     * 空间信息表描述新增
     *
     * @param bbsZone
     * @return
     */
	public String saveZone(BbsZone bbsZone) throws Exception;

	/**
     * 空间信息表描述状态更新
	 *
     * @param zoneId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateZoneState(Integer zoneId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 空间信息表描述修改
	 *
     * @param bbsZone POJO
     * @return
     */
	public boolean updateZone(BbsZone bbsZone) throws Exception;

	/**
     * 根据ID获取空间信息表描述
	 *
     * @param zoneId id
     * @return
     */
	public BbsZone getZone(Integer zoneId) throws Exception;

	/**
     * 根据ID删除空间信息表描述
	 *
     * @param zoneId id
     * @return
     */
	public boolean deleteZone(Integer zoneId) throws Exception;

	/**
     * 空间信息表描述分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<BbsZone> queryZonePage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取空间信息表描述
	 *
     * @param map
     * @return
     */
	public BbsZone getZoneByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除空间信息表描述ByCode
	 *
     * @param map
     * @return
     */
	public boolean delZoneByCode(Map<String, Object> map) throws Exception;
}
