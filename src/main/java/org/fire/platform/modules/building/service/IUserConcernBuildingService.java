package org.fire.platform.modules.building.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.building.domain.UserConcernBuilding;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */
 
public interface IUserConcernBuildingService extends IService<UserConcernBuilding> {
	
	public int deleteByParam(Map<String,Object> param);

}
