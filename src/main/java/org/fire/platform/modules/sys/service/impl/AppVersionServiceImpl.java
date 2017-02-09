package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.AppVersionMapper;
import org.fire.platform.modules.sys.domain.AppVersion;
import org.fire.platform.modules.sys.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-29 17:43:16
 */

@Service
public class AppVersionServiceImpl implements IAppVersionService {

    @Autowired
	private AppVersionMapper mapper;
 
	public int insert(AppVersion bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(AppVersion bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public AppVersion get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<AppVersion> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<AppVersion> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<AppVersion> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<AppVersion> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<AppVersion> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<AppVersion> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public List<AppVersion> queryAPPVersionByMap(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectAPPVersionByMap(param);
	}
	
	
}
