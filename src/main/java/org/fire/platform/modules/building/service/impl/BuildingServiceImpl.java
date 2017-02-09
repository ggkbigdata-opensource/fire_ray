package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingBean;
import org.fire.platform.modules.building.dao.BuildingMapper;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
	private BuildingMapper mapper;
 
	public int insert(Building bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Building bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Building get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Building> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Building> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Building> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Building> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<Building> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Building> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<BuildingBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}


	@Override
	public Long queryBeanByBaseCode(String baseCode) {
		// TODO Auto-generated method stub
		return mapper.selectByBaseCode(baseCode);
	}
	
	
}
