package org.fire.platform.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.sys.bean.MenuBean;
import org.fire.platform.modules.sys.domain.Menu;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */
 
public interface IMenuService extends IService<Menu> {
	
	List<MenuBean> queryBeanByMap(Map<String, Object> params);

	List<MenuBean> queryBeanByCurrentUser(Map<String, Object> params);

	MenuBean getBean(Long id);
}
