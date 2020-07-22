package com.hliedu.hos.service.impl;

import com.hliedu.hos.mapper.HosDocPlanMapper;
import com.hliedu.hos.domain.HosDocPlan;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.hos.service.HosDocPlanService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HosDocPlanServiceImpl  extends BaseServiceImpl implements HosDocPlanService{

	public static final String SYS_CODE="HosDocPlanServiceImpl.";

	@Autowired
	private HosDocPlanMapper hosDocPlanMapper;

	@Override
	public String savePlan(HosDocPlan hosDocPlan) throws Exception{
		//1.检测
		String msg=checkPlan(hosDocPlan);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setPlanDefault(hosDocPlan);
		//3.保存
		savePlanModel(hosDocPlan);
		return hosDocPlan.getPlanCode();
	}

	@Override
	public boolean updatePlanState(Integer planId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStatePlanModel(planId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updatePlan(HosDocPlan hosDocPlan) throws Exception {
		//1.检测
		String msg=checkPlan(hosDocPlan);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		HosDocPlan oldHosDocPlan=getPlanModelById(hosDocPlan.getPlanId());
		if(null == oldHosDocPlan){
			throw new Exception("数据为空");
		}

		//3.默认值
		setPlanUpdataDefault(hosDocPlan);
		//4.保存
		updatePlanModel(hosDocPlan);
		return true;
	}

	@Override
	public HosDocPlan getPlan(Integer planId) throws Exception{
		return getPlanModelById(planId);
	}

	@Override
	public boolean deletePlan(Integer planId) throws Exception {
		int i = deletePlanModel(planId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<HosDocPlan> queryPlanPage(Map<String, Object> map) throws Exception{
		List<HosDocPlan> hosDocPlanList = queryPlanModelPage(map);
		QueryResult<HosDocPlan> queryResult = new QueryResult<HosDocPlan>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countPlan(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(hosDocPlanList);
		return queryResult;
	}
	
	@Override
	public HosDocPlan getPlanByCode(Map<String, Object> map) throws Exception{
		return getPlanModelByCode(map);
	}

	@Override
	public boolean delPlanByCode(Map<String, Object> map) throws Exception {
		int i = delPlanModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测医师排期信息参数
	 * @param hosDocPlan
	 * @return
	 */
	private String checkPlan(HosDocPlan hosDocPlan){
		if(null==hosDocPlan){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(hosDocPlan.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(hosDocPlan.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置医师排期信息新增默认值
	 * @param hosDocPlan
	 */
	private void setPlanDefault(HosDocPlan hosDocPlan){
		if(null == hosDocPlan)return;
		if(null == hosDocPlan.getDataState())hosDocPlan.setDataState(0);
		if(null == hosDocPlan.getGmtCreate())hosDocPlan.setGmtCreate(new Date());
	hosDocPlan.setGmtModified(new Date());
		if(StringUtils.isBlank(hosDocPlan.getPlanCode())){
	hosDocPlan.setPlanCode(createUUIDString());
		}
	}

	/**
	 * 设置医师排期信息修改默认值
	 * @param hosDocPlan
	 */
	private void setPlanUpdataDefault(HosDocPlan hosDocPlan){
		if(null==hosDocPlan)return;
	hosDocPlan.setGmtModified(new Date());
	}

	/**
	 * 保存医师排期信息对象
	 * @param hosDocPlan
	 */
	private int savePlanModel(HosDocPlan hosDocPlan){
		if(null==hosDocPlan)return 0;
		return hosDocPlanMapper.insert(hosDocPlan);
	}

	/**
	 * 获取医师排期信息信息
	 * @param planId
	 * @return HosDocPlan
	 */
	private HosDocPlan getPlanModelById(Integer planId){
		if(null==planId)return null;
		return hosDocPlanMapper.selectByPrimaryKey(planId);
	}

	/**
	 * 获取医师排期信息信息
	 * @param map<planCode>
	 * @return HosDocPlan
	 */
	private HosDocPlan getPlanModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return hosDocPlanMapper.getByCode(map);
	}


	/**
	 * 删除医师排期信息信息
	 * @param map<planCode>
	 */
	private int delPlanModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return hosDocPlanMapper.delByCode(map);
	}


	/**
	 * 删除医师排期信息信息
	 * @param planId
	 */
	private int deletePlanModel(Integer planId){
		if(null==planId)return 0;
		return hosDocPlanMapper.deleteByPrimaryKey(planId);
	}

	/**
	 * 更新医师排期信息对象
	 * @param hosDocPlan
	 */
	private int updatePlanModel(HosDocPlan hosDocPlan){
		if(null==hosDocPlan)return 0;
		return hosDocPlanMapper.updateByPrimaryKeySelective(hosDocPlan);
	}

	/**
	 * 更新医师排期信息状态
	 * @param planId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStatePlanModel(Integer planId, Integer dataState, Integer oldDataState){
		if(null==planId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("planId", planId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return hosDocPlanMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询医师排期信息
	 * @param paramMap
	 * @return HosDocPlan
	 */
	private List<HosDocPlan> queryPlanModelPage(Map<String, Object> paramMap) {
		try{
			return hosDocPlanMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryPlanModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countPlan(Map<String, Object> map) {
		int i = 0;
		try {
			i = hosDocPlanMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countPlan", e);
		}
		return i;
	}

}
