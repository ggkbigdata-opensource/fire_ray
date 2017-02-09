package org.fire.platform.modules.area.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.domain.District;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 18:52:34
 */

public interface DistrictMapper extends Mapper<District> {

	
	Long selectByName(String name);

	int batchDeleteDistrict(List<Long> ids);
}

