package org.fire.platform.modules.report.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.CheckReportInfoBean;
import org.fire.platform.modules.report.bean.ReportInfoBean;
import org.fire.platform.modules.report.domain.CheckReportInfo;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface CheckReportInfoMapper extends Mapper<CheckReportInfo> {

    List<CheckReportInfoBean> selectBeanByMap(Map<String,Object> params);

    ReportInfoBean selectReportBeanById(Long reportId);

    CheckReportInfoBean getBeanById(Long reportId);
}

