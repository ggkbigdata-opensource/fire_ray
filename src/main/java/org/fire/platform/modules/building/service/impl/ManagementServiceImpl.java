package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.dao.ManagementMapper;
import org.fire.platform.modules.building.domain.Management;
import org.fire.platform.modules.building.service.IManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fire.platform.modules.building.bean.ManagementBean;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Service
public class ManagementServiceImpl implements IManagementService {

    @Autowired
	private ManagementMapper mapper;
 
	public int insert(Management bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Management bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Management get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Management> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<Management> queryPageByMap(int page,int size,Map param){
	   PageHelper.startPage(page,size);
	   List<Management> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<Management> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<Management> queryPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Management> queryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<Management> queryPageByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public  ManagementBean getByBuildingId(Long buildingId){
		return mapper.selectByBuildingId(buildingId);
	}
	
	
}
