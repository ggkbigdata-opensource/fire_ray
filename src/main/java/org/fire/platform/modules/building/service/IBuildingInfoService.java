package org.fire.platform.modules.building.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.CountValueData;
import org.fire.platform.modules.building.vo.BuildingDetailVo;
import org.fire.platform.modules.building.vo.BuildingRelatVo;
import org.fire.platform.modules.building.vo.BuildingVo;

public interface IBuildingInfoService {
	/**
	 * 查询关注的建筑信息
	 * @param userId
	 * @return
	 */
	public List<BuildingVo> queryConcernList(Long userId);
	
	/**
	 * 获取建筑相关信息，用于在地图上显示
	 * @param buildingId
	 * @return
	 */
	public BuildingRelatVo getRelateInfo(Long buildingId);
	
	/**
	 * 
	 * @param buildingId
	 * @return
	 */
	public BuildingDetailVo getDetailInfo(Long buildingId,Long userId);
	
	/**
	 * 根据名称查询建筑
	 * @param name
	 * @return
	 */
	public List<BuildingVo> queryBuildingBaseInfo(Map<String,Object> param);
	
	/**
	 * 查询建筑分类数量
	 * @param param
	 * @return
	 */
	public List<CountValueData> queryBuildingTypeCount(Map<String,Object> param);
	
	
	
	
	

}
