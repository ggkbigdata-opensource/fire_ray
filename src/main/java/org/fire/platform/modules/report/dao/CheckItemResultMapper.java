package org.fire.platform.modules.report.dao;


import org.apache.ibatis.annotations.Param;
import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.domain.CheckItemResult;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface CheckItemResultMapper extends Mapper<CheckItemResult> {

    int batchInsertResult(Map<String,Object> params);

    int insertResult(Long reportId , Long itemId, Integer checkNum, Integer unNum);

    List<CheckItemResultBean> selectResultBeanByReportId(@Param("reportId") Long reportId);
}

