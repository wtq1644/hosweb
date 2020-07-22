

package com.hliedu.hos.service;

import com.hliedu.hos.domain.HosDept;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;

import java.util.List;
import java.util.Map;

/**
 * 科室信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface HosDeptService extends BaseService{

	/**
     * 科室信息新增
     *
     * @param hosDept
     * @return
     */
	public String saveDept(HosDept hosDept) throws Exception;

	/**
     * 科室信息状态更新
	 *
     * @param deptId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateDeptState(Integer deptId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 科室信息修改
	 *
     * @param hosDept POJO
     * @return
     */
	public boolean updateDept(HosDept hosDept) throws Exception;

	/**
     * 根据ID获取科室信息
	 *
     * @param deptId id
     * @return
     */
	public HosDept getDept(Integer deptId) throws Exception;

	/**
     * 根据ID删除科室信息
	 *
     * @param deptId id
     * @return
     */
	public boolean deleteDept(Integer deptId) throws Exception;

	/**
     * 科室信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<HosDept> queryDeptPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取科室信息
	 *
     * @param map
     * @return
     */
	public HosDept getDeptByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除科室信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delDeptByCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据医馆ID查询下面的所有科室
	 * @param hosId
	 * @return
	 */
	List<HosDept> queryDeptByHosId(Integer hosId);
}
