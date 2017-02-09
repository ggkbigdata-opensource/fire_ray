package org.fire.platform.modules.building.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.bean.ManagementBean;
import org.fire.platform.modules.building.domain.Management;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

public interface ManagementMapper extends Mapper<Management> {

    public ManagementBean selectByBuildingId(Long buildingId);
}

