

package com.hliedu.hos.service;

import com.hliedu.hos.domain.HosDoctor;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * 医师信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface HosDoctorService extends BaseService{

	/**
     * 医师信息新增
     *
     * @param hosDoctor
     * @return
     */
	public String saveDoctor(HosDoctor hosDoctor) throws Exception;

	/**
     * 医师信息状态更新
	 *
     * @param docId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateDoctorState(Integer docId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 医师信息修改
	 *
     * @param hosDoctor POJO
     * @return
     */
	public boolean updateDoctor(HosDoctor hosDoctor) throws Exception;

	/**
     * 根据ID获取医师信息
	 *
     * @param docId id
     * @return
     */
	public HosDoctor getDoctor(Integer docId) throws Exception;

	/**
     * 根据ID删除医师信息
	 *
     * @param docId id
     * @return
     */
	public boolean deleteDoctor(Integer docId) throws Exception;

	/**
     * 医师信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<HosDoctor> queryDoctorPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取医师信息
	 *
     * @param map
     * @return
     */
	public HosDoctor getDoctorByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除医师信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delDoctorByCode(Map<String, Object> map) throws Exception;


	/**
	 * 根据排期、部门编号查询医师信息
	 * @param map
	 * @return
	 */
	QueryResult<Map<String,Object>> queryDoctorByDeptAndPlanPage(Map<String,Object> map);
}
