package org.fire.platform.modules.data.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.fire.platform.modules.area.dao.AreaMapper;
import org.fire.platform.modules.area.dao.StreetMapper;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.check.dao.CheckClassMapper;
import org.fire.platform.modules.check.dao.CheckReportMapper;
import org.fire.platform.modules.check.domain.CheckClass;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.data.dao.DataHandlerMapper;
import org.fire.platform.modules.data.service.IDataHandler;
import org.fire.platform.modules.data.vo.AreaSumDataVo;
import org.fire.platform.modules.data.vo.ReportStatisVo;
import org.fire.platform.modules.data.vo.SumDataVo;
import org.fire.platform.modules.statis.bean.ReportStatisBean;
import org.fire.platform.modules.statis.dao.CheckReportSumMapper;
import org.fire.platform.modules.statis.dao.FireEventSumMapper;
import org.fire.platform.modules.statis.dao.PunishEventSumMapper;
import org.fire.platform.modules.statis.dao.ReportAnalysisMapper;
import org.fire.platform.modules.statis.dao.StatisMapper;
import org.fire.platform.modules.statis.domain.CheckReportSum;
import org.fire.platform.modules.statis.domain.FireEventSum;
import org.fire.platform.modules.statis.domain.PunishEventSum;
import org.fire.platform.modules.statis.domain.ReportAnalysis;
import org.fire.platform.util.DateFormatUtil;
import org.fire.platform.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataHandlerImpl implements IDataHandler{

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	DataHandlerMapper dataHandlerMapper;

	@Autowired
	CheckReportSumMapper checkReportSumMapper;

	@Autowired
	FireEventSumMapper fireEventSumMapper;

	@Autowired
	PunishEventSumMapper punishEventSumMapper;

	@Autowired
	StatisMapper statisMapper;

	@Autowired
	ReportAnalysisMapper reportAnalysisMapper;

	@Autowired
	CheckClassMapper checkClassMapper;

	@Autowired
	StreetMapper streetMapper;

	@Autowired
	CheckReportMapper checkReportMapper;


	@Override
	public void sumCheckReportData(Map<String,Object> param) {
		//TODO 处理历史数据
		removeReportHistData(param);
		//TODO
		sumCheckReport(param);

	}

	@Override
	public void sumFireEventData(Map<String,Object> param) {
		//TODO 处理历史数据
		removeFireEventHistData(param);

		//TODO
		sumFireEvent(param);

	}

	@Override
	public void sumPunishEventData(Map<String,Object> param) {
		// TODO Auto-generated method stub
		removePunishEventHistData(param);

		sumPunishEvent(param);

	}

	@Override
	public String sumReportAnalysis(Long reportId) {
		// TODO Auto-generated method stub
		removeReportAnalysisHistData(reportId);
        return sumReportAnalysisData(reportId);
	}

	private void removeReportHistData(Map<String,Object> param){
		//TODO 删除历史数据
		logger.info("begin delete repor hist data。param={}",param);
		dataHandlerMapper.deleteReportSumByParam(param);
	}

	private void removeFireEventHistData(Map<String,Object> param){
		logger.info("begin delete fire Event hist data。param={}",param);
		dataHandlerMapper.deleteFireEventSumByParam(param);
	}

	private void removePunishEventHistData(Map<String,Object> param){
		logger.info("begin delete Punish Event hist data。param={}",param);
		dataHandlerMapper.deletePunishEventSumByParam(param);
	}

	private void removeReportAnalysisHistData(Long reportId){
		//TODO
		logger.info("begin delete report Analysis hist data。reportId={}",reportId);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("reportId", reportId);
		reportAnalysisMapper.deleteByParam(param);

	}

	private void sumCheckReport(Map<String,Object> param){
		List<SumDataVo> list = dataHandlerMapper.selectReportSum(param);
		if( CollectionUtils.isEmpty(list)){
			logger.warn("没有获取到检测报告数据,param={}",param);
			return;
		}

		Map<String,CheckReportSum> resultMap = new HashMap<String, CheckReportSum>();

		for(SumDataVo data:list){
			genCheckReportBean(resultMap,data);

		}
		//循环插入
		insertReportSum(resultMap);
	}

	private void sumFireEvent(Map<String,Object> param){
		List<SumDataVo> list = dataHandlerMapper.selectFireEventSum(param);
		if( CollectionUtils.isEmpty(list)){
			logger.warn("没有获取到检测报告数据,param={}",param);
			return;
		}
		Map<String,FireEventSum> resultMap = new HashMap<String, FireEventSum>();

		for(SumDataVo data:list){
			genFireEventBean(resultMap,data);
		}
		//循环插入
		insertFireEventSum(resultMap);
	}

	private void sumPunishEvent(Map<String,Object> param){
		List<SumDataVo> list = dataHandlerMapper.selectPunishEventSum(param);
		if( CollectionUtils.isEmpty(list)){
			logger.warn("没有获取到检测报告数据,param={}",param);
			return;
		}
		Map<String,PunishEventSum> resultMap = new HashMap<String, PunishEventSum>();

		for(SumDataVo data:list){
			genPunishEventBean(resultMap,data);
		}
		//循环插入
		insertPunishEventSum(resultMap);
	}

	private String sumReportAnalysisData(Long reportId){
		//TODO
		Map<String,Object> param = new HashMap<String,Object>();
    	param.put("reportId", reportId);
	    //TODO 获取报告项
    	List<ReportStatisBean> list = statisMapper.selectReportItemSum(param);
    	if( CollectionUtils.isEmpty(list)){
    	    logger.warn("根据报告Id没有获取到报告项,reportId={}",reportId);
    	    return null;
    	 }
    	 Map<String,ReportStatisVo> resultMap = new HashMap<String,ReportStatisVo>();
    	 for(ReportStatisBean data:list){
    		 genReportAnalysisMap(resultMap,data);
 		 }


    	 //TODO 把Map转为Bean
    	 ReportAnalysis bean = getReportAnalysis(resultMap,reportId);

    	 calcTotal(bean);

    	 //TODO 插入记录
    	 reportAnalysisMapper.insert(bean);

    	 //更新字段
    	 CheckReport checkReport = checkReportMapper.selectByPrimaryKey(bean.getReportId());
    	 checkReport.setRiskIndex(bean.getRiskLevel());
    	 int num = 0;
         num += bean.getCheckUnqualified()==null?0:bean.getCheckUnqualified();
         num += bean.getDangerUnqualified()==null?0:bean.getDangerUnqualified();
         num += bean.getEvacuateUnqualified()==null?0:bean.getEvacuateUnqualified();
         num += bean.getFacilitiesUnqualified()==null?0:bean.getFacilitiesUnqualified();
         num += bean.getImportantUnqualified()==null?0:bean.getImportantUnqualified();
         num += bean.getManageUnqualified()==null?0:bean.getManageUnqualified();
    	 checkReport.setUnpassNum(num);
    	 checkReportMapper.updateByPrimaryKey(checkReport);
    	 return bean.getRiskLevel();
	}

	private void calcTotal(ReportAnalysis bean){
		 //TODO 计算总分
   	     List<CheckClass> list = checkClassMapper.selectAll();
   	     if( CollectionUtils.isEmpty(list)){
   	    	 return;
   	     }
   	     Double total = 0.0;
   	     for( CheckClass vo:list){
   	    	 String code = vo.getCode();
   	    	 Double weight = vo.getWeight();
   	    	 if( weight == null){
   	    		 break;
   	    	 }

   	    	 if( code.equalsIgnoreCase("B1") && bean.getFacilitiesUnqualifiedRatio() != null){
   	    		 total += bean.getFacilitiesUnqualifiedRatio() * weight;
   	    	 }else if(code.equalsIgnoreCase("B2") && bean.getEvacuateUnqualifiedRatio() != null ){
   	    		total += bean.getEvacuateUnqualifiedRatio() * weight;
   	    	 }else if(code.equalsIgnoreCase("B3") && bean.getManageUnqualifiedRatio() != null){
   	    		total += bean.getManageUnqualifiedRatio() * weight;
   	    	 }else if(code.equalsIgnoreCase("B4")){
   	    		total += bean.getDangerUnqualifiedRatio() * weight;
   	    	 }else if(code.equalsIgnoreCase("B5") && bean.getImportantUnqualifiedRatio() !=null){
   	    		total += bean.getImportantUnqualifiedRatio() * weight;
   	    	 }else if(code.equalsIgnoreCase("B6") && bean.getCheckUnqualifiedRatio() !=null ){
   	    		total += bean.getCheckUnqualifiedRatio() * weight;
   	    	 }
   	     }
   	     bean.setTotalUnqualified(total);
   	     if(total <= 0.15 ){
   	    	 bean.setRiskLevel("low");
   	     }else if( total > 0.15 && total <= 0.35){
   	    	bean.setRiskLevel("middle");
   	     }else if( total > 0.35 && total <= 0.75){
   	    	bean.setRiskLevel("high");
   	     }else if( total > 0.75 ){
   	    	bean.setRiskLevel("veryHigh");
   	     }
	}

	private void genReportAnalysisMap(Map<String,ReportStatisVo> map,ReportStatisBean data){
		ReportStatisVo bean = null;
		String key = data.getItemSort();

		bean = map.get(key);
		if( bean == null ){
			bean = new ReportStatisVo();
			bean.setItemSort(key);
		}

		if( data.getIsPass() != 0){//检测总数
			bean.setTotalNum(bean.getTotalNum()+data.getCount());
		}

		if( data.getIsPass() == 2 ){//通过
			bean.setPassNum(bean.getPassNum()+data.getCount());
		}

		map.put(key, bean);

	}

	private ReportAnalysis getReportAnalysis(Map<String,ReportStatisVo> map,Long reportId){
		ReportAnalysis bean = new ReportAnalysis();
		bean.setReportId(reportId);
		bean.setCreator(1L);
		bean.setCreateTime(new Date());

		Iterator<String> iter = map.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			ReportStatisVo vo = map.get(key);
			Integer unPassNum = vo.getTotalNum()-vo.getPassNum();
			Double unPassRatio = unPassNum.doubleValue()/vo.getTotalNum();
			if( vo.getItemSort().equalsIgnoreCase("B1")){
				bean.setFacilitiesUnqualified(unPassNum);
				bean.setFacilitiesUnqualifiedRatio(unPassRatio);
			}else if( vo.getItemSort().equalsIgnoreCase("B2")){
				bean.setEvacuateUnqualified(unPassNum);
				bean.setEvacuateUnqualifiedRatio(unPassRatio);
			}else if( vo.getItemSort().equalsIgnoreCase("B3")){
				bean.setManageUnqualified(unPassNum);
				bean.setManageUnqualifiedRatio(unPassRatio);
			}else if( vo.getItemSort().equalsIgnoreCase("B4")){
				bean.setDangerUnqualified(unPassNum);
				bean.setDangerUnqualifiedRatio(unPassRatio);
			}else if( vo.getItemSort().equalsIgnoreCase("B5")){
				bean.setImportantUnqualified(unPassNum);
				bean.setImportantUnqualifiedRatio(unPassRatio);
			}else if( vo.getItemSort().equalsIgnoreCase("B6")){
				bean.setCheckUnqualified(unPassNum);
				bean.setCheckUnqualifiedRatio(unPassRatio);
			}

		}

		//计算总体分数

		return bean;
	}


	private void genCheckReportBean(Map<String,CheckReportSum> map,SumDataVo data){
		CheckReportSum bean = null;
		String key = genereateMapkey(data);

		bean = map.get(key);
		if( bean == null ){
			bean = new CheckReportSum();
			bean.setDistrictId(data.getDistrictId());
			bean.setStreetId(data.getStreetId());
			bean.setBlockId(data.getBlockId());
			bean.setYear(data.getYear());
			bean.setMonth(data.getMonth());
		}
		//增加bean中的值
		if( data.getType1().equalsIgnoreCase("check") && data.getType2().equalsIgnoreCase("1")){ //初检通过
			bean.setCheckPassNum(data.getCount());
			if( bean.getCheckNum() != null){
				bean.setCheckNum(bean.getCheckNum()+data.getCount());
			}else{
				bean.setCheckNum(data.getCount());
			}
		}else if(data.getType1().equalsIgnoreCase("check") && data.getType2().equalsIgnoreCase("0")){//初检未通过
			if( bean.getCheckNum() != null){
				bean.setCheckNum(bean.getCheckNum()+data.getCount());
			}else{
				bean.setCheckNum(data.getCount());
			}
		}else if(data.getType1().equalsIgnoreCase("recheck") && data.getType2().equalsIgnoreCase("1")){//复检通过
			bean.setRecheckPassNum(data.getCount());
			if( bean.getRecheckNum() != null){
				bean.setRecheckNum(bean.getRecheckNum()+data.getCount());
			}else{
				bean.setRecheckNum(data.getCount());
			}

		}else if(data.getType1().equalsIgnoreCase("recheck") && data.getType2().equalsIgnoreCase("0")){//复检未通过
			if( bean.getRecheckNum() != null){
				bean.setRecheckNum(bean.getRecheckNum()+data.getCount());
			}else{
				bean.setRecheckNum(data.getCount());
			}
		}
		map.put(key, bean);

	}


	private void genFireEventBean(Map<String,FireEventSum> map,SumDataVo data){
		FireEventSum bean = null;
		String key = genereateMapkey(data);

		bean = map.get(key);
		if( bean == null ){
			bean = new FireEventSum();
			bean.setDistrictId(data.getDistrictId());
			bean.setStreetId(data.getStreetId());
			bean.setBlockId(data.getBlockId());
			bean.setYear(data.getYear());
			bean.setMonth(data.getMonth());
		}
		//增加bean中的值
		if( data.getType1().equalsIgnoreCase("original")){ //初检通过
			bean.setOriginalFireNum(data.getCount());

		}else if(data.getType1().equalsIgnoreCase("confirm")){//初检未通过
			bean.setConfirmFireNum(data.getCount());
		}else if(data.getType1().equalsIgnoreCase("smoke")){//复检通过
			bean.setSmokeFireNum(data.getCount());
		}
		map.put(key, bean);

	}

	private void genPunishEventBean(Map<String,PunishEventSum> map,SumDataVo data){
		PunishEventSum bean = null;
		String key = genereateMapkey(data);

		bean = map.get(key);
		if( bean == null ){
			bean = new PunishEventSum();
			bean.setDistrictId(data.getDistrictId());
			bean.setStreetId(data.getStreetId());
			bean.setBlockId(data.getBlockId());
			bean.setYear(data.getYear());
			bean.setMonth(data.getMonth());
		}
		//增加bean中的值
		if( data.getType1().equalsIgnoreCase("detained")){ //行政拘留
			bean.setDetainedNum(data.getCount());
		}else if(data.getType1().equalsIgnoreCase("fine")){//行政罚款
			bean.setFineNum(data.getCount());
			bean.setFineTotal(data.getFineTotal());
		}else if(data.getType1().equalsIgnoreCase("three_stop")){//三停
			bean.setThreeStopNum(data.getCount());
		}else if(data.getType1().equalsIgnoreCase("close_down")) {//查封
			bean.setCloseDownNum(data.getCount());
		}
		map.put(key, bean);

	}

	private String genereateMapkey(SumDataVo data){
		//TODO
		return data.getDistrictId()+"_"+data.getStreetId()+"_"+data.getBlockId()+"_"+data.getYear()+"_"+data.getMonth();

	}

	private void insertReportSum(Map<String,CheckReportSum> map){
		//TODO
		if( map.isEmpty()){
			return;
		}

		Iterator<String> iter = map.keySet().iterator();
		while( iter.hasNext()){
			String key = iter.next();
			CheckReportSum bean = map.get(key);
			// 如果没数据则设定默认值 0
			if (bean.getCheckNum() == null) {
				bean.setCheckNum(0);
			}
			if (bean.getRecheckNum() == null) {
				bean.setRecheckNum(0);
			}
			if (bean.getCheckPassNum() == null) {
				bean.setCheckPassNum(0);
			}
			if (bean.getRecheckPassNum() == null) {
				bean.setRecheckPassNum(0);
			}
			bean.setCreateUser(0L);
			bean.setCreateDate(new Date());
			checkReportSumMapper.insert(bean);
		}
	}

	private void insertFireEventSum(Map<String,FireEventSum> map){
		//TODO
		if( map.isEmpty()){
			return;
		}

		Iterator<String> iter = map.keySet().iterator();
		while( iter.hasNext()){
			String key = iter.next();
			FireEventSum bean = map.get(key);
			// 如果没数据则设定默认值 0
			if (bean.getOriginalFireNum() == null) {
				bean.setOriginalFireNum(0);
			}
			if (bean.getConfirmFireNum() == null) {
				bean.setConfirmFireNum(0);
			}
			if(bean.getSmokeFireNum() == null){
				bean.setSmokeFireNum(0);
			}

			bean.setCreateUser(0L);
			bean.setCreateDate(new Date());
			fireEventSumMapper.insert(bean);
		}
	}

	private void insertPunishEventSum(Map<String,PunishEventSum> map){
		//TODO
		if( map.isEmpty()){
			return;
		}

		Iterator<String> iter = map.keySet().iterator();
		while( iter.hasNext()){
			String key = iter.next();
			PunishEventSum bean = map.get(key);
			// 如果没数据则设定默认值 0
			if (bean.getDetainedNum() == null) {
				bean.setDetainedNum(0);
			}
			if (bean.getFineNum() == null) {
				bean.setFineNum(0);
			}
			if (bean.getThreeStopNum() == null) {
				bean.setThreeStopNum(0);
			}
			if (bean.getCloseDownNum() == null) {
				bean.setCloseDownNum(0);
			}
			if (bean.getFineTotal()  == null) {
				bean.setFineTotal(0.0);
			}
			bean.setCreateUser(0L);
			bean.setCreateDate(new Date());
			punishEventSumMapper.insert(bean);
		}
	}

	@Override
	public void calcStreetSafeIndex(Long districtId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		Date curDate = new Date();
		Date lastMonth = DateUtils.addMonths(curDate,-1);
		String lastDateStr = DateFormatUtil.getYearAndMonth(lastMonth);
		param.put("monthBegin", lastDateStr);
		param.put("districtId", districtId);

		// TODO 获取最近一个月的该行政区街道火情数目
		List<AreaSumDataVo> fireList = dataHandlerMapper.selectStreetFireEventSum(param);
		List<AreaSumDataVo> punishList = dataHandlerMapper.selectStreetPunishEventSum(param);
//		List<AreaSumDataVo> reportList = dataHandlerMapper.selectStreetReportSum(param);

		Map<Long,AreaSumDataVo> retMap = new HashMap<Long,AreaSumDataVo>();

		for(AreaSumDataVo vo :fireList ){
			retMap.put(vo.getStreetId(), vo);
		}

		for( AreaSumDataVo vo: punishList){
			AreaSumDataVo data = retMap.get(vo.getStreetId());
			if( vo.getPunishCount() != null){
				data.setPunishCount(vo.getPunishCount());
			}
		}

//		for( AreaSumDataVo vo: reportList){
//			AreaSumDataVo data = retMap.get(vo.getStreetId());
//			if( vo.getReportCount() != null){
//				data.setReportCount(vo.getReportCount());
//			}
//		}

		//TODO 对Map数据进行处理修改区域信息
		Iterator<Long> iter = retMap.keySet().iterator();
		while(iter.hasNext()){
			Long key = iter.next();
			AreaSumDataVo data = retMap.get(key);
			int index = data.calcAreaSafeIndex();
//			if( index >=3 ){
//				index = 3;
//			}
			if( index >=2 ){
				index = 2;
			}

			Street street = new Street();
			street.setId(key);
			street.setLatestRiskIndex(index);
			street.setModDate(new Date());
			street.setModUser(0L);
			streetMapper.updateByPrimaryKey(street);

		}

	}

	@Override
	public void calcBlockSafeIndex(Long streetId) {
		// TODO Auto-generated method stub

	}



}
