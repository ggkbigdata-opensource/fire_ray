package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingFloorBean;
import org.fire.platform.modules.building.dao.BuildingFloorMapper;
import org.fire.platform.modules.building.domain.BuildingFloor;
import org.fire.platform.modules.building.service.IBuildingFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

@Service
public class BuildingFloorServiceImpl implements IBuildingFloorService {

    @Autowired
	private BuildingFloorMapper mapper;
 
	public int insert(BuildingFloor bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingFloor bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingFloor get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<BuildingFloor> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingFloor> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloor> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<BuildingFloor> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<BuildingFloor> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloor> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<BuildingFloorBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloorBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
