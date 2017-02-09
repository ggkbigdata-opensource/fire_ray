package org.fire.platform.modules.area.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.dao.UserConcernAreaMapper;
import org.fire.platform.modules.area.domain.UserConcernArea;
import org.fire.platform.modules.area.service.IUserConcernAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */

@Service
public class UserConcernAreaServiceImpl implements IUserConcernAreaService {

    @Autowired
	private UserConcernAreaMapper mapper;
 
	public int insert(UserConcernArea bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(UserConcernArea bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public UserConcernArea get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<UserConcernArea> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<UserConcernArea> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<UserConcernArea> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<UserConcernArea> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<UserConcernArea> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<UserConcernArea> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public int deleteByParam(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.deleteByParam(map);
	}

    @Override
    public int isConcerned(Integer areaType, Long areaId, Long userId) {
        return mapper.selectByTypeAndAreaIdAndUserId(areaType,areaId,userId);
    }


}
