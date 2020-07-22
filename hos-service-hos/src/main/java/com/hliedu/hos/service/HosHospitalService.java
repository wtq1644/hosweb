

package com.hliedu.hos.service;

import com.hliedu.hos.domain.HosHospital;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * 医馆信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface HosHospitalService extends BaseService{

	/**
     * 医馆信息新增
     *
     * @param hosHospital
     * @return
     */
	public Integer saveHospital(HosHospital hosHospital) throws Exception;

	/**
	 * 新增医馆和科室的关系
	 * @param refs
	 * @return
	 * @throws Exception
	 */
	public Boolean saveHosDeptRefBatch(List<Map<String,Object>> refs) throws Exception;

	/**
     * 医馆信息状态更新
	 *
     * @param hosId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateHospitalState(Integer hosId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 医馆信息修改
	 *
     * @param hosHospital POJO
     * @return
     */
	public boolean updateHospital(HosHospital hosHospital) throws Exception;

	/**
     * 根据ID获取医馆信息
	 *
     * @param hosId id
     * @return
     */
	public HosHospital getHospital(Integer hosId) throws Exception;

	/**
     * 根据ID删除医馆信息
	 *
     * @param hosId id
     * @return
     */
	public boolean deleteHospital(Integer hosId) throws Exception;

	/**
     * 医馆信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<HosHospital> queryHospitalPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取医馆信息
	 *
     * @param map
     * @return
     */
	public HosHospital getHospitalByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除医馆信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delHospitalByCode(Map<String, Object> map) throws Exception;
}
