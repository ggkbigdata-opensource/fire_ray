package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.bean.BuildingBean;
import org.fire.platform.modules.building.bean.BuildingSubjectBean;
import org.fire.platform.modules.building.dao.BuildingSubjectMapper;
import org.fire.platform.modules.building.domain.BuildingSubject;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 11:20:25
 */

@Service
public class BuildingSubjectServiceImpl implements IBuildingSubjectService {

    @Autowired
	private BuildingSubjectMapper mapper;
 
	public int insert(BuildingSubject bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingSubject bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingSubject get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<BuildingSubject> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingSubject> queryPageByMap(int page,int size,Map param){
	   PageHelper.startPage(page,size);
	   List<BuildingSubject> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}


	@Override
	public List<BuildingSubject> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PageInfo<BuildingSubject> queryPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<BuildingSubject> queryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectByMap(map);
	}


	@Override
	public PageInfo<BuildingSubject> queryPageByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public PageInfo<BuildingSubjectBean> queryPageBeanByMap(Map<String, Object> map,
															int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingSubjectBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}
	
}
