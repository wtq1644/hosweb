package com.hliedu.hos.service.impl;

import com.hliedu.hos.mapper.HosDeptMapper;
import com.hliedu.hos.domain.HosDept;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.hos.service.HosDeptService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HosDeptServiceImpl  extends BaseServiceImpl implements HosDeptService{

	public static final String SYS_CODE="HosDeptServiceImpl.";

	@Autowired
	private HosDeptMapper hosDeptMapper;

	@Override
	public String saveDept(HosDept hosDept) throws Exception{
		//1.检测
		String msg=checkDept(hosDept);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setDeptDefault(hosDept);
		//3.保存
		saveDeptModel(hosDept);
		return hosDept.getDeptCode();
	}

	@Override
	public boolean updateDeptState(Integer deptId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateDeptModel(deptId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateDept(HosDept hosDept) throws Exception {
		//1.检测
		String msg=checkDept(hosDept);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		HosDept oldHosDept=getDeptModelById(hosDept.getDeptId());
		if(null == oldHosDept){
			throw new Exception("数据为空");
		}

		//3.默认值
		setDeptUpdataDefault(hosDept);
		//4.保存
		updateDeptModel(hosDept);
		return true;
	}

	@Override
	public HosDept getDept(Integer deptId) throws Exception{
		return getDeptModelById(deptId);
	}

	@Override
	public boolean deleteDept(Integer deptId) throws Exception {
		int i = deleteDeptModel(deptId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<HosDept> queryDeptPage(Map<String, Object> map) throws Exception{
		List<HosDept> hosDeptList = queryDeptModelPage(map);
		QueryResult<HosDept> queryResult = new QueryResult<HosDept>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countDept(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(hosDeptList);
		return queryResult;
	}
	
	@Override
	public HosDept getDeptByCode(Map<String, Object> map) throws Exception{
		return getDeptModelByCode(map);
	}

	@Override
	public boolean delDeptByCode(Map<String, Object> map) throws Exception {
		int i = delDeptModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public List<HosDept> queryDeptByHosId(Integer hosId) {
		if (hosId == null) {
			return null;
		}
		return queryDeptByHosIdModel(hosId);
	}

	public List<HosDept> queryDeptByHosIdModel(Integer hosId) {
		return hosDeptMapper.queryDeptByHosIdModel(hosId);
	}

	/**
	 * 检测科室信息参数
	 * @param hosDept
	 * @return
	 */
	private String checkDept(HosDept hosDept){
		if(null==hosDept){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(hosDept.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(hosDept.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置科室信息新增默认值
	 * @param hosDept
	 */
	private void setDeptDefault(HosDept hosDept){
		if(null == hosDept)return;
		if(null == hosDept.getDataState())hosDept.setDataState(0);
		if(null == hosDept.getGmtCreate())hosDept.setGmtCreate(new Date());
	hosDept.setGmtModified(new Date());
		if(StringUtils.isBlank(hosDept.getDeptCode())){
	hosDept.setDeptCode(createUUIDString());
		}
	}

	/**
	 * 设置科室信息修改默认值
	 * @param hosDept
	 */
	private void setDeptUpdataDefault(HosDept hosDept){
		if(null==hosDept)return;
	hosDept.setGmtModified(new Date());
	}

	/**
	 * 保存科室信息对象
	 * @param hosDept
	 */
	private int saveDeptModel(HosDept hosDept){
		if(null==hosDept)return 0;
		return hosDeptMapper.insert(hosDept);
	}

	/**
	 * 获取科室信息信息
	 * @param deptId
	 * @return HosDept
	 */
	private HosDept getDeptModelById(Integer deptId){
		if(null==deptId)return null;
		return hosDeptMapper.selectByPrimaryKey(deptId);
	}

	/**
	 * 获取科室信息信息
	 * @param map<deptCode>
	 * @return HosDept
	 */
	private HosDept getDeptModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return hosDeptMapper.getByCode(map);
	}


	/**
	 * 删除科室信息信息
	 * @param map<deptCode>
	 */
	private int delDeptModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return hosDeptMapper.delByCode(map);
	}


	/**
	 * 删除科室信息信息
	 * @param deptId
	 */
	private int deleteDeptModel(Integer deptId){
		if(null==deptId)return 0;
		return hosDeptMapper.deleteByPrimaryKey(deptId);
	}

	/**
	 * 更新科室信息对象
	 * @param hosDept
	 */
	private int updateDeptModel(HosDept hosDept){
		if(null==hosDept)return 0;
		return hosDeptMapper.updateByPrimaryKeySelective(hosDept);
	}

	/**
	 * 更新科室信息状态
	 * @param deptId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateDeptModel(Integer deptId, Integer dataState, Integer oldDataState){
		if(null==deptId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("deptId", deptId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return hosDeptMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询科室信息
	 * @param paramMap
	 * @return HosDept
	 */
	private List<HosDept> queryDeptModelPage(Map<String, Object> paramMap) {
		try{
			return hosDeptMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryDeptModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countDept(Map<String, Object> map) {
		int i = 0;
		try {
			i = hosDeptMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countDept", e);
		}
		return i;
	}

}
