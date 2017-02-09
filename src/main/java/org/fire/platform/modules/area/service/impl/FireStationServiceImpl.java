package org.fire.platform.modules.area.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.BlockBean;
import org.fire.platform.modules.area.bean.FireStationBean;
import org.fire.platform.modules.area.dao.FireStationMapper;
import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.area.service.IFireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

@Service
public class FireStationServiceImpl implements IFireStationService {

    @Autowired
	private FireStationMapper mapper;
 
	public int insert(FireStation bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(FireStation bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public FireStation get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<FireStation> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<FireStation> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<FireStation> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<FireStation> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<FireStation> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<FireStation> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<FireStationBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<FireStationBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}
}
