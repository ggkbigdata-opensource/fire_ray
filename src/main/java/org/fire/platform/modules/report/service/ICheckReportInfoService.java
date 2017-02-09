package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckReportInfoBean;
import org.fire.platform.modules.report.bean.ReportInfoBean;
import  org.fire.platform.modules.report.domain.CheckReportInfo;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface ICheckReportInfoService extends IService<CheckReportInfo> {


    List<CheckReportInfoBean> queryBeanByMap(Map<String, Object> map);

    PageInfo<CheckReportInfoBean> queryBeanPageByMap(Map<String, Object> map, int pageNo, int pageSize);

    ReportInfoBean getReportBean(Long reportId);

    CheckReportInfoBean getReportInfoBean(Long reportId);

    int deleteReport(Long id);
}
