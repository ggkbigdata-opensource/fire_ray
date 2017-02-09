package org.fire.platform.modules.building.service;

import org.fire.platform.common.base.IService;

import org.fire.platform.modules.building.bean.ManagementBean;
import  org.fire.platform.modules.building.domain.Management;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */
 
public interface IManagementService extends IService<Management> {

    ManagementBean getByBuildingId(Long buildingId);
}
