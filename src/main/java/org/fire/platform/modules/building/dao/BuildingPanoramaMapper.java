package org.fire.platform.modules.building.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.bean.BuildingPanoramaBean;
import org.fire.platform.modules.building.domain.BuildingPanorama;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

public interface BuildingPanoramaMapper extends Mapper<BuildingPanorama> {

	
	List< BuildingPanoramaBean> selectBeanByMap(Map<String, Object> map);
}

