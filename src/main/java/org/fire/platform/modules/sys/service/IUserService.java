package org.fire.platform.modules.sys.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.bean.UserBean;
import org.fire.platform.modules.sys.domain.User;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */
 
public interface IUserService extends IService<User> {
	
	/**
	 * @author wang
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUserByUserNameAndPassWord(String username,String password);
	
	public PageInfo<UserBean> queryPageBeanByMap(Map<String, Object> map,int pageNo, int pageSize);

}
