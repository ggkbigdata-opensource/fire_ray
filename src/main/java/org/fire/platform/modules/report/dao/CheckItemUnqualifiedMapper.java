package org.fire.platform.modules.report.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemUnqualified;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface CheckItemUnqualifiedMapper extends Mapper<CheckItemUnqualified> {

    int batchInsert(List<CheckItemUnqualified> unqualifiedList);

    List<CheckItemUnqualifiedBean> selectBeanByMap(Map<String, Object> params);
}

