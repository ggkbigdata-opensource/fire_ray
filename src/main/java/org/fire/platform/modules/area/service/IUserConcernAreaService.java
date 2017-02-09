package org.fire.platform.modules.area.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.area.domain.UserConcernArea;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */
 
public interface IUserConcernAreaService extends IService<UserConcernArea> {
	
	int deleteByParam(Map<String,Object> map);

    int isConcerned(Integer areaType, Long areaId, Long userId);
}
