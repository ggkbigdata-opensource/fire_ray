package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.dao.KeypartMapper;
import org.fire.platform.modules.building.domain.Keypart;
import org.fire.platform.modules.building.service.IKeypartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Service
public class KeypartServiceImpl implements IKeypartService {

    @Autowired
	private KeypartMapper mapper;
 
	public int insert(Keypart bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Keypart bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Keypart get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Keypart> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<Keypart> queryPageByMap(int page,int size,Map param){
	   PageHelper.startPage(page,size);
	   List<Keypart> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}


	@Override
	public List<Keypart> queryAll() {
		// TODO Auto-generated method stub
		return mapper.selectAll();
	}


	@Override
	public PageInfo<Keypart> queryPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Keypart> queryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByMap(map);
	}


	@Override
	public PageInfo<Keypart> queryPageByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<Keypart> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
