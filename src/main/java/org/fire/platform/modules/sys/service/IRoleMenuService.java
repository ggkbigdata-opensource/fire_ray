package org.fire.platform.modules.sys.service;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.sys.domain.RoleMenu;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */
 
public interface IRoleMenuService extends IService<RoleMenu> {

    int deleteByRoleId(Long roleId);

    List<RoleMenu> queryByRoleMenu(Long roleId, Long menuId);

    int batchInsert(List<RoleMenu> roleMenus);
}
