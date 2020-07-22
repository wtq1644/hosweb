package com.hliedu.hos.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hliedu.hos.mapper.HosHospitalMapper;
import com.hliedu.hos.domain.HosHospital;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.hos.service.HosHospitalService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HosHospitalServiceImpl  extends BaseServiceImpl implements HosHospitalService{

	public static final String SYS_CODE="HosHospitalServiceImpl.";

	@Autowired
	private HosHospitalMapper hosHospitalMapper;

	@Override
	public Integer saveHospital(HosHospital hosHospital) throws Exception{
		//1.检测
		String msg=checkHospital(hosHospital);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setHospitalDefault(hosHospital);
		//3.保存
		saveHospitalModel(hosHospital);
		return hosHospital.getHosId();
	}

	@Override
	public Boolean saveHosDeptRefBatch(List<Map<String, Object>> refs) throws Exception {
        int index = hosHospitalMapper.insertHosDeptRefBatch(refs);
        if (index > 0) {
            return true;
        }
        return false;
	}

	@Override
	public boolean updateHospitalState(Integer hosId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateHospitalModel(hosId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateHospital(HosHospital hosHospital) throws Exception {
		//1.检测
		String msg=checkHospital(hosHospital);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		HosHospital oldHosHospital=getHospitalModelById(hosHospital.getHosId());
		if(null == oldHosHospital){
			throw new Exception("数据为空");
		}

		//3.默认值
		setHospitalUpdataDefault(hosHospital);
		//4.保存
		updateHospitalModel(hosHospital);
		return true;
	}

	@Override
	public HosHospital getHospital(Integer hosId) throws Exception{
		return getHospitalModelById(hosId);
	}

	@Override
	public boolean deleteHospital(Integer hosId) throws Exception {
		int i = deleteHospitalModel(hosId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<HosHospital> queryHospitalPage(Map<String, Object> map) throws Exception{
		//page 是指当前页 ，size 每页显示行数
		startPage(map);
		List<HosHospital> list = queryHospitalModelPage(map);
		PageInfo<HosHospital> pageResult = getPageInfo(list);

		QueryResult<HosHospital> queryResult = new QueryResult<HosHospital>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount((int)pageResult.getTotal());

		pageTools.setPageCount(pageResult.getPages());
		pageTools.setPageSize(pageResult.getPageSize());
		pageTools.setPageNo(pageResult.getPageNum());

		queryResult.setPageTools(pageTools);
		queryResult.setList(list);
		return queryResult;
	}
	
	@Override
	public HosHospital getHospitalByCode(Map<String, Object> map) throws Exception{
		return getHospitalModelByCode(map);
	}

	@Override
	public boolean delHospitalByCode(Map<String, Object> map) throws Exception {
		int i = delHospitalModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测医馆信息参数
	 * @param hosHospital
	 * @return
	 */
	private String checkHospital(HosHospital hosHospital){
		if(null==hosHospital){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(hosHospital.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(hosHospital.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置医馆信息新增默认值
	 * @param hosHospital
	 */
	private void setHospitalDefault(HosHospital hosHospital){
		if(null == hosHospital)return;
		if(null == hosHospital.getDataState())hosHospital.setDataState(0);
		if(null == hosHospital.getGmtCreate())hosHospital.setGmtCreate(new Date());
	hosHospital.setGmtModified(new Date());
		if(StringUtils.isBlank(hosHospital.getHosCode())){
	hosHospital.setHosCode(createUUIDString());
		}
	}

	/**
	 * 设置医馆信息修改默认值
	 * @param hosHospital
	 */
	private void setHospitalUpdataDefault(HosHospital hosHospital){
		if(null==hosHospital)return;
	hosHospital.setGmtModified(new Date());
	}

	/**
	 * 保存医馆信息对象
	 * @param hosHospital
	 */
	private int saveHospitalModel(HosHospital hosHospital){
		if(null==hosHospital)return 0;
		return hosHospitalMapper.insert(hosHospital);
	}

	/**
	 * 获取医馆信息信息
	 * @param hosId
	 * @return HosHospital
	 */
	private HosHospital getHospitalModelById(Integer hosId){
		if(null==hosId)return null;
		return hosHospitalMapper.selectByPrimaryKey(hosId);
	}

	/**
	 * 获取医馆信息信息
	 * @param map<hosCode>
	 * @return HosHospital
	 */
	private HosHospital getHospitalModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return hosHospitalMapper.getByCode(map);
	}


	/**
	 * 删除医馆信息信息
	 * @param map<hosCode>
	 */
	private int delHospitalModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return hosHospitalMapper.delByCode(map);
	}


	/**
	 * 删除医馆信息信息
	 * @param hosId
	 */
	private int deleteHospitalModel(Integer hosId){
		if(null==hosId)return 0;
		return hosHospitalMapper.deleteByPrimaryKey(hosId);
	}

	/**
	 * 更新医馆信息对象
	 * @param hosHospital
	 */
	private int updateHospitalModel(HosHospital hosHospital){
		if(null==hosHospital)return 0;
		return hosHospitalMapper.updateByPrimaryKeySelective(hosHospital);
	}

	/**
	 * 更新医馆信息状态
	 * @param hosId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateHospitalModel(Integer hosId, Integer dataState, Integer oldDataState){
		if(null==hosId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("hosId", hosId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return hosHospitalMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询医馆信息
	 * @param paramMap
	 * @return HosHospital
	 */
	private List<HosHospital> queryHospitalModelPage(Map<String, Object> paramMap) {
		try{
			return hosHospitalMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryHospitalModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countHospital(Map<String, Object> map) {
		int i = 0;
		try {
			i = hosHospitalMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countHospital", e);
		}
		return i;
	}

}
