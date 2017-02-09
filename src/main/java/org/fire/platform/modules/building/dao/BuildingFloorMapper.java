package org.fire.platform.modules.building.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.bean.BuildingFloorBean;
import org.fire.platform.modules.building.domain.BuildingFloor;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

public interface BuildingFloorMapper extends Mapper<BuildingFloor> {

	
	List< BuildingFloorBean> selectBeanByMap(Map<String, Object> map);
}

