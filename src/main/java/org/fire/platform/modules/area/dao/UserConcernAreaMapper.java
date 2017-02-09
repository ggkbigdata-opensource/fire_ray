package org.fire.platform.modules.area.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.domain.UserConcernArea;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */

public interface UserConcernAreaMapper extends Mapper<UserConcernArea> {

    int selectByTypeAndAreaIdAndUserId(Integer areaType, Long areaId, Long userId);
}

