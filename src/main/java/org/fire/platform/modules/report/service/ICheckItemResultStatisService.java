package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultStatisBean;
import  org.fire.platform.modules.report.domain.CheckItemResultStatis;

import java.util.Collection;
import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface ICheckItemResultStatisService extends IService<CheckItemResultStatis> {

    /**
     * 根据检测报告ID计算统计数据
     * @param reportId
     */
    void sumStatisByReportId(Long reportId);

    PageInfo<CheckItemResultStatisBean> queryStatisBeanByReportId(Integer page, Integer pageSize, Long reportId);

    int batchInsert(List<CheckItemResultStatis> statisList);

    int deleteByReportId(Long reportId);
}
