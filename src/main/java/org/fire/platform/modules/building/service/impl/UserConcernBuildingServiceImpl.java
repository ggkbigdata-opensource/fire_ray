package org.fire.platform.modules.building.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.dao.UserConcernBuildingMapper;
import org.fire.platform.modules.building.domain.UserConcernBuilding;
import org.fire.platform.modules.building.service.IUserConcernBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */

@Service
public class UserConcernBuildingServiceImpl implements IUserConcernBuildingService {

    @Autowired
	private UserConcernBuildingMapper mapper;
 
	public int insert(UserConcernBuilding bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(UserConcernBuilding bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public UserConcernBuilding get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<UserConcernBuilding> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<UserConcernBuilding> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserConcernBuilding> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<UserConcernBuilding> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<UserConcernBuilding> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<UserConcernBuilding> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public int deleteByParam(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.deleteByParam(param);
	}
	
	
}
