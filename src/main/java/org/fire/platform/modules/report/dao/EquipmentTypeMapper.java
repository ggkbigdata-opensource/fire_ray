package org.fire.platform.modules.report.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.domain.EquipmentType;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-12-2 14:28:58
 */

public interface EquipmentTypeMapper extends Mapper<EquipmentType> {

    int batchInsert(List<EquipmentType> typeList);

}

