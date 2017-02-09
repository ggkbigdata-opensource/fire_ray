package org.fire.platform.modules.front.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.constants.AreaConstants;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.domain.UserConcernArea;
import org.fire.platform.modules.area.service.IAreaService;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.area.service.IUserConcernAreaService;
import org.fire.platform.modules.area.vo.*;
import org.fire.platform.modules.building.service.IBuildingInfoService;
import org.fire.platform.modules.front.vo.AreaDetailResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/areaInfo")
public class AreaInfoController {
	
	@Autowired
	IAreaService areaService;
	
	@Autowired
	IDistrictService districtService;
	
	@Autowired 
	IStreetService streetService;
	
	@Autowired 
	IBlockService blockService;
	
	@Autowired
	IUserConcernAreaService concernAreaService;
	
	@Autowired 
	private IBuildingInfoService buildingInfoService;

	private static Logger log = LoggerFactory.getLogger(AreaInfoController.class);
	
	/**
	 * 获取关注社区列表,需要分页
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/queryConcernArea", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryConcernArea(
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		PageInfo<AreaVo> page =  areaService.queryConcernArea(userId,pageNum,pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	public CommonResult queryConcernArea(
			@RequestParam(value="params") Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		Long userId = (Long)params.get("userId");
		
		return queryConcernArea(userId,pageNum,pageSize);
	}
	
	
	@RequestMapping(value="/queryDistrict", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryDistrict(
			@RequestParam(required = false) String name,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		PageInfo<SimpleAreaVo> page =  areaService.queryDistrict(name, pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	public CommonResult queryDistrict(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		String name = (String)params.get("name");
		PageInfo<SimpleAreaVo> page =  areaService.queryDistrict(name, pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	@RequestMapping(value="/queryStreet", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryStreet(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long districtId,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		PageInfo<SimpleAreaVo> page =  areaService.queryStreet(districtId, name, pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	public CommonResult queryStreet(
			@RequestParam(required = false) Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		
		Long districtId = (Long)params.get("districtId");
		String name = (String)params.get("name");
		PageInfo<SimpleAreaVo> page =  areaService.queryStreet(districtId, name, pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}

	/**
	 * 查找社区
	 * @param name
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/queryArea", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryArea(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long districtId,
			@RequestParam(required = false) Long streetId,
			@RequestParam(required = false) String blockType,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		PageInfo<AreaVo> page =  areaService.queryArea(name, districtId,streetId,blockType,pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	public CommonResult queryArea(
			@RequestParam(value="params") Map<String,Object> params,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize
			){
		Long districtId = (Long)params.get("districtId");
		Long streetId = (Long)params.get("streetId");
		String name = (String)params.get("name");
		String blockType = (String)params.get("blockType");
		PageInfo<AreaVo> page =  areaService.queryArea(name, districtId,streetId,blockType,pageNum, pageSize);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	/**
	 * 社区报告
	 * @param areaId 社区ID
	 * @param type 社区类型
	 * @return
	 */
	@RequestMapping(value="/trendReport", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult trendReport(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		
		AreaTrendVo trendVo =  areaService.queryTrendReport(areaId, areaType);
		return CommonResult.success(trendVo);
	}
	
	
	@RequestMapping(value="/queryTrendReport", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryTrendReport(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType,
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd
			){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("monthBegin", monthBegin);
		param.put("monthEnd", monthEnd);
		if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
			param.put("districtId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
			param.put("streetId", areaId);
		}
		
		if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
			param.put("blockId", areaId);
		}
		
		AreaTrendVo trendVo =  areaService.queryTrendReport(param);
		return CommonResult.success(trendVo);
	}
	

	@RequestMapping(value="/compareFire", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult compareFire(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<AreaTypeDataVo> ls = areaService.queryFireCompareData(areaId, areaType);
		retMap.put("title", "火情数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	public CommonResult compareFire(
			@RequestParam(value="params") Map<String,Object> params){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Long areaId = (Long)params.get("areaId");
		Integer areaType = (Integer)params.get("areaType");
		List<AreaTypeDataVo> ls = areaService.queryFireCompareData(areaId, areaType);
		retMap.put("title", "火情数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	@RequestMapping(value="/compareCheckReport", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult compareCheckReport(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<AreaTypeDataVo> ls = areaService.queryCheckReportCompareData(areaId, areaType);
		retMap.put("title", "报告数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	public CommonResult compareCheckReport(
			@RequestParam(value="params") Map<String,Object> params){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Long areaId = (Long)params.get("areaId");
		Integer areaType = (Integer)params.get("areaType");
		List<AreaTypeDataVo> ls = areaService.queryCheckReportCompareData(areaId, areaType);
		retMap.put("title", "报告数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	@RequestMapping(value="/comparePunish", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult comparePunish(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType
			){
		Map<String,Object> retMap = new HashMap<String,Object>();
		List<AreaTypeDataVo> ls = areaService.queryPunishCompareData(areaId, areaType);
		retMap.put("title", "执法数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	public CommonResult comparePunish(
			@RequestParam(value="params") Map<String,Object> params){
		Map<String,Object> retMap = new HashMap<String,Object>();
		Long areaId = (Long)params.get("areaId");
		Integer areaType = (Integer)params.get("areaType");
		List<AreaTypeDataVo> ls = areaService.queryPunishCompareData(areaId, areaType);
		retMap.put("title", "执法数据对比");
		retMap.put("result", ls);
		return CommonResult.success(retMap);
	}
	
	
	/**
	 * 获取街道详情
	 * @param areaId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/getDistrictDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getDistrictDetail(
			@RequestParam(value = "streetId") Long districtId){
		
		
		District district =  districtService.get(districtId);
		if( district == null ){
			return CommonResult.fail("没有获取到街道信息");
		}
		return CommonResult.success(district);
	}
	
	/**
	 * 获取街道详情
	 * @param areaId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/getStreetDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getStreetDetail(
			@RequestParam(value = "streetId") Long streetId){
		
		
		Street street =  streetService.get(streetId);
		if( street == null ){
			return CommonResult.fail("没有获取到街道信息");
		}
		return CommonResult.success(street);
	}
	
	/**
	 * 获取街道详情
	 * @param areaId
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/getBlockDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getBlockDetail(
			@RequestParam(value = "blockId") Long blockId){
		Block block = blockService.get(blockId);
		if( block == null ){
			return CommonResult.fail("没有获取到街区信息");
		}
		return CommonResult.success(block);
	}
	
	/**
	 * 关注社区
	 * @param areaId
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/addConcern", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult addConcern(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType,
			@RequestParam(value = "userId") Long userId
			){
		//TODO 先检查是否已经关注
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("areaId", areaId);
		param.put("areaType", areaType);
		param.put("userId", userId);
		List<UserConcernArea> ls = concernAreaService.queryByMap(param);
		if( CollectionUtils.isNotEmpty(ls)){
			return CommonResult.fail("您已经关注过该社区了");
		}
		
		UserConcernArea bean = new UserConcernArea();
		bean.setAreaId(areaId);
		bean.setAreaType(areaType);
		bean.setUserId(userId);
		bean.setCreateDate(new Date());
		int ret = concernAreaService.insert(bean);
		if( ret > 0 ){
			return CommonResult.success();
		}
		return CommonResult.fail("关注失败");
	}
	
	/**
	 * 取消关注
	 * @param areaId
	 * @param type
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/cancelConcern", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult cancelConcern(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType,
			@RequestParam(value = "userId") Long userId
			){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("areaId", areaId);
		param.put("areaType", areaType);
		param.put("userId", userId);
		int ret = concernAreaService.deleteByParam(param);
		if( ret <=0 ){
			return CommonResult.fail("取消关注失败");
		}
		return CommonResult.success();
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryDetail(
			@RequestParam(required = false) Long areaId,
			@RequestParam(required = false) Integer areaType,
			@RequestParam(required = false) String areaCode,
			@RequestParam(required = false) Long userId){
		
		AreaDetailResult detail = areaService.queryAreaDetail(areaId, areaType, areaCode, userId);
		if(detail == null){
			return CommonResult.fail("数据详情未查到");
		}else{
			return CommonResult.success(detail);
		}
	}


	/**
	 * 获取火情对比数据，根据街道排名
	 * @param areaId
	 * @param areaType
	 * @param monthBegin
	 * @param monthEnd
     * @return
     */
	@RequestMapping(value="/detailFire", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryDetailFire(
			@RequestParam(required = false) Long areaId,
			@RequestParam(required = false) Integer areaType,
			@RequestParam(required = false) String monthBegin,
			@RequestParam(required = false) String monthEnd){

		log.info("AreaInfoController -> queryDetailFire params -> areaId = {}, areaType = {}, monthBegin = {}, monthEnd = {} " , areaId , areaType , monthBegin , monthEnd );
		List<AreaCompareStreetData> detail = areaService.queryFireDataGroup(areaId, areaType,monthBegin,monthEnd);
		if(detail == null){
			return CommonResult.fail("数据详情未查到");
		}else{
			return CommonResult.success(detail);
		}
	}

}
