package org.fire.platform.modules.sys.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.sys.domain.RoleMenu;

import java.util.List;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    int deleteByRoleId(Long roleId);

    List<RoleMenu> selectByRoleMenu(Long roleId,Long menuId);

    int batchInsert(List<RoleMenu> roleMenus);

}

