package org.fire.platform.modules.front.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.vo.RequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/facade")
public class RequestFacadeController {
	
	private static String AREA_QUERY_CONCERN_AREA = "app/areaInfo/queryConcernArea";
	private static String AREA_QUERY_DISTRICT = "app/areaInfo/queryDistrict";
	private static String AREA_QUERY_STREET = "app/areaInfo/queryStreet";
	private static String AREA_QUERY_AREA = "app/areaInfo/queryArea";
	private static String AREA_QUERY_TREND_REPORT = "app/areaInfo/trendReport";
	private static String AREA_COMPARE_FIRE = "app/areaInfo/compareFire";
	private static String AREA_COMPARE_CHECK_REPORT = "app/areaInfo/compareCheckReport";
	private static String AREA_COMPARE_PUNISH = "app/areaInfo/comparePunish";
	private static String CONFIG_GET_DICTLIST = "app/config/getDictList";
	
	private static String QUERY_FIRE_EVENT = "app/query/fireEvent";
	private static String QUERY_FIER_EVENT_DETAIL = "app/query/fireEventDetail";
	private static String QUERY_CHECK_REPORT = "app/query/checkReport";
	private static String QUERY_CHECK_REPORT_DETAIL = "app/query/reportDetail";
	private static String QUERY_PUNISH_EVENT = "app/query/punishEvent";
	
	
	
	@Autowired 
	AreaInfoController areaInfoController;
	
	@Autowired
	ConfigInfoController configController;
	
	@Autowired
	QueryInfoController queryController;
	
	@RequestMapping(value="/batchInvoke", method=RequestMethod.GET)
	@ResponseBody
	public Map<String,CommonResult> batchInvoke(@RequestParam(value = "batchRequest") List<RequestVo> batchRequest){
		
		
		Map<String,CommonResult> responseMap = new HashMap<String,CommonResult>();
		
		if( CollectionUtils.isEmpty(batchRequest)){
			return null;
		}
		
		for(RequestVo request:batchRequest){
			CommonResult result = invokeRequest(request);
			responseMap.put(request.getApiKey(), result);
		}
		
		
		return responseMap;
		
	}
	
	private CommonResult invokeRequest(RequestVo request){
		if( request == null ){
			return CommonResult.fail("请求为空");
		}
		if( request.getApiKey() == null){
			return CommonResult.fail("请求API为空");
		}
		if( request.getParams()== null){
			return CommonResult.fail("请求参数为空");
		}
		
		if( request.getApiKey().equalsIgnoreCase(AREA_QUERY_CONCERN_AREA)){
			return areaInfoController.queryConcernArea(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if( request.getApiKey().equalsIgnoreCase(AREA_QUERY_DISTRICT)){
			return areaInfoController.queryDistrict(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_QUERY_STREET)){
			return areaInfoController.queryStreet(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_QUERY_AREA)){
			return areaInfoController.queryArea(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_QUERY_TREND_REPORT)){
			//return areaInfoController.trendReport(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_COMPARE_FIRE)){
			return areaInfoController.compareFire(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_COMPARE_CHECK_REPORT)){
			return areaInfoController.compareCheckReport(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(AREA_COMPARE_PUNISH)){
			return areaInfoController.comparePunish(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(CONFIG_GET_DICTLIST)){
			return configController.getDictList(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(QUERY_FIRE_EVENT)){
			return queryController.queryFireEvent(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(QUERY_FIER_EVENT_DETAIL)){
			return queryController.getFireEventDetail(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(QUERY_CHECK_REPORT)){
			return queryController.queryCheckReport(request.getParams(),request.getPageNum(),request.getPaseSize());
		}else if(request.getApiKey().equalsIgnoreCase(QUERY_CHECK_REPORT_DETAIL)){
			return queryController.reportDetail(request.getParams());
		}else if(request.getApiKey().equalsIgnoreCase(QUERY_PUNISH_EVENT)){
			return queryController.queryPunishEvent(request.getParams(),request.getPageNum(),request.getPaseSize());
		}
		
		return null;
	}

}
