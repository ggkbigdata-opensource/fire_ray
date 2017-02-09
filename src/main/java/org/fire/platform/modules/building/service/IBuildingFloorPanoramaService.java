package org.fire.platform.modules.building.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingFloorPanoramaBean;
import org.fire.platform.modules.building.domain.BuildingFloorPanorama;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */
 
public interface IBuildingFloorPanoramaService extends IService<BuildingFloorPanorama> {

	
	PageInfo<BuildingFloorPanoramaBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
}
