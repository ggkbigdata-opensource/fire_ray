package org.fire.platform.modules.area.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.StreetBean;
import org.fire.platform.modules.area.domain.Street;

public interface IStreetService extends IService<Street>{

	
	PageInfo<StreetBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
		
	Long queryBeanByName(String name);
}
