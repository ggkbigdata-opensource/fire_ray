package org.fire.platform.modules.building.dao;

import org.apache.ibatis.annotations.Param;
import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.building.domain.FireSystem;
import sun.rmi.log.LogInputStream;

import java.util.Collection;
import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

public interface FireSystemMapper extends Mapper<FireSystem> {

    int batchInsert(List<FireSystem> fireSystems);
}

