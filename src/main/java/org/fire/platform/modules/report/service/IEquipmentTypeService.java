package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import  org.fire.platform.modules.report.domain.EquipmentType;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-12-2 14:28:58
 */
 
public interface IEquipmentTypeService extends IService<EquipmentType> {

    int batchInsert(List<EquipmentType> typeList);
}
