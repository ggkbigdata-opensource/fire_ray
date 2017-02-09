package org.fire.platform.modules.check.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.dao.CheckClassMapper;
import org.fire.platform.modules.check.domain.CheckClass;
import org.fire.platform.modules.check.service.ICheckClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Service
public class CheckClassServiceImpl implements ICheckClassService {

    @Autowired
	private CheckClassMapper mapper;
 
	public int insert(CheckClass bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckClass bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckClass get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckClass> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckClass> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckClass> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<CheckClass> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<CheckClass> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<CheckClass> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
