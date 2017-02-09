package org.fire.platform.modules.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.service.IAreaService;
import org.fire.platform.modules.check.domain.CheckItemResultBak;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.service.ICheckItemResultService;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.check.vo.CheckReportDetailVo;
import org.fire.platform.modules.check.vo.CheckReportVo;
import org.fire.platform.modules.event.bean.FireEventBean;
import org.fire.platform.modules.event.bean.PunishEventBean;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.service.IFireEventService;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.event.vo.FireEventVo;
import org.fire.platform.modules.event.vo.PunishEventVo;
import org.fire.platform.modules.front.vo.AreaRiskLevelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/query")
public class QueryInfoController {
	
	@Autowired
	private IFireEventService fireEventService;
	@Autowired
	private IPunishEventService punishEventService;
	@Autowired
	private ICheckReportService checkReportService;
	@Autowired
	private ICheckItemResultService checkItemResultService;
	
	@Autowired
	private IAreaService areaService;
	
	
	
	/**
	 * 用于火情数据列表及其详情
	 * @param streetId
	 * @param blockId
	 * @param fireType
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/fireEvent", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryFireEvent(
			@RequestParam(required = false) Long districtId,
			@RequestParam(required = false) Long streetId,
			@RequestParam(required = false) String fireType,
			@RequestParam(required = false) String name,
			@RequestParam(required =false) String date,
			@RequestParam(required =false) String occurEndMonth,
			@RequestParam(required =false) String occurStartMonth,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum,
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){

		Map<String,Object> param = new HashMap<String,Object>();
		
		if( districtId != null && districtId > 0L ){
			param.put("districtId", districtId);
		}
		
		if( streetId !=null && streetId > 0L){
			param.put("streetId", streetId);
		}
		
		if( StringUtils.isNotBlank(fireType)){
			param.put("fireType", fireType);
		}
		
		if( StringUtils.isNotBlank(name)){
			param.put("nameLike", name);
		}
		
		if( StringUtils.isNotBlank(date)){
			param.put("occurTime", date);
		}
		if( StringUtils.isNoneBlank(occurStartMonth)){
			param.put("occurStartMonth",occurStartMonth);
		}
		if( StringUtils.isNoneBlank(occurEndMonth)){
			param.put("occurEndMonth",occurEndMonth);
		}

		
		return queryFireEvent(param,pageNum,pageSize);
	}
	
	public CommonResult queryFireEvent(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		PageHelper.startPage(pageNum, pageSize);
		List<FireEventVo> list = fireEventService.queryfireEvent(params);
		PageInfo<FireEventVo> page = PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	
	@RequestMapping(value="/fireEventDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getFireEventDetail(
			@RequestParam(value = "fireEventId") Long fireEventId){
		//TODO
		Map<String, Object> map = new HashMap<String, Object>();
		if (fireEventId != null) {
			map.put("id", fireEventId+"");
		}
		FireEvent fireEvent = null;
		List<FireEventBean> fires = fireEventService.queryBeanByMap(map);
		if (fires != null  && fires.size() > 0) {
			fireEvent = fires.get(0);
		}else{
			return CommonResult.fail("数据详情未查到");
		}
		return CommonResult.success(fireEvent);
//		FireEventFullVo fireEventVo = fireEventService.getVo(fireEventId);
//		return CommonResult.success(fireEventVo);
	}
	
	
	@RequestMapping(value="/punishEventDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult punishEventDetail(
			@RequestParam(value = "punishEventId") Long punishEventId){
		//TODO
		Map<String, Object> map = new HashMap<String, Object>();
		if (punishEventId != null) {
			map.put("id", punishEventId+"");
		}
		PunishEventBean punishEventBean = null;
		List<PunishEventBean> punishs = punishEventService.queryBeanByMap(map);
		if (punishs != null  && punishs.size() > 0) {
			punishEventBean = punishs.get(0);
		}else{
			return CommonResult.fail("数据详情未查到");
		}
		return CommonResult.success(punishEventBean);
	}

	public CommonResult getFireEventDetail(
			@RequestParam(required = false) Map<String,Object> params){
		
		Long fireEventId = (Long)params.get("fireEventId");	
		FireEvent fireEvent = fireEventService.get(fireEventId);
		return CommonResult.success(fireEvent);
	}
	
	@RequestMapping(value="/checkReport", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryCheckReport(
			@RequestParam(required =false) Long districtId,
			@RequestParam(required =false) Long streetId,
			@RequestParam(required =false)  String name,
			@RequestParam(required =false) String reportType,
			@RequestParam(required =false) String date,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( districtId != null && districtId > 0L ){
			param.put("districtId", districtId);
		}

		if( streetId !=null && streetId > 0L){
			param.put("streetId", streetId);
		}
		
		if( StringUtils.isNotBlank(name)){
			param.put("nameLike", name);
		}
		
		if( StringUtils.isNotBlank(reportType)){
			param.put("reportType", reportType);
		}
		
		if( StringUtils.isNotBlank(date)){
			param.put("pubTime", date);
		}
		
		return queryCheckReport(param,pageNum,pageSize);
	}
	
	public CommonResult queryCheckReport(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		PageHelper.startPage(pageNum, pageSize);
		List<CheckReportVo> list = checkReportService.queryCheckReport(params);
		PageInfo<CheckReportVo> page = PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	@RequestMapping(value="/reportDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult reportDetail(
			@RequestParam(value = "reportId") Long reportId){
		
		//TODO 获取报告基本信息
		CheckReportDetailVo checkReportDetail = checkReportService.getDetail(reportId);
		if( checkReportDetail == null ){
			return CommonResult.fail("没有查到报告详情");
		}

		//TODO 获取报告检测条目
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("reportId", reportId);
		param.put("isPass",1); //未通过
		List<CheckItemResultBak> itemLs = checkItemResultService.queryByMap(param);
		checkReportDetail.setItemLs(itemLs);
		//TODO 获取其他历史报告
		
		Long buildingId = checkReportDetail.getBuildingId();
		if( buildingId == null || buildingId <=0){
			return CommonResult.success(checkReportDetail);
		}
		param.clear();
		
		param.put("buildingId", buildingId);
		param.put("notEqualId", reportId);
		List<CheckReportVo> relatReportList = checkReportService.queryCheckReport(param);
		checkReportDetail.setRelatReportList(relatReportList);
		
		return CommonResult.success(checkReportDetail);

	}
	
	public CommonResult reportDetail(
			@RequestParam(required = false) Map<String,Object> params){
		
		Long reportId = (Long) params.get("reportId");
		return reportDetail(reportId);
	}
	
	
	/**
	 * @deprecated
	 * @param buildingId
	 * @return
	 */
	@RequestMapping(value="/buildingReportHistory", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getBuildingReportHistory(
			@RequestParam(value = "buildingId") Long buildingId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("buildingId", buildingId);
		
		List<CheckReport> list = checkReportService.queryByMap(param);
		return CommonResult.success(list);
	}
	
	@RequestMapping(value="/punishEvent", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryPunishEvent(
			@RequestParam(required = false) Long districtId,
			@RequestParam(required = false) Long streetId,
			@RequestParam(required = false) String punishType,
			@RequestParam(required = false) String name,
			@RequestParam(required =false) String date,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( districtId != null && districtId > 0L ){
			param.put("districtId", districtId);
		}
		
		if( streetId !=null && streetId > 0L){
			param.put("streetId", streetId);
		}
		
		if( StringUtils.isNotBlank(punishType) ){
			param.put("punishType", punishType);
		}
		
		if( StringUtils.isNotBlank(name)){
			param.put("nameLike", name);
		}
		
		if( StringUtils.isNotBlank(date)){
			param.put("punishTime", date);
		}
		
		return queryPunishEvent(param,pageNum,pageSize);
	}
	
	public CommonResult queryPunishEvent(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		PageHelper.startPage(pageNum, pageSize);
		List<PunishEventVo> list =  punishEventService.queryPunishEvent(params);
		PageInfo<PunishEventVo> page= PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	@RequestMapping(value="/queryAreaRiskIndex", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryAreaRiskIndex(
			@RequestParam(required = false) Long districtId){
		Map<String,Object> param = new HashMap<String,Object>();
		if(districtId != null) {
			param.put("districtId", districtId);
		}
		List<AreaRiskLevelVo> list = areaService.queryAreaRiskLevel(param);
		return CommonResult.success(list);
	}

}
