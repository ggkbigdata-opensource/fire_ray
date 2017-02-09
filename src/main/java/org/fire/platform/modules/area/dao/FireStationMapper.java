package org.fire.platform.modules.area.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.bean.FireStationBean;
import org.fire.platform.modules.area.domain.FireStation;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

public interface FireStationMapper extends Mapper<FireStation> {

	List<FireStationBean>  selectBeanByMap(Map<String, Object> map);
	
	Long selectByName(String name);
}

