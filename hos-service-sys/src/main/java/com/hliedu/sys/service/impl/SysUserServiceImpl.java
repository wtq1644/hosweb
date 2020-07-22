package com.hliedu.sys.service.impl;

import com.hliedu.sys.mapper.SysUserMapper;
import com.hliedu.sys.domain.SysUser;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.sys.service.SysUserService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl  extends BaseServiceImpl implements SysUserService{

	public static final String SYS_CODE="SysUserServiceImpl.";

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public String saveUser(SysUser sysUser) throws Exception{
		//1.检测
		String msg=checkUser(sysUser);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setUserDefault(sysUser);
		//3.保存
		saveUserModel(sysUser);
		return sysUser.getUserCode();
	}

	@Override
	public boolean updateUserState(Integer userId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateUserModel(userId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateUser(SysUser sysUser) throws Exception {
		//1.检测
		String msg=checkUser(sysUser);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		SysUser oldSysUser=getUserModelById(sysUser.getUserId());
		if(null == oldSysUser){
			throw new Exception("数据为空");
		}

		//3.默认值
		setUserUpdataDefault(sysUser);
		//4.保存
		updateUserModel(sysUser);
		return true;
	}

	@Override
	public SysUser getUser(Integer userId) throws Exception{
		return getUserModelById(userId);
	}

	@Override
	public boolean deleteUser(Integer userId) throws Exception {
		int i = deleteUserModel(userId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<SysUser> queryUserPage(Map<String, Object> map) throws Exception{
		List<SysUser> sysUserList = queryUserModelPage(map);
		QueryResult<SysUser> queryResult = new QueryResult<SysUser>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countUser(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(sysUserList);
		return queryResult;
	}
	
	@Override
	public SysUser getUserByCode(Map<String, Object> map) throws Exception{
		return getUserModelByCode(map);
	}

	@Override
	public boolean delUserByCode(Map<String, Object> map) throws Exception {
		int i = delUserModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测后台用户信息参数
	 * @param sysUser
	 * @return
	 */
	private String checkUser(SysUser sysUser){
		if(null==sysUser){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(sysUser.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(sysUser.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置后台用户信息新增默认值
	 * @param sysUser
	 */
	private void setUserDefault(SysUser sysUser){
		if(null == sysUser)return;
		if(null == sysUser.getDataState())sysUser.setDataState(0);
		if(null == sysUser.getGmtCreate())sysUser.setGmtCreate(new Date());
	sysUser.setGmtModified(new Date());
		if(StringUtils.isBlank(sysUser.getUserCode())){
	sysUser.setUserCode(createUUIDString());
		}
	}

	/**
	 * 设置后台用户信息修改默认值
	 * @param sysUser
	 */
	private void setUserUpdataDefault(SysUser sysUser){
		if(null==sysUser)return;
	sysUser.setGmtModified(new Date());
	}

	/**
	 * 保存后台用户信息对象
	 * @param sysUser
	 */
	private int saveUserModel(SysUser sysUser){
		if(null==sysUser)return 0;
		return sysUserMapper.insert(sysUser);
	}

	/**
	 * 获取后台用户信息信息
	 * @param userId
	 * @return SysUser
	 */
	private SysUser getUserModelById(Integer userId){
		if(null==userId)return null;
		return sysUserMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 获取后台用户信息信息
	 * @param map<userCode>
	 * @return SysUser
	 */
	private SysUser getUserModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return sysUserMapper.getByCode(map);
	}


	/**
	 * 删除后台用户信息信息
	 * @param map<userCode>
	 */
	private int delUserModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return sysUserMapper.delByCode(map);
	}


	/**
	 * 删除后台用户信息信息
	 * @param userId
	 */
	private int deleteUserModel(Integer userId){
		if(null==userId)return 0;
		return sysUserMapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 更新后台用户信息对象
	 * @param sysUser
	 */
	private int updateUserModel(SysUser sysUser){
		if(null==sysUser)return 0;
		return sysUserMapper.updateByPrimaryKeySelective(sysUser);
	}

	/**
	 * 更新后台用户信息状态
	 * @param userId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateUserModel(Integer userId, Integer dataState, Integer oldDataState){
		if(null==userId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return sysUserMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询后台用户信息
	 * @param paramMap
	 * @return SysUser
	 */
	private List<SysUser> queryUserModelPage(Map<String, Object> paramMap) {
		try{
			return sysUserMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryUserModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countUser(Map<String, Object> map) {
		int i = 0;
		try {
			i = sysUserMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countUser", e);
		}
		return i;
	}

}
