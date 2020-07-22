

package com.hliedu.mm.service;

import com.hliedu.mm.domain.MmMerber;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 会员用户信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface MmMerberService extends BaseService{

	/**
     * 会员用户信息新增
     *
     * @param mmMerber
     * @return
     */
	public String saveMerber(MmMerber mmMerber) throws Exception;

	/**
     * 会员用户信息状态更新
	 *
     * @param merberId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateMerberState(Integer merberId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 会员用户信息修改
	 *
     * @param mmMerber POJO
     * @return
     */
	public boolean updateMerber(MmMerber mmMerber) throws Exception;

	/**
     * 根据ID获取会员用户信息
	 *
     * @param merberId id
     * @return
     */
	public MmMerber getMerber(Integer merberId) throws Exception;

	/**
     * 根据ID删除会员用户信息
	 *
     * @param merberId id
     * @return
     */
	public boolean deleteMerber(Integer merberId) throws Exception;

	/**
     * 会员用户信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<MmMerber> queryMerberPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取会员用户信息
	 *
     * @param map
     * @return
     */
	public MmMerber getMerberByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除会员用户信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delMerberByCode(Map<String, Object> map) throws Exception;
}
