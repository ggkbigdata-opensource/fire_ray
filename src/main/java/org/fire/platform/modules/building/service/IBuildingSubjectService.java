package org.fire.platform.modules.building.service;

import org.fire.platform.common.base.IService;

import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingSubjectBean;
import  org.fire.platform.modules.building.domain.BuildingSubject;

import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 11:20:25
 */
 
public interface IBuildingSubjectService extends IService<BuildingSubject> {

    PageInfo<BuildingSubjectBean> queryPageBeanByMap(Map<String, Object> map,
                                                     int pageNo, int pageSize);

    Long queryBeanByName(String name);
}
