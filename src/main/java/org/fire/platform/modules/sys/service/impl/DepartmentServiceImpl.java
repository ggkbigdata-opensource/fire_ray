package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.DepartmentMapper;
import org.fire.platform.modules.sys.domain.Department;
import org.fire.platform.modules.sys.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
	private DepartmentMapper mapper;
 
	public int insert(Department bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Department bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Department get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Department> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Department> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Department> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Department> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<Department> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Department> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	
	
}
