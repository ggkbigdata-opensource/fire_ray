package org.fire.platform.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.sys.bean.UserBean;
import org.fire.platform.modules.sys.domain.User;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

public interface UserMapper extends Mapper<User> {

	
	List<UserBean> selectBeanByMap(Map<String, Object> map);
}

