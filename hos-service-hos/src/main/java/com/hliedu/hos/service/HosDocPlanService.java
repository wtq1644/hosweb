

package com.hliedu.hos.service;

import com.hliedu.hos.domain.HosDocPlan;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 医师排期信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface HosDocPlanService extends BaseService{

	/**
     * 医师排期信息新增
     *
     * @param hosDocPlan
     * @return
     */
	public String savePlan(HosDocPlan hosDocPlan) throws Exception;

	/**
     * 医师排期信息状态更新
	 *
     * @param planId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updatePlanState(Integer planId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 医师排期信息修改
	 *
     * @param hosDocPlan POJO
     * @return
     */
	public boolean updatePlan(HosDocPlan hosDocPlan) throws Exception;

	/**
     * 根据ID获取医师排期信息
	 *
     * @param planId id
     * @return
     */
	public HosDocPlan getPlan(Integer planId) throws Exception;

	/**
     * 根据ID删除医师排期信息
	 *
     * @param planId id
     * @return
     */
	public boolean deletePlan(Integer planId) throws Exception;

	/**
     * 医师排期信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<HosDocPlan> queryPlanPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取医师排期信息
	 *
     * @param map
     * @return
     */
	public HosDocPlan getPlanByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除医师排期信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delPlanByCode(Map<String, Object> map) throws Exception;
}
