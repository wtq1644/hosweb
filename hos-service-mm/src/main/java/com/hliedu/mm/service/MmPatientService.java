

package com.hliedu.mm.service;

import com.hliedu.mm.domain.MmPatient;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 预约客户信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MmPatientService extends BaseService{

	/**
     * 预约客户信息新增
     *
     * @param mmPatient
     * @return
     */
	public String savePatient(MmPatient mmPatient) throws Exception;

	/**
     * 预约客户信息状态更新
	 *
     * @param patientId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updatePatientState(Integer patientId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 预约客户信息修改
	 *
     * @param mmPatient POJO
     * @return
     */
	public boolean updatePatient(MmPatient mmPatient) throws Exception;

	/**
     * 根据ID获取预约客户信息
	 *
     * @param patientId id
     * @return
     */
	public MmPatient getPatient(Integer patientId) throws Exception;

	/**
     * 根据ID删除预约客户信息
	 *
     * @param patientId id
     * @return
     */
	public boolean deletePatient(Integer patientId) throws Exception;

	/**
     * 预约客户信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MmPatient> queryPatientPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取预约客户信息
	 *
     * @param map
     * @return
     */
	public MmPatient getPatientByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除预约客户信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delPatientByCode(Map<String, Object> map) throws Exception;
}
