package org.fire.platform.modules.building.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingPanoramaBean;
import org.fire.platform.modules.building.domain.BuildingPanorama;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */
 
public interface IBuildingPanoramaService extends IService<BuildingPanorama> {

	
	
	PageInfo<BuildingPanoramaBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
}
