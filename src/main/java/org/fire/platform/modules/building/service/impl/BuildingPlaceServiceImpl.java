package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.dao.BuildingPlaceMapper;
import org.fire.platform.modules.building.domain.BuildingPlace;
import org.fire.platform.modules.building.service.IBuildingPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-30 14:55:37
 */

@Service
public class BuildingPlaceServiceImpl implements IBuildingPlaceService {

    @Autowired
	private BuildingPlaceMapper mapper;
 
	public int insert(BuildingPlace bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingPlace bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingPlace get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<BuildingPlace> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingPlace> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingPlace> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<BuildingPlace> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<BuildingPlace> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingPlace> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
