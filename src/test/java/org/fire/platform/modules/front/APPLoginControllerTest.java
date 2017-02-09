package org.fire.platform.modules.front;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.APPLoginController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class APPLoginControllerTest {
	@Autowired
	private APPLoginController controller;
	
	@Test
	public void testLogin(){
		
		CommonResult result = controller.login("test", "123456", "12312245");
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
		
	}
	
	@Test
	public void testLogout(){
		CommonResult result = controller.logout(0L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}

}
