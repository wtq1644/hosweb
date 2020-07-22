package com.hliedu.hos.service.impl;

import com.github.pagehelper.PageInfo;
import com.hliedu.hos.domain.HosHospital;
import com.hliedu.hos.mapper.HosDoctorMapper;
import com.hliedu.hos.domain.HosDoctor;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.hos.service.HosDoctorService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HosDoctorServiceImpl  extends BaseServiceImpl implements HosDoctorService{

	public static final String SYS_CODE="HosDoctorServiceImpl.";

	@Autowired
	private HosDoctorMapper hosDoctorMapper;

	@Override
	public String saveDoctor(HosDoctor hosDoctor) throws Exception{
		//1.检测
		String msg=checkDoctor(hosDoctor);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setDoctorDefault(hosDoctor);
		//3.保存
		saveDoctorModel(hosDoctor);
		return hosDoctor.getDocCode();
	}

	@Override
	public boolean updateDoctorState(Integer docId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateDoctorModel(docId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateDoctor(HosDoctor hosDoctor) throws Exception {
		//1.检测
		String msg=checkDoctor(hosDoctor);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		HosDoctor oldHosDoctor=getDoctorModelById(hosDoctor.getDocId());
		if(null == oldHosDoctor){
			throw new Exception("数据为空");
		}

		//3.默认值
		setDoctorUpdataDefault(hosDoctor);
		//4.保存
		updateDoctorModel(hosDoctor);
		return true;
	}

	@Override
	public HosDoctor getDoctor(Integer docId) throws Exception{
		return getDoctorModelById(docId);
	}

	@Override
	public boolean deleteDoctor(Integer docId) throws Exception {
		int i = deleteDoctorModel(docId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<HosDoctor> queryDoctorPage(Map<String, Object> map) throws Exception{
		startPage(map);
		List<HosDoctor> hosDoctorList = queryDoctorModelPage(map);
		PageInfo<HosDoctor> pageResult = getPageInfo(hosDoctorList);

		QueryResult<HosDoctor> queryResult = new QueryResult<HosDoctor>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount((int)pageResult.getTotal());

		pageTools.setPageCount(pageResult.getPages());
		pageTools.setPageSize(pageResult.getPageSize());
		pageTools.setPageNo(pageResult.getPageNum());

		queryResult.setPageTools(pageTools);
		queryResult.setList(hosDoctorList);
		return queryResult;
	}
	
	@Override
	public HosDoctor getDoctorByCode(Map<String, Object> map) throws Exception{
		return getDoctorModelByCode(map);
	}

	@Override
	public boolean delDoctorByCode(Map<String, Object> map) throws Exception {
		int i = delDoctorModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<Map<String,Object>> queryDoctorByDeptAndPlanPage(Map<String,Object> map){
		startPage(map);
		List<Map<String, Object>> list = queryDoctorByDeptAndPlanModel(map);
		PageInfo<Map<String, Object>> pageResult = getPageInfo(list);

		QueryResult<Map<String, Object>> queryResult = new QueryResult<>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount((int)pageResult.getTotal());

		pageTools.setPageCount(pageResult.getPages());
		pageTools.setPageSize(pageResult.getPageSize());
		pageTools.setPageNo(pageResult.getPageNum());

		queryResult.setPageTools(pageTools);
		queryResult.setList(list);
		return queryResult;
	}


	private List<Map<String,Object>> queryDoctorByDeptAndPlanModel(Map<String,Object> map){
		return hosDoctorMapper.queryDoctorByDeptAndPlan(map);
	}


	/**
	 * 检测医师信息参数
	 * @param hosDoctor
	 * @return
	 */
	private String checkDoctor(HosDoctor hosDoctor){
		if(null==hosDoctor){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(hosDoctor.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(hosDoctor.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置医师信息新增默认值
	 * @param hosDoctor
	 */
	private void setDoctorDefault(HosDoctor hosDoctor){
		if(null == hosDoctor)return;
		if(null == hosDoctor.getDataState())hosDoctor.setDataState(0);
		if(null == hosDoctor.getGmtCreate())hosDoctor.setGmtCreate(new Date());
	hosDoctor.setGmtModified(new Date());
		if(StringUtils.isBlank(hosDoctor.getDocCode())){
	hosDoctor.setDocCode(createUUIDString());
		}
	}

	/**
	 * 设置医师信息修改默认值
	 * @param hosDoctor
	 */
	private void setDoctorUpdataDefault(HosDoctor hosDoctor){
		if(null==hosDoctor)return;
	hosDoctor.setGmtModified(new Date());
	}

	/**
	 * 保存医师信息对象
	 * @param hosDoctor
	 */
	private int saveDoctorModel(HosDoctor hosDoctor){
		if(null==hosDoctor)return 0;
		return hosDoctorMapper.insert(hosDoctor);
	}

	/**
	 * 获取医师信息信息
	 * @param docId
	 * @return HosDoctor
	 */
	private HosDoctor getDoctorModelById(Integer docId){
		if(null==docId)return null;
		return hosDoctorMapper.selectByPrimaryKey(docId);
	}

	/**
	 * 获取医师信息信息
	 * @param map<docCode>
	 * @return HosDoctor
	 */
	private HosDoctor getDoctorModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return hosDoctorMapper.getByCode(map);
	}


	/**
	 * 删除医师信息信息
	 * @param map<docCode>
	 */
	private int delDoctorModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return hosDoctorMapper.delByCode(map);
	}


	/**
	 * 删除医师信息信息
	 * @param docId
	 */
	private int deleteDoctorModel(Integer docId){
		if(null==docId)return 0;
		return hosDoctorMapper.deleteByPrimaryKey(docId);
	}

	/**
	 * 更新医师信息对象
	 * @param hosDoctor
	 */
	private int updateDoctorModel(HosDoctor hosDoctor){
		if(null==hosDoctor)return 0;
		return hosDoctorMapper.updateByPrimaryKeySelective(hosDoctor);
	}

	/**
	 * 更新医师信息状态
	 * @param docId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateDoctorModel(Integer docId, Integer dataState, Integer oldDataState){
		if(null==docId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("docId", docId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return hosDoctorMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询医师信息
	 * @param paramMap
	 * @return HosDoctor
	 */
	private List<HosDoctor> queryDoctorModelPage(Map<String, Object> paramMap) {
		try{
			return hosDoctorMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryDoctorModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countDoctor(Map<String, Object> map) {
		int i = 0;
		try {
			i = hosDoctorMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countDoctor", e);
		}
		return i;
	}

}
