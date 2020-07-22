

package com.hliedu.bs.service;

import com.hliedu.bs.domain.BsCity;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 城市信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface BsCityService extends BaseService{

	/**
     * 地区信息描述新增
     *
     * @param bsCity
     * @return
     */
	public String saveCity(BsCity bsCity) throws Exception;

	/**
     * 地区信息描述状态更新
	 *
     * @param cityId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateCityState(Integer cityId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 地区信息描述修改
	 *
     * @param bsCity POJO
     * @return
     */
	public boolean updateCity(BsCity bsCity) throws Exception;

	/**
     * 根据ID获取地区信息描述
	 *
     * @param cityId id
     * @return
     */
	public BsCity getCity(Integer cityId) throws Exception;

	/**
     * 根据ID删除地区信息描述
	 *
     * @param cityId id
     * @return
     */
	public boolean deleteCity(Integer cityId) throws Exception;

	/**
     * 地区信息描述分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<BsCity> queryCityPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取地区信息描述
	 *
     * @param map
     * @return
     */
	public BsCity getCityByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除地区信息描述ByCode
	 *
     * @param map
     * @return
     */
	public boolean delCityByCode(Map<String, Object> map) throws Exception;

	/**
	 *
	 */
	public void cacheCityByProvince();

}
