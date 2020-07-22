package com.hliedu.bs.service.impl;

import com.hliedu.bs.domain.BsCity;
import com.hliedu.bs.mapper.BsAreaMapper;
import com.hliedu.bs.domain.BsArea;
import com.hliedu.ee.redis.RedisHashUtil;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.bs.service.BsAreaService;

import java.util.*;

import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BsAreaServiceImpl extends BaseServiceImpl implements BsAreaService{

	public static final String SYS_CODE="BsAreaServiceImpl.";

	@Autowired
	private BsAreaMapper bsAreaMapper;

	@Override
	public String saveArea(BsArea bsArea) throws Exception{
		//1.检测
		String msg=checkArea(bsArea);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setAreaDefault(bsArea);
		//3.保存
		saveAreaModel(bsArea);
		return bsArea.getAreaCode();
	}

	@Override
	public boolean updateAreaState(Integer areaId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateAreaModel(areaId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateArea(BsArea bsArea) throws Exception {
		//1.检测
		String msg=checkArea(bsArea);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		BsArea oldBsArea=getAreaModelById(bsArea.getAreaId());
		if(null == oldBsArea){
			throw new Exception("数据为空");
		}

		//3.默认值
		setAreaUpdataDefault(bsArea);
		//4.保存
		updateAreaModel(bsArea);
		return true;
	}

	@Override
	public BsArea getArea(Integer areaId) throws Exception{
		return getAreaModelById(areaId);
	}

	@Override
	public boolean deleteArea(Integer areaId) throws Exception {
		int i = deleteAreaModel(areaId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<BsArea> queryAreaPage(Map<String, Object> map) throws Exception{
		List<BsArea> bsAreaList = queryAreaModelPage(map);
		QueryResult<BsArea> queryResult = new QueryResult<BsArea>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countArea(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(bsAreaList);
		return queryResult;
	}
	
	@Override
	public BsArea getAreaByCode(Map<String, Object> map) throws Exception{
		return getAreaModelByCode(map);
	}

	@Override
	public boolean delAreaByCode(Map<String, Object> map) throws Exception {
		int i = delAreaModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	//缓存区信息，以cityCode作为key
	@Autowired
	private RedisHashUtil redisHashUtil;

	private String areaCache_key="area_list";

	@Override
	public void cacheAreaByCity() {
		//从数据库获取数据
		Map<String,Object> map = new HashMap<>();
		List<BsArea> bsAreas = queryAreaModelPage(map);

		//声明redis缓存的map
		Map<String , List<BsArea>> areaMap = new HashMap<>();
		for (BsArea area : bsAreas) {
			String cityCode = area.getCityCode();
			if(areaMap.containsKey(cityCode)){
				areaMap.get(cityCode).add(area);
				continue;
			}
			ArrayList<BsArea> list = new ArrayList<>();
			list.add(area);
			areaMap.put(cityCode , list);
		}
		redisHashUtil.addMapAll(areaCache_key , areaMap);
	}

	/**
	 * 检测地区信息描述参数
	 * @param bsArea
	 * @return
	 */
	private String checkArea(BsArea bsArea){
		if(null==bsArea){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(bsArea.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(bsArea.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置地区信息描述新增默认值
	 * @param bsArea
	 */
	private void setAreaDefault(BsArea bsArea){
		if(null == bsArea)return;
		if(null == bsArea.getDataState())bsArea.setDataState(0);
		if(null == bsArea.getGmtCreate())bsArea.setGmtCreate(new Date());
	bsArea.setGmtModified(new Date());
		if(StringUtils.isBlank(bsArea.getAreaCode())){
	bsArea.setAreaCode(createUUIDString());
		}
	}

	/**
	 * 设置地区信息描述修改默认值
	 * @param bsArea
	 */
	private void setAreaUpdataDefault(BsArea bsArea){
		if(null==bsArea)return;
	bsArea.setGmtModified(new Date());
	}

	/**
	 * 保存地区信息描述对象
	 * @param bsArea
	 */
	private int saveAreaModel(BsArea bsArea){
		if(null==bsArea)return 0;
		return bsAreaMapper.insert(bsArea);
	}

	/**
	 * 获取地区信息描述信息
	 * @param areaId
	 * @return BsArea
	 */
	private BsArea getAreaModelById(Integer areaId){
		if(null==areaId)return null;
		return bsAreaMapper.selectByPrimaryKey(areaId);
	}

	/**
	 * 获取地区信息描述信息
	 * @param map<areaCode>
	 * @return BsArea
	 */
	private BsArea getAreaModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return bsAreaMapper.getByCode(map);
	}


	/**
	 * 删除地区信息描述信息
	 * @param map<areaCode>
	 */
	private int delAreaModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return bsAreaMapper.delByCode(map);
	}


	/**
	 * 删除地区信息描述信息
	 * @param areaId
	 */
	private int deleteAreaModel(Integer areaId){
		if(null==areaId)return 0;
		return bsAreaMapper.deleteByPrimaryKey(areaId);
	}

	/**
	 * 更新地区信息描述对象
	 * @param bsArea
	 */
	private int updateAreaModel(BsArea bsArea){
		if(null==bsArea)return 0;
		return bsAreaMapper.updateByPrimaryKeySelective(bsArea);
	}

	/**
	 * 更新地区信息描述状态
	 * @param areaId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateAreaModel(Integer areaId, Integer dataState, Integer oldDataState){
		if(null==areaId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("areaId", areaId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return bsAreaMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询地区信息描述
	 * @param paramMap
	 * @return BsArea
	 */
	private List<BsArea> queryAreaModelPage(Map<String, Object> paramMap) {
		try{
			return bsAreaMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryAreaModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countArea(Map<String, Object> map) {
		int i = 0;
		try {
			i = bsAreaMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countArea", e);
		}
		return i;
	}

}
