package org.fire.platform.modules.check.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.dao.CheckItemMapper;
import org.fire.platform.modules.check.domain.CheckItem;
import org.fire.platform.modules.check.service.ICheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Service
public class CheckItemServiceImpl implements ICheckItemService {

    @Autowired
	private CheckItemMapper mapper;
 
	public int insert(CheckItem bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItem bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItem get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItem> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItem> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItem> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<CheckItem> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<CheckItem> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItem> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
