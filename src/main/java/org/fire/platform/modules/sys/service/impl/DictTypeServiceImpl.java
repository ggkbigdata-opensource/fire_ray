package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.DictTypeMapper;
import org.fire.platform.modules.sys.domain.DictType;
import org.fire.platform.modules.sys.service.IDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:47
 */

@Service
public class DictTypeServiceImpl implements IDictTypeService {

    @Autowired
	private DictTypeMapper mapper;
 
	public int insert(DictType bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(DictType bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public DictType get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<DictType> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<DictType> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<DictType> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<DictType> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<DictType> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<DictType> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
