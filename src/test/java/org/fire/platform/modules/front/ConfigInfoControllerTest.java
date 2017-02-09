package org.fire.platform.modules.front;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.ConfigInfoController;
import org.fire.platform.modules.front.controller.QueryInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class ConfigInfoControllerTest {
	
	@Autowired
	ConfigInfoController controller;
	
	@Test
	public void testQueryAppVersion(){
		
		CommonResult result =  controller.getVersion("1.0", 1);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	
	
	


}
