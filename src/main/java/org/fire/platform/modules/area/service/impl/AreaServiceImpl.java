package org.fire.platform.modules.area.service.impl;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.fire.platform.common.base.CountValueData;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.constants.AreaConstants;
import org.fire.platform.modules.area.dao.AreaMapper;
import org.fire.platform.modules.area.service.IAreaService;
import org.fire.platform.modules.area.service.IUserConcernAreaService;
import org.fire.platform.modules.area.vo.*;
import org.fire.platform.modules.building.service.IBuildingInfoService;
import org.fire.platform.modules.front.vo.AreaDetailResult;
import org.fire.platform.modules.front.vo.AreaRiskLevelVo;
import org.fire.platform.modules.front.vo.AreaTypeResultVo;
import org.fire.platform.util.DateFormatUtil;
import org.fire.platform.util.LatlngUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class AreaServiceImpl implements IAreaService{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AreaMapper areaMapper;
	
	@Autowired
	private IBuildingInfoService buildingInfoService;

	@Autowired
	private IUserConcernAreaService userConcernAreaService;
	
	@Override
	public PageInfo<AreaVo> queryConcernArea(Long userId, Integer pageNo,
			Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<AreaVo> ls=  areaMapper.selectConcernArea(userId);
		//区域趋势图
		setAreaTrendData(ls);
		return PageHelper.getPageInfo(ls);
	}
	
	
	
	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryDistrict_' +  #name + '_' + #pageNo")
	})
	public PageInfo<SimpleAreaVo> queryDistrict(String name, Integer pageNo,
			Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( StringUtils.isNotBlank(name)){
			param.put("name", name);
		}
		List<SimpleAreaVo> list =  areaMapper.selectDistrictSimple(param);
		return PageHelper.getPageInfo(list);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryStreet_' +  #name + '_' + #pageNo")
	})
	public PageInfo<SimpleAreaVo> queryStreet(Long districtId, String name,
			Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( StringUtils.isNotBlank(name)){
			param.put("name", name);
		}
		
		if( districtId != null && districtId > 0 ){
			param.put("districtId", districtId);
		}
		
		List<SimpleAreaVo> list =  areaMapper.selectStreetSimple(param);
		return PageHelper.getPageInfo(list);
	}
	
	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryArea_' + #districtId + '_' + #streetId + '_' + #blockType + '_' + #pageNo")
	})
	public PageInfo<AreaVo> queryArea(String name, Long districtId,Long streetId,String blockType, Integer pageNo,
			Integer pageSize) {
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( StringUtils.isNotBlank(name)){
			param.put("name", name);
		}
		
		if(districtId !=null && districtId > 0 ){
			param.put("districtId", districtId);
		}
		
		if( streetId!=null && streetId > 0 ){
			param.put("streetId", streetId);
		}
		
		if( StringUtils.isNotBlank(blockType)){
			param.put("blockType", blockType);
		}
		
		if( streetId != null && streetId > 0 ){
			return queryBlock(param,pageNo,pageSize);
			
		}else if(districtId !=null && (streetId == null || streetId == 0) ){
			return queryStreetAndBlock(param,pageNo,pageSize);
		}
		return queryAllArea(param,pageNo,pageSize);
		
	}
	
	PageInfo<AreaVo> queryBlock(Map<String,Object> param,Integer pageNo,Integer pageSize){
		PageHelper.startPage(pageNo, pageSize);
		List<AreaVo> ls = areaMapper.selectBlock(param);
		return PageHelper.getPageInfo(ls);
	}
	
	
	PageInfo<AreaVo> queryStreetAndBlock(Map<String,Object> param,Integer pageNo,Integer pageSize){
		PageHelper.startPage(pageNo, pageSize);
		List<AreaVo> ls = areaMapper.selectStreetAndBlock(param);
		return PageHelper.getPageInfo(ls);
	}
	
	PageInfo<AreaVo> queryAllArea(Map<String,Object> param,Integer pageNo,Integer pageSize){
		PageHelper.startPage(pageNo, pageSize);
		List<AreaVo> ls = areaMapper.selectArea(param);
		return PageHelper.getPageInfo(ls);
	}
	
	
	
	private void setAreaTrendData(List<AreaVo> list){
		if( CollectionUtils.isEmpty(list)){
			logger.warn("AreaVo list is empty");
			return;
		}
		for( AreaVo vo : list){
			AreaTrendVo areaTrend = queryTrendReport(vo.getAreaId(),vo.getAreaType());
			if(areaTrend != null){
				vo.setAreaTrend(areaTrend);
			}
			
			//TODO 查区域安全指数
		}
	}

	/**
	 * 需要缓存
	 */
	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryTrendReport_' + #areaId + '_' + #areaType")
	})
	public AreaTrendVo queryTrendReport(Long areaId, Integer areaType) {
		// TODO Auto-generated method stub
		Map<String,Object> param = getBeginAndEndMonth();
		setAreaParameter(areaId, areaType, param);
		return queryTrendReport(param);
	}
	
	public AreaTrendVo queryTrendReport(Map<String,Object> param){
		logger.info("queryAreaTrendReport param={}",param);
		List<AreaDataVo> confirmFireList = areaMapper.selectConfirmFireSum(param);
		confirmFireList = fillMonth(confirmFireList);
//		List<AreaDataVo> unpassReportList = areaMapper.selectUnPassCheckReportSum(param);
//		unpassReportList = fillMonth(unpassReportList);
		List<AreaDataVo> punishList = areaMapper.selectPunishSum(param);
		punishList = fillMonth(punishList);
		AreaTrendVo areaTrend = new AreaTrendVo();
//		areaTrend.setCheckReportDatas(unpassReportList);
		areaTrend.setFireEventDatas(confirmFireList);
		areaTrend.setPunishEventDatas(punishList);
		return areaTrend;
	}

	@Override
	public List<AreaTypeDataVo> queryFireCompareData(Long areaId, Integer areaType) {
		logger.info("queryFireCompareData,areaId={},areaType={}",areaId,areaType);
		List<AreaTypeDataVo> list = new ArrayList<AreaTypeDataVo>();
		//TODO 获取今年的火情数据
		Map<String,Object> param = getThisYeay();
		AreaTypeDataVo thisYearData = getFireData(areaId, areaType, param);
		thisYearData = checkVo(thisYearData,(String) param.get("monthBegin"));

		//TODO 获取去年的火情数据
		param = getLastYear();
		AreaTypeDataVo lastYearData = getFireData(areaId, areaType, param);
		lastYearData = checkVo(lastYearData,(String) param.get("monthBegin"));


		list.add(lastYearData);
		list.add(thisYearData);
		return list;
		
		//List<AreaCompareData> fireCompare = getFireCompareData(lastYearData,thisYearData);
		
		//return fireCompare;

	}

	/**
	 * 获取今年统计数据group by
	 * @param areaId
	 * @param areaType
     * @return
     */
	@Override
	public List<AreaCompareStreetData> queryFireDataGroup(Long areaId, Integer areaType,String monthBegin,String monthEnd) {
		logger.info("AreaServiceImpl -> queryFireDataGroup params -> areaId = {}, areaType = {}, monthBegin = {}, monthEnd = {} " , areaId , areaType , monthBegin , monthEnd );
		Map<String,Object> param = new HashMap<String,Object>();
		// 同期
		Map<String,Object> lastParam = new HashMap<String,Object>();
		String lastStartMonth = "";
		Integer lastYear = 0;
		if( StringUtils.isNotBlank(monthBegin)){
			param.put("monthBegin", monthBegin);
			String year = monthBegin.split("-")[0]; // 传进来的年份
			lastYear = Integer.parseInt(year) - 1;  // 上一年
			lastStartMonth = String.valueOf(lastYear) + "-"+monthBegin.split("-")[1];
			lastParam.put("monthBegin",lastStartMonth);
		}
		if( StringUtils.isNotBlank(monthEnd)){
			param.put("monthEnd", monthEnd);
			String lastEndMonth = String.valueOf(lastYear) + "-"+monthEnd.split("-")[1];
			lastParam.put("monthEnd",lastEndMonth);
		}
		// 所选择的
		setAreaParameter(areaId, areaType, param);
		List<AreaTypeDataVo>  firstFireData = getFireDataGroup(areaId, areaType, param);
		// 同期
		setAreaParameter(areaId, areaType, lastParam);
		List<AreaTypeDataVo>  lastFireData = getFireDataGroup(areaId, areaType, lastParam);
		List<AreaCompareStreetData> result = new ArrayList<AreaCompareStreetData>();
		if (firstFireData != null & firstFireData.size() >0 && lastFireData != null && lastFireData.size() >0){
			for (int i=0;i<firstFireData.size();i++){
				if (firstFireData.get(i) != null && lastFireData.get(i) != null){
					String thisYearStreetName = firstFireData.get(i).getStreetName();
					String lastYearStreetName = lastFireData.get(i).getStreetName();
					if (thisYearStreetName != null && lastYearStreetName != null){
						AreaCompareStreetData acsd = new AreaCompareStreetData();
						acsd.setStreetName(thisYearStreetName);
						acsd.setAreaCompareDatas(getFireCompareData(lastFireData.get(i),firstFireData.get(i)));
						result.add(acsd);
					}
				}
			}
		}
		return result;
	}


	@Override
	public List<AreaTypeDataVo> queryCheckReportCompareData(Long areaId,
			Integer areaType) {
		logger.info("queryCheckReportCompareData,areaId={},areaType={}",areaId,areaType);
		List<AreaTypeDataVo> list = new ArrayList<AreaTypeDataVo>();
		//TODO 获取今年的检查报告
		Map<String,Object> param = getThisYeay();
		AreaTypeDataVo thisYearData = getCheckReportData(areaId, areaType, param);
		thisYearData = checkVo(thisYearData,(String) param.get("monthBegin"));

		//TODO 获取去年的检查报告
		param = getLastYear();
		AreaTypeDataVo lastYearData = getCheckReportData(areaId, areaType, param);
		lastYearData = checkVo(lastYearData,(String) param.get("monthBegin"));

		//List<AreaCompareData> fireCompare = getCheckReportCompareData(lastYearData,thisYearData);
		
		//return fireCompare;

		list.add(lastYearData);
		list.add(thisYearData);
		return list;
	}

	@Override
	public List<AreaTypeDataVo> queryPunishCompareData(Long areaId,
			Integer areaType) {
		//TODO 获取今年的执法数据
		List<AreaTypeDataVo> list = new ArrayList<AreaTypeDataVo>();
		Map<String,Object> param = getThisYeay();
		AreaTypeDataVo thisYearData = getPunishData(areaId, areaType, param);
		thisYearData = checkVo(thisYearData,(String) param.get("monthBegin"));
		//TODO 获取去年的执法数据
		param = getLastYear();
		AreaTypeDataVo lastYearData = getPunishData(areaId, areaType, param);
		lastYearData = checkVo(lastYearData,(String) param.get("monthBegin"));
		//List<AreaCompareData> punishCompare = getPunishCompareData(lastYearData,thisYearData);
		//return punishCompare;

		list.add(lastYearData);
		list.add(thisYearData);
		return list;
	}
	

	private AreaTypeDataVo getFireData(Long areaId, Integer areaType,
			Map<String, Object> param) {
		logger.info("getFireData areaId={},areaType+{},param={}",areaId,areaType,param);
		setAreaParameter(areaId, areaType, param);
		AreaTypeDataVo fireTypeData = areaMapper.selectFireTypeSum(param);
		return fireTypeData;
	}

	/**
	 * 按街道分组排名
	 * @param areaId
	 * @param areaType
	 * @param param
     * @return
     */
	private List<AreaTypeDataVo> getFireDataGroup(Long areaId, Integer areaType,
									   Map<String, Object> param) {
		logger.info("getFireData areaId={},areaType+{},param={}",areaId,areaType,param);
		setAreaParameter(areaId, areaType, param);
		List<AreaTypeDataVo> fireTypeDataGroup = areaMapper.selectFireTypeSumGroup(param);
		return fireTypeDataGroup;
	}
	
	private AreaTypeDataVo getCheckReportData(Long areaId, Integer areaType,
			Map<String, Object> param){
		logger.info("getCheckReportData areaId={},areaType+{},param={}",areaId,areaType,param);
		setAreaParameter(areaId, areaType, param);
		AreaTypeDataVo checkReportTypeData = areaMapper.selectCheckReportTypeSum(param);
		return checkReportTypeData;
		
	}
	
	private AreaTypeDataVo getPunishData(Long areaId, Integer areaType,
			Map<String, Object> param){
		logger.info("getPunishData areaId={},areaType+{},param={}",areaId,areaType,param);
		setAreaParameter(areaId, areaType, param);
		AreaTypeDataVo punishTypeData = areaMapper.selectPunishTypeSum(param);
		return punishTypeData;
		
	}
	private AreaTypeDataVo checkVo(AreaTypeDataVo typeDataVo ,String month){
		if(typeDataVo == null || StringUtils.isBlank(typeDataVo.getYear())){
			if(typeDataVo == null) {
				typeDataVo = new AreaTypeDataVo();
			}
			typeDataVo.setYear(month.split("-")[0]);
		}
		return typeDataVo;
	}

	/**
	 * 以上月开始补全areaDataVoList数据到12个月
	 * @param areaDataVoList
	 * @return
	 */
	private List<AreaDataVo> fillMonth(List<AreaDataVo> areaDataVoList){
		//完整12条记录直接返回
		if(areaDataVoList.size() == 12){
			return areaDataVoList;
		}
		int monthNum = 12;
		Map<String, AreaDataVo> monthData = new HashedMap();
		List<AreaDataVo> list = new ArrayList<>();
		for (AreaDataVo areaDataVo : areaDataVoList) {
			monthData.put(areaDataVo.getMonth(),areaDataVo);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH,-13);
		for (int i = 1; i <= monthNum; i++) {
			calendar.add(Calendar.MONTH, 1);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH) + 1;
			String key = year + (month<10?"-0"+month:"-"+month);
			AreaDataVo areaDataVo = monthData.get(key);
			if(areaDataVo == null){
				areaDataVo = new AreaDataVo();
				areaDataVo.setMonth(key);
			}
			list.add(areaDataVo);
		}
		return list;
	}

	private void setAreaParameter(Long areaId, Integer areaType,
			Map<String, Object> param) {
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
	}
	
	
	
	
	private List<AreaCompareData> getFireCompareData(AreaTypeDataVo lastYearData,AreaTypeDataVo thisYearData){
		
		List<AreaCompareData> retLs = new ArrayList<AreaCompareData>();
		
		if( lastYearData == null || thisYearData == null){
			return retLs;
		}
		
		AreaCompareData originalCompare = new AreaCompareData();
		originalCompare.setName("原始火灾警情");
		originalCompare.addData(lastYearData.getYear(),lastYearData.getData1());
		originalCompare.addData(thisYearData.getYear(),thisYearData.getData1());
	
		
		AreaCompareData confirmCompare = new AreaCompareData();
		confirmCompare.setName("确认火灾警情");
		confirmCompare.addData(lastYearData.getYear(),lastYearData.getData2());
		confirmCompare.addData(thisYearData.getYear(),thisYearData.getData2());
		
		AreaCompareData smokeCompare = new AreaCompareData();
		smokeCompare.setName("冒烟火灾警情");
		smokeCompare.addData(lastYearData.getYear(),lastYearData.getData3());
		smokeCompare.addData(thisYearData.getYear(),thisYearData.getData3());
		
		retLs.add(originalCompare);
		retLs.add(confirmCompare);
		retLs.add(smokeCompare);
		
		return retLs;
	}
	
	private List<AreaCompareData> getCheckReportCompareData(AreaTypeDataVo lastYearData,AreaTypeDataVo thisYearData){
		
		List<AreaCompareData> retLs = new ArrayList<AreaCompareData>();
		if( lastYearData == null || thisYearData == null){
			return retLs;
		}
		
		AreaCompareData checkCompare = new AreaCompareData();
		checkCompare.setName("初检数量");
		checkCompare.addData(lastYearData.getYear(),lastYearData.getData1());
		checkCompare.addData(thisYearData.getYear(),thisYearData.getData1());

		AreaCompareData checkPassCompare = new AreaCompareData();
		checkPassCompare.setName("初检通过数量");
		checkPassCompare.addData(lastYearData.getYear(),lastYearData.getData2());
		checkPassCompare.addData(thisYearData.getYear(),thisYearData.getData2());
		
		AreaCompareData recheckCompare = new AreaCompareData();
		recheckCompare.setName("复检数量");
		recheckCompare.addData(lastYearData.getYear(),lastYearData.getData3());
		recheckCompare.addData(thisYearData.getYear(),thisYearData.getData3());
		
		AreaCompareData recheckPassCompare = new AreaCompareData();
		recheckPassCompare.setName("复检通过数量");
		recheckPassCompare.addData(lastYearData.getYear(),lastYearData.getData4());
		recheckPassCompare.addData(lastYearData.getYear(),thisYearData.getData4());
		
		retLs.add(checkCompare);
		retLs.add(checkPassCompare);
		retLs.add(recheckCompare);
		retLs.add(recheckPassCompare);
		return retLs;
	}
	
	private List<AreaCompareData> getPunishCompareData(AreaTypeDataVo lastYearData,AreaTypeDataVo thisYearData){
		
		List<AreaCompareData> retLs = new ArrayList<AreaCompareData>();
		if( lastYearData == null || thisYearData == null){
			return retLs;
		}
		
		AreaCompareData detainedCompare = new AreaCompareData();
		detainedCompare.setName("行政拘留");
		detainedCompare.addData(lastYearData.getYear(),lastYearData.getData1());
		detainedCompare.addData(thisYearData.getYear(),thisYearData.getData1());
		
		AreaCompareData fineCompare = new AreaCompareData();
		fineCompare.setName("行政罚款");
		fineCompare.addData(lastYearData.getYear(),lastYearData.getData2());
		fineCompare.addData(thisYearData.getYear(),thisYearData.getData2());
		
		AreaCompareData fineTotalCompare = new AreaCompareData();
		fineTotalCompare.setName("罚款金额");
		fineTotalCompare.addData(lastYearData.getYear(),lastYearData.getData3());
		fineTotalCompare.addData(thisYearData.getYear(),thisYearData.getData3());
		
		AreaCompareData threeStopCompare = new AreaCompareData();
		threeStopCompare.setName("三停");
		threeStopCompare.addData(lastYearData.getYear(),lastYearData.getData4());
		threeStopCompare.addData(thisYearData.getYear(),thisYearData.getData4());
		
		
		retLs.add(detainedCompare);
		retLs.add(fineCompare);
		retLs.add(fineTotalCompare);
		retLs.add(threeStopCompare);
		
		return retLs;
	}
	
	
	
	
	private AreaDetailVo queryComparisonTypeData(Map<String,Object> param){
		AreaDetailVo detail = new AreaDetailVo();
		//火情柱状图
		AreaTypeDataVo fireTypeData = areaMapper.selectFireTypeSum(param);
		Map<String,Object> fireMap = getFireTypeData(fireTypeData);
		//报告柱状图
		AreaTypeDataVo checkReportTypeData = areaMapper.selectCheckReportTypeSum(param);
		Map<String,Object> checkReportMap = getCheckReportTypeData(checkReportTypeData);
		//执法柱状图
		AreaTypeDataVo punishTypeData = areaMapper.selectPunishTypeSum(param);
		Map<String,Object> punishMap = getPunishTypeData(punishTypeData);
		detail.setFireMap(fireMap);
		detail.setCheckReportMap(checkReportMap);
		detail.setPunishMap(punishMap);
		return detail;
	}
	
	
	private Map<String,Object> getBeginAndEndMonth(){
		Map<String,Object> param = new HashMap<String,Object>();
		Date curDate = new Date();
		Date lastYearDate = DateUtils.addMonths(curDate,-12);
		String curDateStr = DateFormatUtil.getYearAndMonth(curDate);
		String lastDateStr = DateFormatUtil.getYearAndMonth(lastYearDate);
		param.put("monthBegin", lastDateStr);
		param.put("monthEnd", curDateStr);
		return param;
	}
	
	private Map<String,Object> getThisYeay(){
		Map<String,Object> param = new HashMap<String,Object>();
		Date curDate = new Date();
		String curYear = DateFormatUtil.getYear(curDate);
		param.put("monthBegin", curYear+"-01");
		param.put("monthEnd", DateFormatUtil.getYearAndMonth(curDate));
//		param.put("year", curYear);
		return param;
	}
	
	private Map<String,Object> getLastYear(){
		Map<String,Object> param = new HashMap<String,Object>();
		Date curDate = new Date();
		Date lastYearDate = DateUtils.addMonths(curDate,-12);
		String lastYear = DateFormatUtil.getYear(lastYearDate);
		param.put("monthBegin", lastYear+"-01");
		param.put("monthEnd", DateFormatUtil.getYearAndMonth(lastYearDate));
//		param.put("year", lastYear);
		return param;
	}
	
	
	
	private Map<String,Object> getFireTypeData(AreaTypeDataVo fireTypeData){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("origalSum", fireTypeData.getData1());
		retMap.put("confirmSum", fireTypeData.getData2());
		retMap.put("smokeSum", fireTypeData.getData3());
		return retMap;
	}
	
	private Map<String,Object> getCheckReportTypeData(AreaTypeDataVo checkReportTypeData){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("checkSum", checkReportTypeData.getData1());
		retMap.put("checkPassSum", checkReportTypeData.getData2());
		retMap.put("recheckSum", checkReportTypeData.getData3());
		retMap.put("reCheckPassSum", checkReportTypeData.getData4());
		return retMap;
	}
	
	private Map<String,Object> getPunishTypeData(AreaTypeDataVo punishTypeData){
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("checkSum", punishTypeData.getData1());
		retMap.put("checkPassSum", punishTypeData.getData2());
		retMap.put("recheckSum", punishTypeData.getData3());
		return retMap;
	}


	
	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryAreaDetail_' + #areaId + '_' + #areaType + '_' + #code")
	})
	public AreaDetailResult queryAreaDetail(Long areaId, Integer areaType, String code, Long userId){
		AreaDetailResult ret = new AreaDetailResult();
		
		AreaVo areaVo =  queryAreaBase(areaId,areaType,code,userId);
		if( areaVo == null ){
			return null;
		}
		
		AreaTrendVo areaTrend = queryTrendReport(areaVo.getAreaId(),areaVo.getAreaType());
		if( areaTrend != null){
			areaVo.setAreaTrend(areaTrend);
		}
		ret.setArea(areaVo);

		//火情对比
		List<AreaTypeDataVo> fireList = queryFireCompareData(areaVo.getAreaId(),areaVo.getAreaType());
		AreaTypeResultVo fireResult = new AreaTypeResultVo("火情数据对比",fireList);
		ret.setFireResult(fireResult);
		//报告对比
//		List<AreaTypeDataVo> reportList = queryCheckReportCompareData(areaVo.getAreaId(),areaVo.getAreaType());
//		AreaTypeResultVo reportResult = new AreaTypeResultVo("报告数据对比",reportList);
//	    ret.setReportResult(reportResult);
		//处罚对比
		List<AreaTypeDataVo> punishList = queryPunishCompareData(areaVo.getAreaId(),areaVo.getAreaType());
		AreaTypeResultVo punishResult = new AreaTypeResultVo("执法数据对比",punishList);
		ret.setPunishResult(punishResult);
		//建筑分类统计结果
		List<CountValueData> typeCountList = queryBuildingTypeCount(areaVo.getAreaId(),areaVo.getAreaType());
		ret.setTypeCount(typeCountList);
		
		//获取用户是否关注
		
		return ret;
	}
	
	public AreaVo queryAreaBase(Long areaId, Integer areaType, String code, Long userId) {
		// TODO Auto-generated method stub
		List<AreaVo> list = null;
		if( StringUtils.isNotBlank(code)){
			list = queryDetailByCode(code);
		}else{
			list = queryDetailById(areaId,areaType);
		}
		if( CollectionUtils.isNotEmpty(list)){
			AreaVo vo = list.get(0);
			int num = userConcernAreaService.isConcerned(vo.getAreaType(),vo.getAreaId(),userId);
			vo.setConcerned(num>0?1:0);
			return vo;
		}
		return null;
	}
	
	private List<CountValueData> queryBuildingTypeCount(Long areaId,Integer areaType){
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( areaType == 0 ){
			param.put("districtId", areaId);
		}else if( areaType == 1 ){
			param.put("streetId", areaId);
		}else if( areaType == 2 ){
			param.put("blockId", areaId);
		}
		return buildingInfoService.queryBuildingTypeCount(param);
	}
	
	
	
	private List<AreaVo> queryDetailByCode(String code){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("code", code);
		return areaMapper.selectArea(param);
	}
	
	private List<AreaVo> queryDetailById(Long areaId, Integer areaType){
		if( areaId ==null ){
			return null;
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("id",areaId);
		if( areaType == 0 ){
			return areaMapper.selectDistrict(param);
			
		}else if( areaType ==1){
			return areaMapper.selectStreet(param);
			
		}else if( areaType ==2){
			return areaMapper.selectBlock(param);
		}
		return null;
	}



	@Override
	public List<AreaRiskLevelVo> queryAreaRiskLevel(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<AreaRiskLevelVo> list = areaMapper.selectAreaRiskLevel(param);
		if( CollectionUtils.isEmpty(list)){
			return list;
		}
        
		for( AreaRiskLevelVo vo :list){
			String center = vo.getAreaCenter();
			Latlng latlng = LatlngUtil.getLatlng(center);
			vo.setCenter(latlng);
		}
		return list;
	}

	

	
	

	
	
	



}
