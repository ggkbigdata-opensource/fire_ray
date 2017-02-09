package org.fire.platform.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.sys.bean.MenuBean;
import org.fire.platform.modules.sys.domain.Menu;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

public interface MenuMapper extends Mapper<Menu> {

	List<MenuBean> selectBeanByMap(Map<String, Object> map);

	List<MenuBean> selectBeanByCurrentUser(Map<String, Object> map);

	MenuBean selectBeanByPrimaryKey(Long id);
}

