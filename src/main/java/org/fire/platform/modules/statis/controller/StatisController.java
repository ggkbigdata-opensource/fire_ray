package org.fire.platform.modules.statis.controller;

import java.util.HashMap;
import java.util.Map;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.area.constants.AreaConstants;
import org.fire.platform.modules.statis.service.IStatisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/statis")
public class StatisController {

	
	@Autowired
	private IStatisService statisService; 
	
	private static Logger log = LoggerFactory.getLogger(StatisController.class);
	
	
	@RequestMapping(value = "/getFireReasonTypePercent")
	@ResponseBody
	public CommonResult  getFireReasonTypePercent(
			@RequestParam(required = false) String fireType,
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd,
			@RequestParam(required = false) String year,
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		log.info("getFireReasonTypePercent,areaId={},areaType={}",areaId,areaType);
	    Map<String,Object> param = new HashMap<String,Object>();
		if (StringUtils.hasText(fireType) && !fireType.equals("0")) {
			param.put("fireType", fireType);
		}
		if (StringUtils.hasText(monthBegin)) {
			param.put("monthBegin", monthBegin);
		}
		if (StringUtils.hasText(monthEnd)) {
	       	param.put("monthEnd", monthEnd);
		}

       	param.put("year", year);
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
	    return 	CommonResult.success(statisService.queryFireReasonTypePercent(param));
	}
	
	@RequestMapping(value = "/getPlaceUseTypePercent")
	@ResponseBody
	public CommonResult  getPlaceUseTypePercent(
			@RequestParam(required = false) String fireType,
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd,
			@RequestParam(required = false) String year,
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		log.info("getPlaceUseTypePercent,areaId={},areaType={}",areaId,areaType);
	    Map<String,Object> param = new HashMap<String,Object>();
		if (StringUtils.hasText(fireType) && !fireType.equals("0")) {
			param.put("fireType", fireType);
		}
		if (StringUtils.hasText(monthBegin)) {
			param.put("monthBegin", monthBegin);
		}
		if (StringUtils.hasText(monthEnd)) {
	       	param.put("monthEnd", monthEnd);
		}

       	param.put("year", year);
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
	    return 	CommonResult.success(statisService.queryPlaceUseTypePercent(param));
	}
	/**
	 * 由于建筑为非必填，故废弃使用
	 * @param fireType
	 * @param monthBegin
	 * @param monthEnd
	 * @param year
	 * @param areaId
	 * @param areaType
	 * @return
	 */
	@RequestMapping(value = "/getFirePlaceTypePercent")
	@ResponseBody
	public CommonResult  getFirePlaceTypePercent(
			@RequestParam(required = false) String fireType,
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd,
			@RequestParam(required = false) String year,
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
	    log.info("getFirePlaceTypePercent,areaId={},areaType={}",areaId,areaType);
	    Map<String,Object> param = new HashMap<String,Object>();
		if (StringUtils.hasText(monthBegin)) {
			param.put("monthBegin", monthBegin);
		}
		if (StringUtils.hasText(monthEnd)) {
	       	param.put("monthEnd", monthEnd);
		}
		if (StringUtils.hasText(monthBegin)) {
			param.put("monthBegin", monthBegin);
		}
		if (StringUtils.hasText(monthEnd)) {
	       	param.put("monthEnd", monthEnd);
		}
       	param.put("year", year);
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
	    return 	CommonResult.success(statisService.queryFirePlaceTypePercent(param));
	}
	
	@RequestMapping(value = "/getReportLevelPercent")
	@ResponseBody
	public CommonResult  getReportLevelPercent(
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd,
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
	    log.info("getReportLevelPercent,areaId={},areaType={}",areaId,areaType);
	    Map<String,Object> param = new HashMap<String,Object>();
    	
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
		
		if( monthBegin != null ){
			param.put("monthBegin", monthBegin);
		}
		
		if( monthEnd != null ){
			param.put("monthEnd", monthEnd);
		}
		
	    return 	CommonResult.success(statisService.queryReportLevelPercent(param));
	}
}
