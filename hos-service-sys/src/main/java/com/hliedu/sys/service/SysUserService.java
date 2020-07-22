

package com.hliedu.sys.service;

import com.hliedu.sys.domain.SysUser;
import com.hliedu.mybatis.service.BaseService;
import com.hliedu.mybatis.page.QueryResult;
import java.util.Map;

/**
 * 后台用户信息
 *
 * 带你轻松学Java：恒骊学堂
 * www.hliedu.com
 * QQ群：827553720
 */
public interface SysUserService extends BaseService{

	/**
     * 后台用户信息新增
     *
     * @param sysUser
     * @return
     */
	public String saveUser(SysUser sysUser) throws Exception;

	/**
     * 后台用户信息状态更新
	 *
     * @param userId id
     * @param dataState 新状态
     * @param oldDataState  旧状态
     * @return
     */
	public boolean updateUserState(Integer userId, Integer dataState, Integer oldDataState) throws Exception;

	/**
     * 后台用户信息修改
	 *
     * @param sysUser POJO
     * @return
     */
	public boolean updateUser(SysUser sysUser) throws Exception;

	/**
     * 根据ID获取后台用户信息
	 *
     * @param userId id
     * @return
     */
	public SysUser getUser(Integer userId) throws Exception;

	/**
     * 根据ID删除后台用户信息
	 *
     * @param userId id
     * @return
     */
	public boolean deleteUser(Integer userId) throws Exception;

	/**
     * 后台用户信息分页查询
	 *
     * @param map
     * @return
     */
	public QueryResult<SysUser> queryUserPage(Map<String, Object> map) throws Exception;

	/**
     * 根据code获取后台用户信息
	 *
     * @param map
     * @return
     */
	public SysUser getUserByCode(Map<String, Object> map) throws Exception;

	/**
     * 根据code删除后台用户信息ByCode
	 *
     * @param map
     * @return
     */
	public boolean delUserByCode(Map<String, Object> map) throws Exception;
}
