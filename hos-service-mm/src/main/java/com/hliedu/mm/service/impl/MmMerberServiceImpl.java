package com.hliedu.mm.service.impl;

import com.hliedu.mm.mapper.MmMerberMapper;
import com.hliedu.mm.domain.MmMerber;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.mm.service.MmMerberService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MmMerberServiceImpl  extends BaseServiceImpl implements MmMerberService{

	public static final String SYS_CODE="MmMerberServiceImpl.";

	@Autowired
	private MmMerberMapper mmMerberMapper;

	@Override
	public String saveMerber(MmMerber mmMerber) throws Exception{
		//1.检测
		String msg=checkMerber(mmMerber);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setMerberDefault(mmMerber);
		//3.保存
		saveMerberModel(mmMerber);
		return mmMerber.getMerberCode();
	}

	@Override
	public boolean updateMerberState(Integer merberId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateMerberModel(merberId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateMerber(MmMerber mmMerber) throws Exception {
		//1.检测
		String msg=checkMerber(mmMerber);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MmMerber oldMmMerber=getMerberModelById(mmMerber.getMerberId());
		if(null == oldMmMerber){
			throw new Exception("数据为空");
		}

		//3.默认值
		setMerberUpdataDefault(mmMerber);
		//4.保存
		updateMerberModel(mmMerber);
		return true;
	}

	@Override
	public MmMerber getMerber(Integer merberId) throws Exception{
		return getMerberModelById(merberId);
	}

	@Override
	public boolean deleteMerber(Integer merberId) throws Exception {
		int i = deleteMerberModel(merberId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MmMerber> queryMerberPage(Map<String, Object> map) throws Exception{
		List<MmMerber> mmMerberList = queryMerberModelPage(map);
		QueryResult<MmMerber> queryResult = new QueryResult<MmMerber>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countMerber(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(mmMerberList);
		return queryResult;
	}
	
	@Override
	public MmMerber getMerberByCode(Map<String, Object> map) throws Exception{
		return getMerberModelByCode(map);
	}

	@Override
	public boolean delMerberByCode(Map<String, Object> map) throws Exception {
		int i = delMerberModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测会员用户信息参数
	 * @param mmMerber
	 * @return
	 */
	private String checkMerber(MmMerber mmMerber){
		if(null==mmMerber){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(mmMerber.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(mmMerber.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置会员用户信息新增默认值
	 * @param mmMerber
	 */
	private void setMerberDefault(MmMerber mmMerber){
		if(null == mmMerber)return;
		if(null == mmMerber.getDataState())mmMerber.setDataState(0);
		if(null == mmMerber.getGmtCreate())mmMerber.setGmtCreate(new Date());
	mmMerber.setGmtModified(new Date());
		if(StringUtils.isBlank(mmMerber.getMerberCode())){
	mmMerber.setMerberCode(createUUIDString());
		}
	}

	/**
	 * 设置会员用户信息修改默认值
	 * @param mmMerber
	 */
	private void setMerberUpdataDefault(MmMerber mmMerber){
		if(null==mmMerber)return;
	mmMerber.setGmtModified(new Date());
	}

	/**
	 * 保存会员用户信息对象
	 * @param mmMerber
	 */
	private int saveMerberModel(MmMerber mmMerber){
		if(null==mmMerber)return 0;
		return mmMerberMapper.insert(mmMerber);
	}

	/**
	 * 获取会员用户信息信息
	 * @param merberId
	 * @return MmMerber
	 */
	private MmMerber getMerberModelById(Integer merberId){
		if(null==merberId)return null;
		return mmMerberMapper.selectByPrimaryKey(merberId);
	}

	/**
	 * 获取会员用户信息信息
	 * @param map<merberCode>
	 * @return MmMerber
	 */
	private MmMerber getMerberModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return mmMerberMapper.getByCode(map);
	}


	/**
	 * 删除会员用户信息信息
	 * @param map<merberCode>
	 */
	private int delMerberModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return mmMerberMapper.delByCode(map);
	}


	/**
	 * 删除会员用户信息信息
	 * @param merberId
	 */
	private int deleteMerberModel(Integer merberId){
		if(null==merberId)return 0;
		return mmMerberMapper.deleteByPrimaryKey(merberId);
	}

	/**
	 * 更新会员用户信息对象
	 * @param mmMerber
	 */
	private int updateMerberModel(MmMerber mmMerber){
		if(null==mmMerber)return 0;
		return mmMerberMapper.updateByPrimaryKeySelective(mmMerber);
	}

	/**
	 * 更新会员用户信息状态
	 * @param merberId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateMerberModel(Integer merberId, Integer dataState, Integer oldDataState){
		if(null==merberId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("merberId", merberId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return mmMerberMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询会员用户信息
	 * @param paramMap
	 * @return MmMerber
	 */
	private List<MmMerber> queryMerberModelPage(Map<String, Object> paramMap) {
		try{
			return mmMerberMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryMerberModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countMerber(Map<String, Object> map) {
		int i = 0;
		try {
			i = mmMerberMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countMerber", e);
		}
		return i;
	}

}
