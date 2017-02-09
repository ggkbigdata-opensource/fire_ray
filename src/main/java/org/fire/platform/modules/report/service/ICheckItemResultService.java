package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import  org.fire.platform.modules.report.domain.CheckItemResult;
import org.omg.IOP.CodecPackage.InvalidTypeForEncoding;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface ICheckItemResultService extends IService<CheckItemResult> {

    /**
     * 批量插入Result
     * @param reportId
     * @param resultList
     * @return
     */
    int batchInsert(Long reportId, List<CheckItemResult> resultList);

    PageInfo<CheckItemResultBean> queryResultBeanByReportId(Integer page, Integer pageSize , Long reportId);


    int deleteByReportId(Long reportId);
}
