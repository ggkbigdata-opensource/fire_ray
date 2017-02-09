package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.fire.platform.modules.building.dao.BuildingFunctionMapper;
import org.fire.platform.modules.building.domain.BuildingFunction;
import org.fire.platform.modules.building.service.IBuildingFunctionService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-12-26 16:28:43
 */

@Service
public class BuildingFunctionServiceImpl implements IBuildingFunctionService {

    @Autowired
	private BuildingFunctionMapper mapper;
 
	public int insert(BuildingFunction bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(BuildingFunction bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public BuildingFunction get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BuildingFunction> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<BuildingFunction> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFunction> functions = mapper.selectAll();
		return PageHelper.getPageInfo(functions);
	}

	@Override
	public List<BuildingFunction> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<BuildingFunction> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<BuildingFunction> functions = mapper.selectByMap(map);
		return PageHelper.getPageInfo(functions);
	}

	public List<BuildingFunction> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<BuildingFunction> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<BuildingFunction> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}
	

	
}
