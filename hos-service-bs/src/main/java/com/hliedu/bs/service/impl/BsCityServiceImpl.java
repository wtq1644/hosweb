package com.hliedu.bs.service.impl;

import com.hliedu.bs.domain.BsProvince;
import com.hliedu.bs.mapper.BsCityMapper;
import com.hliedu.bs.domain.BsCity;
import com.hliedu.ee.redis.RedisHashUtil;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.bs.service.BsCityService;

import java.util.*;

import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BsCityServiceImpl  extends BaseServiceImpl implements BsCityService{

	public static final String SYS_CODE="BsCityServiceImpl.";

	@Autowired
	private BsCityMapper bsCityMapper;

	@Override
	public String saveCity(BsCity bsCity) throws Exception{
		//1.检测
		String msg=checkCity(bsCity);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setCityDefault(bsCity);
		//3.保存
		saveCityModel(bsCity);
		return bsCity.getCityCode();
	}

	@Override
	public boolean updateCityState(Integer cityId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateCityModel(cityId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateCity(BsCity bsCity) throws Exception {
		//1.检测
		String msg=checkCity(bsCity);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		BsCity oldBsCity=getCityModelById(bsCity.getCityId());
		if(null == oldBsCity){
			throw new Exception("数据为空");
		}

		//3.默认值
		setCityUpdataDefault(bsCity);
		//4.保存
		updateCityModel(bsCity);
		return true;
	}

	@Override
	public BsCity getCity(Integer cityId) throws Exception{
		return getCityModelById(cityId);
	}

	@Override
	public boolean deleteCity(Integer cityId) throws Exception {
		int i = deleteCityModel(cityId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<BsCity> queryCityPage(Map<String, Object> map) throws Exception{
		List<BsCity> bsCityList = queryCityModelPage(map);
		QueryResult<BsCity> queryResult = new QueryResult<BsCity>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countCity(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(bsCityList);
		return queryResult;
	}
	
	@Override
	public BsCity getCityByCode(Map<String, Object> map) throws Exception{
		return getCityModelByCode(map);
	}

	@Override
	public boolean delCityByCode(Map<String, Object> map) throws Exception {
		int i = delCityModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	//缓存城市信息，以provinceCode为key
	@Autowired
	private RedisHashUtil redisHashUtil;

	private String cityCache_key="city_list";

	@Override
	public void cacheCityByProvince() {
		//从数据库获取数据
		Map<String,Object> map = new HashMap<>();
		List<BsCity> cityList = queryCityModelPage(map);

		//声明redis缓存的map
		Map<String , List<BsCity>> cityMap = new HashMap<>();
		for (BsCity city : cityList) {
			String provinceCode = city.getProvinceCode();
			//如果redis缓存map中已经存在该省，那么直接将city添加
			if(cityMap.containsKey(provinceCode)){
				cityMap.get(provinceCode).add(city);
				continue;
			}
			//否则添加一个新的key
			List<BsCity> tempList = new ArrayList<>();
			tempList.add(city);
			cityMap.put(provinceCode , tempList);
		}
		redisHashUtil.addMapAll(cityCache_key , cityMap);
	}

	/**
	 * 检测地区信息描述参数
	 * @param bsCity
	 * @return
	 */
	private String checkCity(BsCity bsCity){
		if(null==bsCity){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(bsCity.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(bsCity.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置地区信息描述新增默认值
	 * @param bsCity
	 */
	private void setCityDefault(BsCity bsCity){
		if(null == bsCity)return;
		if(null == bsCity.getDataState())bsCity.setDataState(0);
		if(null == bsCity.getGmtCreate())bsCity.setGmtCreate(new Date());
	bsCity.setGmtModified(new Date());
		if(StringUtils.isBlank(bsCity.getCityCode())){
	bsCity.setCityCode(createUUIDString());
		}
	}

	/**
	 * 设置地区信息描述修改默认值
	 * @param bsCity
	 */
	private void setCityUpdataDefault(BsCity bsCity){
		if(null==bsCity)return;
	bsCity.setGmtModified(new Date());
	}

	/**
	 * 保存地区信息描述对象
	 * @param bsCity
	 */
	private int saveCityModel(BsCity bsCity){
		if(null==bsCity)return 0;
		return bsCityMapper.insert(bsCity);
	}

	/**
	 * 获取地区信息描述信息
	 * @param cityId
	 * @return BsCity
	 */
	private BsCity getCityModelById(Integer cityId){
		if(null==cityId)return null;
		return bsCityMapper.selectByPrimaryKey(cityId);
	}

	/**
	 * 获取地区信息描述信息
	 * @param map<cityCode>
	 * @return BsCity
	 */
	private BsCity getCityModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return bsCityMapper.getByCode(map);
	}


	/**
	 * 删除地区信息描述信息
	 * @param map<cityCode>
	 */
	private int delCityModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return bsCityMapper.delByCode(map);
	}


	/**
	 * 删除地区信息描述信息
	 * @param cityId
	 */
	private int deleteCityModel(Integer cityId){
		if(null==cityId)return 0;
		return bsCityMapper.deleteByPrimaryKey(cityId);
	}

	/**
	 * 更新地区信息描述对象
	 * @param bsCity
	 */
	private int updateCityModel(BsCity bsCity){
		if(null==bsCity)return 0;
		return bsCityMapper.updateByPrimaryKeySelective(bsCity);
	}

	/**
	 * 更新地区信息描述状态
	 * @param cityId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateCityModel(Integer cityId, Integer dataState, Integer oldDataState){
		if(null==cityId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("cityId", cityId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return bsCityMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询地区信息描述
	 * @param paramMap
	 * @return BsCity
	 */
	private List<BsCity> queryCityModelPage(Map<String, Object> paramMap) {
		try{
			return bsCityMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryCityModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countCity(Map<String, Object> map) {
		int i = 0;
		try {
			i = bsCityMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countCity", e);
		}
		return i;
	}

}
