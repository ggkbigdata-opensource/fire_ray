package org.fire.platform.modules.building.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.fire.platform.common.base.CountValueData;
import org.fire.platform.modules.area.dao.FireStationMapper;
import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.building.dao.BuildingMapper;
import org.fire.platform.modules.building.dao.BuildingPanoramaMapper;
import org.fire.platform.modules.building.dao.UserConcernBuildingMapper;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.domain.BuildingPanorama;
import org.fire.platform.modules.building.domain.UserConcernBuilding;
import org.fire.platform.modules.building.service.IBuildingInfoService;
import org.fire.platform.modules.building.vo.BuildingDetailVo;
import org.fire.platform.modules.building.vo.BuildingRelatVo;
import org.fire.platform.modules.building.vo.BuildingVo;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.check.vo.CheckReportVo;
import org.fire.platform.modules.event.dao.FireEventMapper;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.event.vo.FireEventVo;
import org.fire.platform.modules.event.vo.PunishEventVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class BuildingInfoServiceImpl implements IBuildingInfoService{
	
	@Autowired
	BuildingMapper buildingMapper;
	
	@Autowired
	FireStationMapper fireStationMapper;
	
	@Autowired
	FireEventMapper fireEventMapper;
	
	@Autowired
	IPunishEventService pulishEventService;
	
	@Autowired
	ICheckReportService checkReportService;
	
	@Autowired
	BuildingPanoramaMapper buildingPanoramaMapper;
	
	@Autowired
	UserConcernBuildingMapper userConcernBuildingMapper;

	@Override
	public List<BuildingVo> queryConcernList(Long userId) {
		// TODO Auto-generated method stub
		return buildingMapper.selectConcernBuildings(userId);
	}

	@Override
	public BuildingRelatVo getRelateInfo(Long buildingId) {
		// TODO Auto-generated method stub
		//TODO 查周边消防站,根据建筑的经纬度来查询
		BuildingRelatVo relatVo = new BuildingRelatVo();
		
		Building building = buildingMapper.selectByPrimaryKey(buildingId);
		if( building == null ){
			return null;
		}
		String geoHashCode = building.getGeoHashCode();
		if( StringUtils.isBlank(geoHashCode)){
			return null;
		}
		
		String matchCode = StringUtils.substring(geoHashCode, 0, 5);
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("geoHashCodeLike", matchCode);
		List<FireStation> fireStationlist = fireStationMapper.selectByMap(param);
		
		relatVo.setLocalfireStationList(fireStationlist);
		
		//TODO 查相关火情事件
		param.clear();
		param.put("buildingId", buildingId);
		List<FireEventVo> fireEventList = fireEventMapper.selectFireEvent(param);
		relatVo.setFireEventList(fireEventList);
		
		//TODO 查相关执法事件
		param.clear();
		param.put("buildingId", buildingId);
		List<PunishEventVo> punishEventList = pulishEventService.queryPunishEvent(param);
		relatVo.setPunishEventList(punishEventList);
		return relatVo;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "hourCache", key = "'getDetailInfo_' + #buildingId + '_' + #userId")
	})
	public BuildingDetailVo getDetailInfo(Long buildingId,Long userId) {
		// TODO Auto-generated method stub
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("buildingId", buildingId);
		
		List<BuildingVo> buildings = buildingMapper.selectBuildingBaseInfo(param);
		if(CollectionUtils.isEmpty(buildings)){
			return null;
		}
		
		BuildingVo building = buildings.get(0);

		List<BuildingPanorama> buildingPanoramaList = buildingPanoramaMapper.selectByMap(param);
		
		//TODO获取楼层信息
		
		BuildingDetailVo detail = new BuildingDetailVo();
		detail.setBuildingInfo(building);
		detail.setBuildingPanoramaList(buildingPanoramaList);
		
		//TODO 获取检测报告历史
		List<CheckReportVo> reportList =  checkReportService.queryCheckReport(param);
		detail.setReportList(reportList);
		
		//TODO 获取执法信息
		//param.put("buildingId", buildingId);
		List<PunishEventVo> punishEventList = pulishEventService.queryPunishEvent(param);
		detail.setPunishList(punishEventList);
		
		//TODO 获取火情信息
		List<FireEventVo> fireEventList = fireEventMapper.selectFireEvent(param);
		detail.setFireList(fireEventList);
		
		//TODO 获取建筑关注信息
		param.put("userId", userId);
		List<UserConcernBuilding> concernLs = userConcernBuildingMapper.selectByMap(param);
		if( CollectionUtils.isNotEmpty(concernLs)){
			detail.setConcernFlag(1); //关注
		}
		
		return detail;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryBuildingBaseInfo_' + #param")
	})
	public List<BuildingVo> queryBuildingBaseInfo(Map<String,Object> param) {
		// TODO Auto-generated method stub
		return buildingMapper.selectBuildingBaseInfo(param);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'queryBuildingTypeCount_' + #param ")
	})
	public List<CountValueData> queryBuildingTypeCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return buildingMapper.selectBuildingTypeCount(param);
	}

}
