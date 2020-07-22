package com.hliedu.bs.service;

import com.hliedu.bs.domain.BsArea;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 地区信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface BsAreaService extends BaseService{

	/**
     * 地区信息描述新增
     *
     * @param bsArea
     * @return
     */
	public String saveArea(BsArea bsArea) throws Exception;

	/**
     * 地区信息描述状态更新
	 *
     * @param areaId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateAreaState(Integer areaId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 地区信息描述修改
	 *
     * @param bsArea POJO
     * @return
     */
	public boolean updateArea(BsArea bsArea) throws Exception;

	/**
     * 根据ID获取地区信息描述
	 *
     * @param areaId id
     * @return
     */
	public BsArea getArea(Integer areaId) throws Exception;

	/**
     * 根据ID删除地区信息描述
	 *
     * @param areaId id
     * @return
     */
	public boolean deleteArea(Integer areaId) throws Exception;

	/**
     * 地区信息描述分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<BsArea> queryAreaPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取地区信息描述
	 *
     * @param map
     * @return
     */
	public BsArea getAreaByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除地区信息描述ByCode
	 *
     * @param map
     * @return
     */
	public boolean delAreaByCode(Map<String, Object> map) throws Exception;

	/**
	 * 缓存所有的区，根据城市
	 */
	public void cacheAreaByCity();
}
