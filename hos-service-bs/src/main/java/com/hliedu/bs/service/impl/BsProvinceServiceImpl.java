package com.hliedu.bs.service.impl;

import com.hliedu.bs.mapper.BsProvinceMapper;
import com.hliedu.bs.domain.BsProvince;
import com.hliedu.ee.redis.RedisListUtil;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.JsonUtil;
import com.hliedu.tools.StringUtils;
import com.hliedu.bs.service.BsProvinceService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BsProvinceServiceImpl  extends BaseServiceImpl implements BsProvinceService{

	public static final String SYS_CODE="BsProvinceServiceImpl.";

	@Autowired
	private BsProvinceMapper bsProvinceMapper;


	@Override
	public String saveProvince(BsProvince bsProvince) throws Exception{
		//1.检测
		String msg=checkProvince(bsProvince);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setProvinceDefault(bsProvince);
		//3.保存
		saveProvinceModel(bsProvince);
		return bsProvince.getProvinceCode();
	}

	@Override
	public boolean updateProvinceState(Integer provinceId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateProvinceModel(provinceId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateProvince(BsProvince bsProvince) throws Exception {
		//1.检测
		String msg=checkProvince(bsProvince);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		BsProvince oldBsProvince=getProvinceModelById(bsProvince.getProvinceId());
		if(null == oldBsProvince){
			throw new Exception("数据为空");
		}

		//3.默认值
		setProvinceUpdataDefault(bsProvince);
		//4.保存
		updateProvinceModel(bsProvince);
		return true;
	}

	@Override
	public BsProvince getProvince(Integer provinceId) throws Exception{
		return getProvinceModelById(provinceId);
	}

	@Override
	public boolean deleteProvince(Integer provinceId) throws Exception {
		int i = deleteProvinceModel(provinceId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<BsProvince> queryProvincePage(Map<String, Object> map) throws Exception{
		List<BsProvince> bsProvinceList = queryProvinceModelPage(map);
		QueryResult<BsProvince> queryResult = new QueryResult<BsProvince>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countProvince(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(bsProvinceList);
		return queryResult;
	}
	
	@Override
	public BsProvince getProvinceByCode(Map<String, Object> map) throws Exception{
		return getProvinceModelByCode(map);
	}

	@Override
	public boolean delProvinceByCode(Map<String, Object> map) throws Exception {
		int i = delProvinceModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	//缓存省份信息
	@Autowired
	private RedisListUtil redisListUtil;

	final String provinceCache_key="province_list";//code -obg

	@Override
	public void cacheProvince() {
		Map<String,Object> map = new HashMap<>();
		List<BsProvince> bsProvinceList = queryProvinceModelPage(map);
		redisListUtil.insert(provinceCache_key , bsProvinceList);
	}


	/**
	 * 检测省份信息描述参数
	 * @param bsProvince
	 * @return
	 */
	private String checkProvince(BsProvince bsProvince){
		if(null==bsProvince){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(bsProvince.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(bsProvince.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置省份信息描述新增默认值
	 * @param bsProvince
	 */
	private void setProvinceDefault(BsProvince bsProvince){
		if(null == bsProvince)return;
		if(null == bsProvince.getDataState())bsProvince.setDataState(0);
		if(null == bsProvince.getGmtCreate())bsProvince.setGmtCreate(new Date());
	bsProvince.setGmtModified(new Date());
		if(StringUtils.isBlank(bsProvince.getProvinceCode())){
	bsProvince.setProvinceCode(createUUIDString());
		}
	}

	/**
	 * 设置省份信息描述修改默认值
	 * @param bsProvince
	 */
	private void setProvinceUpdataDefault(BsProvince bsProvince){
		if(null==bsProvince)return;
	bsProvince.setGmtModified(new Date());
	}

	/**
	 * 保存省份信息描述对象
	 * @param bsProvince
	 */
	private int saveProvinceModel(BsProvince bsProvince){
		if(null==bsProvince)return 0;
		return bsProvinceMapper.insert(bsProvince);
	}

	/**
	 * 获取省份信息描述信息
	 * @param provinceId
	 * @return BsProvince
	 */
	private BsProvince getProvinceModelById(Integer provinceId){
		if(null==provinceId)return null;
		return bsProvinceMapper.selectByPrimaryKey(provinceId);
	}

	/**
	 * 获取省份信息描述信息
	 * @param map<provinceCode>
	 * @return BsProvince
	 */
	private BsProvince getProvinceModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return bsProvinceMapper.getByCode(map);
	}


	/**
	 * 删除省份信息描述信息
	 * @param map<provinceCode>
	 */
	private int delProvinceModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return bsProvinceMapper.delByCode(map);
	}


	/**
	 * 删除省份信息描述信息
	 * @param provinceId
	 */
	private int deleteProvinceModel(Integer provinceId){
		if(null==provinceId)return 0;
		return bsProvinceMapper.deleteByPrimaryKey(provinceId);
	}

	/**
	 * 更新省份信息描述对象
	 * @param bsProvince
	 */
	private int updateProvinceModel(BsProvince bsProvince){
		if(null==bsProvince)return 0;
		return bsProvinceMapper.updateByPrimaryKeySelective(bsProvince);
	}

	/**
	 * 更新省份信息描述状态
	 * @param provinceId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateProvinceModel(Integer provinceId, Integer dataState, Integer oldDataState){
		if(null==provinceId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return bsProvinceMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询省份信息描述
	 * @param paramMap
	 * @return BsProvince
	 */
	private List<BsProvince> queryProvinceModelPage(Map<String, Object> paramMap) {
		try{
			return bsProvinceMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryProvinceModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countProvince(Map<String, Object> map) {
		int i = 0;
		try {
			i = bsProvinceMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countProvince", e);
		}
		return i;
	}

}
