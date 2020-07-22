package com.hliedu.mm.service.impl;

import com.hliedu.mm.mapper.MmOrderInfoMapper;
import com.hliedu.mm.domain.MmOrderInfo;
import com.hliedu.mybatis.page.PageTools;
import com.hliedu.mybatis.service.BaseServiceImpl;
import com.hliedu.tools.BeanUtils;
import com.hliedu.tools.StringUtils;
import com.hliedu.mm.service.MmOrderInfoService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hliedu.mybatis.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MmOrderInfoServiceImpl  extends BaseServiceImpl implements MmOrderInfoService{

	public static final String SYS_CODE="MmOrderInfoServiceImpl.";

	@Autowired
	private MmOrderInfoMapper mmOrderInfoMapper;

	@Override
	public String saveOrderInfo(MmOrderInfo mmOrderInfo) throws Exception{
		//1.检测
		String msg=checkOrderInfo(mmOrderInfo);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}
		//2.默认值
		setOrderInfoDefault(mmOrderInfo);
		//3.保存
		saveOrderInfoModel(mmOrderInfo);
		return mmOrderInfo.getOrderCode();
	}

	@Override
	public boolean updateOrderInfoState(Integer orderId, Integer dataState, Integer oldDataState)
			throws Exception {
		int i = updateStateOrderInfoModel(orderId, dataState, oldDataState);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("修改失败");
		}
		return success;
	}

	@Override
	public boolean updateOrderInfo(MmOrderInfo mmOrderInfo) throws Exception {
		//1.检测
		String msg=checkOrderInfo(mmOrderInfo);
		if(StringUtils.isNotBlank(msg)){
			throw new Exception(msg);
		}

		//2.获取MODEL
		MmOrderInfo oldMmOrderInfo=getOrderInfoModelById(mmOrderInfo.getOrderId());
		if(null == oldMmOrderInfo){
			throw new Exception("数据为空");
		}

		//3.默认值
		setOrderInfoUpdataDefault(mmOrderInfo);
		//4.保存
		updateOrderInfoModel(mmOrderInfo);
		return true;
	}

	@Override
	public MmOrderInfo getOrderInfo(Integer orderId) throws Exception{
		return getOrderInfoModelById(orderId);
	}

	@Override
	public boolean deleteOrderInfo(Integer orderId) throws Exception {
		int i = deleteOrderInfoModel(orderId);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}

	@Override
	public QueryResult<MmOrderInfo> queryOrderInfoPage(Map<String, Object> map) throws Exception{
		List<MmOrderInfo> mmOrderInfoList = queryOrderInfoModelPage(map);
		QueryResult<MmOrderInfo> queryResult = new QueryResult<MmOrderInfo>();
		PageTools pageTools = new PageTools();
		pageTools.setRecordCount(countOrderInfo(map));
		queryResult.setPageTools(pageTools);
		queryResult.setList(mmOrderInfoList);
		return queryResult;
	}
	
	@Override
	public MmOrderInfo getOrderInfoByCode(Map<String, Object> map) throws Exception{
		return getOrderInfoModelByCode(map);
	}

	@Override
	public boolean delOrderInfoByCode(Map<String, Object> map) throws Exception {
		int i = delOrderInfoModelByCode(map);
		boolean success = i > 0 ? true : false;
		if(!success){
			throw new Exception("删除失败");
		}
		return success;
	}



	/**
	 * 检测预约单信息参数
	 * @param mmOrderInfo
	 * @return
	 */
	private String checkOrderInfo(MmOrderInfo mmOrderInfo){
		if(null==mmOrderInfo){
			return "参数为空";
		}
		//TODO 需要根据当前对象做修改
		//if(StringUtils.isBlank(mmOrderInfo.getFtpserviceCode())){
		//	return "FtpserviceCode为空;";
		//}
		//if(StringUtils.isBlank(mmOrderInfo.getFtpserviceUrl())){
		//	return "FtpserviceUrl为空;";
		//}
		return "";
	}

	/**
	 * 设置预约单信息新增默认值
	 * @param mmOrderInfo
	 */
	private void setOrderInfoDefault(MmOrderInfo mmOrderInfo){
		if(null == mmOrderInfo)return;
		if(null == mmOrderInfo.getDataState())mmOrderInfo.setDataState(0);
		if(null == mmOrderInfo.getGmtCreate())mmOrderInfo.setGmtCreate(new Date());
	mmOrderInfo.setGmtModified(new Date());
		if(StringUtils.isBlank(mmOrderInfo.getOrderCode())){
	mmOrderInfo.setOrderCode(createUUIDString());
		}
	}

	/**
	 * 设置预约单信息修改默认值
	 * @param mmOrderInfo
	 */
	private void setOrderInfoUpdataDefault(MmOrderInfo mmOrderInfo){
		if(null==mmOrderInfo)return;
	mmOrderInfo.setGmtModified(new Date());
	}

	/**
	 * 保存预约单信息对象
	 * @param mmOrderInfo
	 */
	private int saveOrderInfoModel(MmOrderInfo mmOrderInfo){
		if(null==mmOrderInfo)return 0;
		return mmOrderInfoMapper.insert(mmOrderInfo);
	}

	/**
	 * 获取预约单信息信息
	 * @param orderId
	 * @return MmOrderInfo
	 */
	private MmOrderInfo getOrderInfoModelById(Integer orderId){
		if(null==orderId)return null;
		return mmOrderInfoMapper.selectByPrimaryKey(orderId);
	}

	/**
	 * 获取预约单信息信息
	 * @param map<orderCode>
	 * @return MmOrderInfo
	 */
	private MmOrderInfo getOrderInfoModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return null;
		return mmOrderInfoMapper.getByCode(map);
	}


	/**
	 * 删除预约单信息信息
	 * @param map<orderCode>
	 */
	private int delOrderInfoModelByCode(Map<String,Object> map){
		if(null==map||map.isEmpty())return 0;
		return mmOrderInfoMapper.delByCode(map);
	}


	/**
	 * 删除预约单信息信息
	 * @param orderId
	 */
	private int deleteOrderInfoModel(Integer orderId){
		if(null==orderId)return 0;
		return mmOrderInfoMapper.deleteByPrimaryKey(orderId);
	}

	/**
	 * 更新预约单信息对象
	 * @param mmOrderInfo
	 */
	private int updateOrderInfoModel(MmOrderInfo mmOrderInfo){
		if(null==mmOrderInfo)return 0;
		return mmOrderInfoMapper.updateByPrimaryKeySelective(mmOrderInfo);
	}

	/**
	 * 更新预约单信息状态
	 * @param orderId
	 * @param dataState
	 * @param oldDataState
	 */
	private int updateStateOrderInfoModel(Integer orderId, Integer dataState, Integer oldDataState){
		if(null==orderId||null==dataState)return 0;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("dataState", dataState);
		map.put("oldDataState", oldDataState);
		return mmOrderInfoMapper.updateStateByPrimaryKey(map);
	}

	/**
	 * 分页查询预约单信息
	 * @param paramMap
	 * @return MmOrderInfo
	 */
	private List<MmOrderInfo> queryOrderInfoModelPage(Map<String, Object> paramMap) {
		try{
			return mmOrderInfoMapper.query(paramMap);
		}catch(Exception e){
			logger.error(SYS_CODE+".queryOrderInfoModel", e);
		}
		return null;
	}

	/**
	 * 获取记录数
	 * @param map
	 * @return 记录数
	 */
	private int countOrderInfo(Map<String, Object> map) {
		int i = 0;
		try {
			i = mmOrderInfoMapper.count(map);
		} catch (Exception e) {
			logger.error(SYS_CODE+".countOrderInfo", e);
		}
		return i;
	}

}
