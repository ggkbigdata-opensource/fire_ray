package org.fire.platform.modules.area.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.bean.StreetBean;
import org.fire.platform.modules.area.domain.Street;

public interface StreetMapper extends Mapper<Street>{

	
	List<StreetBean> selectBeanByMap(Map<String, Object> map);
	
	Long selectByName(String name);
}
