package org.fire.platform.modules.statis.controller;

import org.fire.platform.common.base.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class StatisControllerTest {
	@Autowired
	StatisController controller;
	
	@Test
	public void testGetFireReasonTypePercent(){
		
		
		CommonResult ret = controller.getFireReasonTypePercent("smoke", "2016-9", "2016-11", "2016",79L, 1);
		Gson gson = new Gson();
		System.out.println(gson.toJson(ret));
		
	}
	
	@Test
	public void testGetFirePlaceTypePercent(){
		
		
		CommonResult ret = controller.getFirePlaceTypePercent(null, "2016-9", "2016-10", "2016",1L, 0);
		Gson gson = new Gson();
		System.out.println(gson.toJson(ret));
		
	}
	
	
	
	
}
