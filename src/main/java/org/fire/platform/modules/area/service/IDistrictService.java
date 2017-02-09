package org.fire.platform.modules.area.service;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.area.domain.District;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 18:52:34
 */
 
public interface IDistrictService extends IService<District> {

	
	Long queryBeanByName(String name);

	int batchDelete(List<Long> ids);
}
