package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.LoginLogMapper;
import org.fire.platform.modules.sys.domain.LoginLog;
import org.fire.platform.modules.sys.service.ILoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */

@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
	private LoginLogMapper mapper;
 
	public int insert(LoginLog bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(LoginLog bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public LoginLog get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<LoginLog> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<LoginLog> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<LoginLog> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<LoginLog> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<LoginLog> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<LoginLog> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
