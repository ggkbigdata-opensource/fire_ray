package org.fire.platform.modules.report.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.EquipmentEnrolmentBean;
import org.fire.platform.modules.report.bean.EquipmentTypeBean;
import org.fire.platform.modules.report.domain.EquipmentEnrolment;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface EquipmentEnrolmentMapper extends Mapper<EquipmentEnrolment> {

    int batchInsert(Map<String, Object> params);

    List<EquipmentEnrolmentBean> getBeanList(Long reportId);

    List<EquipmentTypeBean> getBeanList2(Long reportId);
}

