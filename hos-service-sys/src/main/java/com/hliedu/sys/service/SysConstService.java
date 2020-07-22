

package com.hliedu.sys.service;

import com.hliedu.sys.domain.SysConst;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 系统常量配置
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface SysConstService extends BaseService{

	/**
     * 系统常量配置新增
     *
     * @param sysConst
     * @return
     */
	public String saveConst(SysConst sysConst) throws Exception;

	/**
     * 系统常量配置状态更新
	 *
     * @param constId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateConstState(Integer constId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 系统常量配置修改
	 *
     * @param sysConst POJO
     * @return
     */
	public boolean updateConst(SysConst sysConst) throws Exception;

	/**
     * 根据ID获取系统常量配置
	 *
     * @param constId id
     * @return
     */
	public SysConst getConst(Integer constId) throws Exception;

	/**
     * 根据ID删除系统常量配置
	 *
     * @param constId id
     * @return
     */
	public boolean deleteConst(Integer constId) throws Exception;

	/**
     * 系统常量配置分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<SysConst> queryConstPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取系统常量配置
	 *
     * @param map
     * @return
     */
	public SysConst getConstByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除系统常量配置ByCode
	 *
     * @param map
     * @return
     */
	public boolean delConstByCode(Map<String, Object> map) throws Exception;
}
