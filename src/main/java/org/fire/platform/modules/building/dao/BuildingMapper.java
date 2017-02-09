package org.fire.platform.modules.building.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.CountValueData;
import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.bean.BuildingBean;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.vo.BuildingEventVo;
import org.fire.platform.modules.building.vo.BuildingVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

public interface BuildingMapper extends Mapper<Building> {
	
	public List<BuildingVo> selectBuildingBaseInfo(Map<String,Object> param);
	
	public List<BuildingVo> selectConcernBuildings(Long userId);
	
	public List<BuildingEventVo> selectBuildingReport(Long buildingId);
	
	public List<BuildingEventVo> selectBuildingPunish(Long buildingId);
	
	
	public List<CountValueData> selectBuildingTypeCount(Map<String,Object> param);

	
	List<BuildingBean> selectBeanByMap(Map<String, Object> map);
	
	Long selectByName(String name);
	
	Long selectByBaseCode(String baseCode);
}

