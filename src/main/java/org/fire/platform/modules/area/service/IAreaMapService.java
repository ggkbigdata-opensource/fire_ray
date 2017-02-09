package org.fire.platform.modules.area.service;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.area.domain.AreaMap;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-30 14:55:37
 */
 
public interface IAreaMapService extends IService<AreaMap> {
	
	public String generateMap();
	
	public String generateWebMap();

}
