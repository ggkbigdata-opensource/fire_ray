package org.fire.platform.modules.report.dao;


import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.CheckItemResultStatisBean;
import org.fire.platform.modules.report.domain.CheckItemResultStatis;

import java.util.Collection;
import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface CheckItemResultStatisMapper extends Mapper<CheckItemResultStatis> {

    void sumStatisByReportId(Long reportId);

    List<CheckItemResultStatisBean> queryStatisBeanByReportId(Long reportId);

    int batchInsert(Collection<CheckItemResultStatis> statisList);
}

