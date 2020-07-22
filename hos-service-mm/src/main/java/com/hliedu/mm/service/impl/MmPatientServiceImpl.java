package com.hliedu.mm.service.impl;

import com.hliedu.mm.mapper.MmPatientMapper;
import com.hliedu.mm.domain.MmPatient;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.mm.service.MmPatientService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MmPatientServiceImpl  extends BaseServiceImpl implements MmPatientService{

	public static final String SYS_CODE="MmPatientServiceImpl.";

	@Autowired
	private MmPatientMapper mmPatientMapper;

	@Override
	public String savePatient(MmPatient mmPatient) throws Exception{
		//1.检测
		String msg=checkPatient(mmPatient);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setPatientDefault(mmPatient);
		//3.保存
		savePatientModel(mmPatient);
		return mmPatient.getPatientCode();
	}

	@Override
	public boolean updatePatientState(Integer patientId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStatePatientModel(patientId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updatePatient(MmPatient mmPatient) throws Exception {
		//1.检测
		String msg=checkPatient(mmPatient);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MmPatient oldMmPatient=getPatientModelById(mmPatient.getPatientId());
		if(null == oldMmPatient){
			throw new Exception("数据为空");
		}

		//3.默认值
		setPatientUpdataDefault(mmPatient);
		//4.保存
		updatePatientModel(mmPatient);
		return true;
	}

	@Override
	public MmPatient getPatient(Integer patientId) throws Exception{
		return getPatientModelById(patientId);
	}

	@Override
	public boolean deletePatient(Integer patientId) throws Exception {
		int i = deletePatientModel(patientId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MmPatient> queryPatientPage(Map<String, Object> map) throws Exception{
		List<MmPatient> mmPatientList = queryPatientModelPage(map);
		QueryResult<MmPatient> queryResult = new QueryResult<MmPatient>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countPatient(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(mmPatientList);
		return queryResult;
	}
	
	@Override
	public MmPatient getPatientByCode(Map<String, Object> map) throws Exception{
		return getPatientModelByCode(map);
	}

	@Override
	public boolean delPatientByCode(Map<String, Object> map) throws Exception {
		int i = delPatientModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测预约客户信息参数
	 * @param mmPatient
	 * @return
	 */
	private String checkPatient(MmPatient mmPatient){
		if(null==mmPatient){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(mmPatient.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(mmPatient.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置预约客户信息新增默认值
	 * @param mmPatient
	 */
	private void setPatientDefault(MmPatient mmPatient){
		if(null == mmPatient)return;
		if(null == mmPatient.getDataState())mmPatient.setDataState(0);
		if(null == mmPatient.getGmtCreate())mmPatient.setGmtCreate(new Date());
	mmPatient.setGmtModified(new Date());
		if(StringUtils.isBlank(mmPatient.getPatientCode())){
	mmPatient.setPatientCode(createUUIDString());
		}
	}

	/**
	 * 设置预约客户信息修改默认值
	 * @param mmPatient
	 */
	private void setPatientUpdataDefault(MmPatient mmPatient){
		if(null==mmPatient)return;
	mmPatient.setGmtModified(new Date());
	}

	/**
	 * 保存预约客户信息对象
	 * @param mmPatient
	 */
	private int savePatientModel(MmPatient mmPatient){
		if(null==mmPatient)return 0;
		return mmPatientMapper.insert(mmPatient);
	}

	/**
	 * 获取预约客户信息信息
	 * @param patientId
	 * @return MmPatient
	 */
	private MmPatient getPatientModelById(Integer patientId){
		if(null==patientId)return null;
		return mmPatientMapper.selectByPrimaryKey(patientId);
	}

	/**
	 * 获取预约客户信息信息
	 * @param map<patientCode>
	 * @return MmPatient
	 */
	private MmPatient getPatientModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return mmPatientMapper.getByCode(map);
	}


	/**
	 * 删除预约客户信息信息
	 * @param map<patientCode>
	 */
	private int delPatientModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return mmPatientMapper.delByCode(map);
	}


	/**
	 * 删除预约客户信息信息
	 * @param patientId
	 */
	private int deletePatientModel(Integer patientId){
		if(null==patientId)return 0;
		return mmPatientMapper.deleteByPrimaryKey(patientId);
	}

	/**
	 * 更新预约客户信息对象
	 * @param mmPatient
	 */
	private int updatePatientModel(MmPatient mmPatient){
		if(null==mmPatient)return 0;
		return mmPatientMapper.updateByPrimaryKeySelective(mmPatient);
	}

	/**
	 * 更新预约客户信息状态
	 * @param patientId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStatePatientModel(Integer patientId, Integer dataState, Integer oldDataState){
		if(null==patientId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("patientId", patientId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return mmPatientMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询预约客户信息
	 * @param paramMap
	 * @return MmPatient
	 */
	private List<MmPatient> queryPatientModelPage(Map<String, Object> paramMap) {
		try{
			return mmPatientMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryPatientModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countPatient(Map<String, Object> map) {
		int i = 0;
		try {
			i = mmPatientMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countPatient", e);
		}
		return i;
	}

}
