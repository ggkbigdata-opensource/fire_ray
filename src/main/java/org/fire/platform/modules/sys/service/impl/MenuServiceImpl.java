package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.bean.MenuBean;
import org.fire.platform.modules.sys.dao.MenuMapper;
import org.fire.platform.modules.sys.domain.Menu;
import org.fire.platform.modules.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
	private MenuMapper mapper;
 
	public int insert(Menu bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Menu bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Menu get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Menu> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Menu> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Menu> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Menu> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public List<MenuBean> queryBeanByMap(Map<String, Object> map) {
		return mapper.selectBeanByMap(map);
	}

	public List<MenuBean> queryBeanByCurrentUser(Map<String, Object> map) {
		return mapper.selectBeanByCurrentUser(map);
	}
	
	public PageInfo<Menu> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Menu> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	public MenuBean getBean(Long id){
		return mapper.selectBeanByPrimaryKey(id);
	}



}
