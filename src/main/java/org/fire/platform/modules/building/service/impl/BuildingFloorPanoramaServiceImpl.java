package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingFloorPanoramaBean;
import org.fire.platform.modules.building.dao.BuildingFloorPanoramaMapper;
import org.fire.platform.modules.building.domain.BuildingFloorPanorama;
import org.fire.platform.modules.building.service.IBuildingFloorPanoramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

@Service
public class BuildingFloorPanoramaServiceImpl implements IBuildingFloorPanoramaService {

    @Autowired
	private BuildingFloorPanoramaMapper mapper;
 
	public int insert(BuildingFloorPanorama bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingFloorPanorama bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingFloorPanorama get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<BuildingFloorPanorama> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingFloorPanorama> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloorPanorama> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<BuildingFloorPanorama> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<BuildingFloorPanorama> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloorPanorama> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<BuildingFloorPanoramaBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFloorPanoramaBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
