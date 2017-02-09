package org.fire.platform.modules.sys.service;

import java.util.List;

import org.fire.platform.modules.sys.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class SysUserServiceTest {
	
	@Autowired
	private IUserService service;
	
	

	@Test
	public void testFindAll() {
		List<User> ls =  service.queryAll();
		
		System.out.println(ls);
	}
	

}
