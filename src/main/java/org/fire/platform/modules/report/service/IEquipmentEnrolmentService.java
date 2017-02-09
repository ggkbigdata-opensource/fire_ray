package org.fire.platform.modules.report.service;


import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseIterators;
import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.EquipmentEnrolmentBean;
import org.fire.platform.modules.report.bean.EquipmentTypeBean;
import  org.fire.platform.modules.report.domain.EquipmentEnrolment;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface IEquipmentEnrolmentService extends IService<EquipmentEnrolment> {

    int batchInsert(List<EquipmentEnrolment> enrolmentList);

    PageInfo<EquipmentEnrolmentBean> getBeanList(Integer page , Integer pageSize , Long reportId);

    int deleteByReportId(Long reportId);

    List<EquipmentTypeBean> getBeanList2(Long reportId);
}
