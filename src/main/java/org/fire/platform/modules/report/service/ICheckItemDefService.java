package org.fire.platform.modules.report.service;


import org.fire.platform.common.base.IService;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemDef;
import org.fire.platform.modules.report.domain.CheckItemResult;
import org.fire.platform.modules.report.domain.CheckItemResultStatis;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */
 
public interface ICheckItemDefService extends IService<CheckItemDef> {

    /**
     * 获取检测项。不包含大分类
     * @return 所有小项的检测项
     */
    List<CheckItemDef> getCheckItem();

    /**
     * 获取检测统计项
     * 用于匹配PDF导入数据
     * @return 检测统计定义
     */
    List<CheckItemResultStatis> getStatisItem();

    /**
     * 获取检测项
     * 用于匹配PDF导入数据
     * @return 检测项定义
     */
    List<CheckItemResultBean> getItemResult();

}
