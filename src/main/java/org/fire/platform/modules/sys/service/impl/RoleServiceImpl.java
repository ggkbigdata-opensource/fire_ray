package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.RoleMapper;
import org.fire.platform.modules.sys.domain.Role;
import org.fire.platform.modules.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
	private RoleMapper mapper;
 
	public int insert(Role bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Role bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Role get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Role> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Role> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Role> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Role> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<Role> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Role> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
