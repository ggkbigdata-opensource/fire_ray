package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.dao.FireSystemMapper;
import org.fire.platform.modules.building.domain.FireSystem;
import org.fire.platform.modules.building.service.IFireSystemService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Service
public class FireSystemServiceImpl implements IFireSystemService {

    @Autowired
	private FireSystemMapper mapper;
 
	public int insert(FireSystem bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(FireSystem bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public FireSystem get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<FireSystem> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<FireSystem> queryPageByMap(int page,int size,Map param){
	   PageHelper.startPage(page,size);
	   List<FireSystem> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<FireSystem> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<FireSystem> queryPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<FireSystem> queryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByMap(map);
	}


	@Override
	public PageInfo<FireSystem> queryPageByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<FireSystem> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	public int batchInsert(List<FireSystem> fireSystems){

		return mapper.batchInsert(fireSystems);
	}
	
}
