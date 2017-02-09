package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemUnqualified;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface ICheckItemUnqualifiedService extends IService<CheckItemUnqualified> {


    int batchInsert(List<CheckItemUnqualified> unqualifiedList);

    PageInfo<CheckItemUnqualifiedBean> getUnqualifiedBeanByReportId(Integer page, Integer pageSize, Long reportId);

    int deleteByReportId(Long reportId);
}
