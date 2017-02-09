package org.fire.platform.modules.front.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.base.CountValueData;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.domain.UserConcernBuilding;
import org.fire.platform.modules.building.service.IBuildingInfoService;
import org.fire.platform.modules.building.service.IUserConcernBuildingService;
import org.fire.platform.modules.building.vo.BuildingDetailVo;
import org.fire.platform.modules.building.vo.BuildingRelatVo;
import org.fire.platform.modules.building.vo.BuildingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/buildingInfo")
public class BuildingInfoController {
	
	@Autowired 
	private IBuildingInfoService buildingInfoService;
	
	@Autowired
	private IUserConcernBuildingService userConcernBuildingService;
	

	
	/**
	 * 获取建筑信息
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/queryConcernBuilding", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryConcernBuilding(
			@RequestParam(value = "userId",required=true) Long userId,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		
		//pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
		PageHelper.startPage(pageNum, pageSize);
		
		List<BuildingVo> list = buildingInfoService.queryConcernList(userId);
		PageInfo<BuildingVo> page = PageHelper.getPageInfo(list);
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	/**
	 * 获取相关信息
	 * @param buildingId
	 * @return
	 */
	@RequestMapping(value="/getRelateInfo", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getBaseInfo(
			@RequestParam(value = "buildingId",required=true) Long buildingId){
		BuildingRelatVo vo =  buildingInfoService.getRelateInfo(buildingId);
		return CommonResult.success(vo);
	}
	
	
	@RequestMapping(value="/getDetail", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult getDetail(
			@RequestParam(value = "buildingId",required=true) Long buildingId,
			@RequestParam(value = "userId",required=true) Long userId){
		BuildingDetailVo vo = buildingInfoService.getDetailInfo(buildingId,userId);
		return CommonResult.success(vo);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult search(
			@RequestParam(required=false)  String name,
			@RequestParam(value = "pageNum",defaultValue="1") Integer pageNum, 
			@RequestParam(value = "pageSize",defaultValue="10") Integer pageSize){
		//TODO 全文检索
		//先按照名称来查询
		PageHelper.startPage(pageNum, pageSize);
		
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("nameLike", name);
		List<BuildingVo> list = buildingInfoService.queryBuildingBaseInfo(param);
		PageInfo<BuildingVo> page = PageHelper.getPageInfo(list);
		
		return CommonResult.success(page.getList(),page.getTotal());
	}
	
	@RequestMapping(value="/searchLocal", method=RequestMethod.GET)
	@ResponseBody	
	public CommonResult searchLocal(
			@RequestParam(value = "longitude") float longitude,
			@RequestParam(value = "latitude") float latitude){
		
		//TODO
		
		return null;
	}
	
	@RequestMapping(value="/addConcern", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult addConcern(
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "buildingId") Long buildingId){
		
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("buildingId", buildingId);
		List<UserConcernBuilding> list =  userConcernBuildingService.queryByMap(param);
		if( CollectionUtils.isNotEmpty(list)){
			return CommonResult.fail("您已经关注该建筑了");
		}
		
		UserConcernBuilding bean = new UserConcernBuilding();
		bean.setUserId(userId);
		bean.setBuildingId(buildingId);
		bean.setCreateDate(new Date());
		int ret = userConcernBuildingService.insert(bean);
		if( ret <=0 ){
			return CommonResult.fail("关注建筑失败");
		}
		
		return CommonResult.success();
	}
	
	@RequestMapping(value="/cancelConcern", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult cancelConcern(
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "buildingId") Long buildingId){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("userId", userId);
		param.put("buildingId", buildingId);
		int ret = userConcernBuildingService.deleteByParam(param);
		if( ret <=0 ){
			return CommonResult.fail("取消关注建筑失败");
		}
		return CommonResult.success();
	}
	
	@RequestMapping(value="/queryBuildingTypeCount", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult queryBuildingTypeCount(
			@RequestParam(value = "areaId") Long areaId,
			@RequestParam(value = "areaType") Integer areaType){
		
		Map<String,Object> param = new HashMap<String,Object>();
		if( areaType == 0 ){
			param.put("districtId", areaId);
		}else if( areaType == 1 ){
			param.put("streetId", areaId);
		}else if( areaType == 2 ){
			param.put("blockId", areaId);
		}
		
		List<CountValueData> list = buildingInfoService.queryBuildingTypeCount(param);
		
		return CommonResult.success(list);
	}
	
	
	
	@RequestMapping(value="/topConcern", method=RequestMethod.GET)
	@ResponseBody
	public CommonResult topConcern(
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "buildingId") Long buildingId){
		
		//TODO
		return null;
	}
	
	
	

}
