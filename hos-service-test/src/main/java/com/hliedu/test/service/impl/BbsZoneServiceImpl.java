package com.hliedu.test.service.impl;

import com.hliedu.test.mapper.BbsZoneMapper;
import com.hliedu.test.domain.BbsZone;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.StringUtils;
import com.hliedu.test.service.BbsZoneService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BbsZoneServiceImpl  extends BaseServiceImpl implements BbsZoneService{

	public static final String SYS_CODE="BbsZoneServiceImpl.";

	@Autowired
	private BbsZoneMapper bbsZoneMapper;

	@Override
	public String saveZone(BbsZone bbsZone) throws Exception{
		//1.检测
		String msg=checkZone(bbsZone);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setZoneDefault(bbsZone);
		//3.保存
		saveZoneModel(bbsZone);
		return bbsZone.getZoneCode();
	}

	@Override
	public boolean updateZoneState(Integer zoneId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateZoneModel(zoneId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateZone(BbsZone bbsZone) throws Exception {
		//1.检测
		String msg=checkZone(bbsZone);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		BbsZone oldBbsZone=getZoneModelById(bbsZone.getZoneId());
		if(null == oldBbsZone){
			throw new Exception("数据为空");
		}

		//3.默认值
		setZoneUpdataDefault(bbsZone);
		//4.保存
		updateZoneModel(bbsZone);
		return true;
	}

	@Override
	public BbsZone getZone(Integer zoneId) throws Exception{
		return getZoneModelById(zoneId);
	}

	@Override
	public boolean deleteZone(Integer zoneId) throws Exception {
		int i = deleteZoneModel(zoneId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<BbsZone> queryZonePage(Map<String, Object> map) throws Exception{
		List<BbsZone> bbsZoneList = queryZoneModelPage(map);
		QueryResult<BbsZone> queryResult = new QueryResult<BbsZone>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countZone(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(bbsZoneList);
		return queryResult;
	}
	
	@Override
	public BbsZone getZoneByCode(Map<String, Object> map) throws Exception{
		return getZoneModelByCode(map);
	}

	@Override
	public boolean delZoneByCode(Map<String, Object> map) throws Exception {
		int i = delZoneModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测空间信息表描述参数
	 * @param bbsZone
	 * @return
	 */
	private String checkZone(BbsZone bbsZone){
		if(null==bbsZone){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(bbsZone.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(bbsZone.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置空间信息表描述新增默认值
	 * @param bbsZone
	 */
	private void setZoneDefault(BbsZone bbsZone){
		if(null == bbsZone)return;
		if(null == bbsZone.getDataState())bbsZone.setDataState(0);
		if(null == bbsZone.getGmtCreate())bbsZone.setGmtCreate(new Date());
	bbsZone.setGmtModified(new Date());
		if(StringUtils.isBlank(bbsZone.getZoneCode())){
	bbsZone.setZoneCode(createUUIDString());
		}
	}

	/**
	 * 设置空间信息表描述修改默认值
	 * @param bbsZone
	 */
	private void setZoneUpdataDefault(BbsZone bbsZone){
		if(null==bbsZone)return;
	bbsZone.setGmtModified(new Date());
	}

	/**
	 * 保存空间信息表描述对象
	 * @param bbsZone
	 */
	private int saveZoneModel(BbsZone bbsZone){
		if(null==bbsZone)return 0;
		return bbsZoneMapper.insert(bbsZone);
	}

	/**
	 * 获取空间信息表描述信息
	 * @param zoneId
	 * @return BbsZone
	 */
	private BbsZone getZoneModelById(Integer zoneId){
		if(null==zoneId)return null;
		return bbsZoneMapper.selectByPrimaryKey(zoneId);
	}

	/**
	 * 获取空间信息表描述信息
	 * @param map<zoneCode>
	 * @return BbsZone
	 */
	private BbsZone getZoneModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return bbsZoneMapper.getByCode(map);
	}


	/**
	 * 删除空间信息表描述信息
	 * @param map<zoneCode>
	 */
	private int delZoneModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return bbsZoneMapper.delByCode(map);
	}


	/**
	 * 删除空间信息表描述信息
	 * @param zoneId
	 */
	private int deleteZoneModel(Integer zoneId){
		if(null==zoneId)return 0;
		return bbsZoneMapper.deleteByPrimaryKey(zoneId);
	}

	/**
	 * 更新空间信息表描述对象
	 * @param bbsZone
	 */
	private int updateZoneModel(BbsZone bbsZone){
		if(null==bbsZone)return 0;
		return bbsZoneMapper.updateByPrimaryKeySelective(bbsZone);
	}

	/**
	 * 更新空间信息表描述状态
	 * @param zoneId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateZoneModel(Integer zoneId, Integer dataState, Integer oldDataState){
		if(null==zoneId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("zoneId", zoneId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return bbsZoneMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询空间信息表描述
	 * @param paramMap
	 * @return BbsZone
	 */
	private List<BbsZone> queryZoneModelPage(Map<String, Object> paramMap) {
		try{
			return bbsZoneMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryZoneModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countZone(Map<String, Object> map) {
		int i = 0;
		try {
			i = bbsZoneMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countZone", e);
		}
		return i;
	}

}
