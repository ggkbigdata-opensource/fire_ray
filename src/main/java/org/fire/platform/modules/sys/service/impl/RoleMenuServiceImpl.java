package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.RoleMenuMapper;
import org.fire.platform.modules.sys.domain.RoleMenu;
import org.fire.platform.modules.sys.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

    @Autowired
	private RoleMenuMapper mapper;
 
	public int insert(RoleMenu bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(RoleMenu bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int deleteByRoleId(Long roleId) {
		return mapper.deleteByRoleId(roleId);
	}
	
	public RoleMenu get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<RoleMenu> queryAll(){
	   return mapper.selectAll();
	}

	public List<RoleMenu> queryByRoleMenu(Long roleId, Long menuId){
		return mapper.selectByRoleMenu(roleId,menuId);
	}
	
	public PageInfo<RoleMenu> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<RoleMenu> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<RoleMenu> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<RoleMenu> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<RoleMenu> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	public int batchInsert(List<RoleMenu> roleMenus){
		return mapper.batchInsert(roleMenus);
	}
}
