

package com.hliedu.mm.service;

import com.hliedu.mm.domain.MmOrderInfo;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 预约单信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MmOrderInfoService extends BaseService{

	/**
     * 预约单信息新增
     *
     * @param mmOrderInfo
     * @return
     */
	public String saveOrderInfo(MmOrderInfo mmOrderInfo) throws Exception;

	/**
     * 预约单信息状态更新
	 *
     * @param orderId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateOrderInfoState(Integer orderId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 预约单信息修改
	 *
     * @param mmOrderInfo POJO
     * @return
     */
	public boolean updateOrderInfo(MmOrderInfo mmOrderInfo) throws Exception;

	/**
     * 根据ID获取预约单信息
	 *
     * @param orderId id
     * @return
     */
	public MmOrderInfo getOrderInfo(Integer orderId) throws Exception;

	/**
     * 根据ID删除预约单信息
	 *
     * @param orderId id
     * @return
     */
	public boolean deleteOrderInfo(Integer orderId) throws Exception;

	/**
     * 预约单信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MmOrderInfo> queryOrderInfoPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取预约单信息
	 *
     * @param map
     * @return
     */
	public MmOrderInfo getOrderInfoByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除预约单信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delOrderInfoByCode(Map<String, Object> map) throws Exception;
}
