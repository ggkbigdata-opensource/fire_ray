package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingPanoramaBean;
import org.fire.platform.modules.building.dao.BuildingPanoramaMapper;
import org.fire.platform.modules.building.domain.BuildingPanorama;
import org.fire.platform.modules.building.service.IBuildingPanoramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

@Service
public class BuildingPanoramaServiceImpl implements IBuildingPanoramaService {

    @Autowired
	private BuildingPanoramaMapper mapper;
 
	public int insert(BuildingPanorama bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingPanorama bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingPanorama get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<BuildingPanorama> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingPanorama> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingPanorama> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<BuildingPanorama> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<BuildingPanorama> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingPanorama> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<BuildingPanoramaBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingPanoramaBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
