

package com.hliedu.bs.service;

import com.hliedu.bs.domain.BsProvince;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 省份信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface BsProvinceService extends BaseService{

	/**
     * 省份信息描述新增
     *
     * @param bsProvince
     * @return
     */
	public String saveProvince(BsProvince bsProvince) throws Exception;

	/**
     * 省份信息描述状态更新
	 *
     * @param provinceId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateProvinceState(Integer provinceId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 省份信息描述修改
	 *
     * @param bsProvince POJO
     * @return
     */
	public boolean updateProvince(BsProvince bsProvince) throws Exception;

	/**
     * 根据ID获取省份信息描述
	 *
     * @param provinceId id
     * @return
     */
	public BsProvince getProvince(Integer provinceId) throws Exception;

	/**
     * 根据ID删除省份信息描述
	 *
     * @param provinceId id
     * @return
     */
	public boolean deleteProvince(Integer provinceId) throws Exception;

	/**
     * 省份信息描述分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<BsProvince> queryProvincePage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取省份信息描述
	 *
     * @param map
     * @return
     */
	public BsProvince getProvinceByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除省份信息描述ByCode
	 *
     * @param map
     * @return
     */
	public boolean delProvinceByCode(Map<String, Object> map) throws Exception;

	/**
	 * 省份信息缓存
	 */
	public void cacheProvince();
}
