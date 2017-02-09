package org.fire.platform.modules.report.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemDef;
import org.fire.platform.modules.report.domain.CheckItemResultStatis;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

public interface CheckItemDefMapper extends Mapper<CheckItemDef> {

    List<CheckItemDef> getCheckItem();

    List<CheckItemResultStatis> getStatisItem();

    List<CheckItemResultBean> getItemResult();
}

