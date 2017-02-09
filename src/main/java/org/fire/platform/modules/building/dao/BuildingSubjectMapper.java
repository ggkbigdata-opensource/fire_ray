package org.fire.platform.modules.building.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.bean.BuildingBean;
import org.fire.platform.modules.building.bean.BuildingSubjectBean;
import org.fire.platform.modules.building.domain.BuildingSubject;

import java.util.List;
import java.util.Map;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 11:20:25
 */

public interface BuildingSubjectMapper extends Mapper<BuildingSubject> {

    List<BuildingSubjectBean> selectBeanByMap(Map<String, Object> map);

    Long selectByName(String name);
}

