package org.fire.platform.modules.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.bean.UserBean;
import org.fire.platform.modules.sys.dao.UserMapper;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
	private UserMapper mapper;
 
	public int insert(User bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(User bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public User get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<User> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<User> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<User> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<User> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<User> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	/**
	 * @author wang
	 */
	@Override
	public User getUserByUserNameAndPassWord(String username, String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", password);
		List<User> users = mapper.selectByMap(map);
		
		if( CollectionUtils.isEmpty(users)){
			return null;
		}
		
		return users.get(0);
	}


	@Override
	public PageInfo<UserBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<UserBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
