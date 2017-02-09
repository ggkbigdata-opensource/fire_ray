package org.fire.platform.modules.data.controller;

import org.fire.platform.common.base.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DataHandlerControllerTest {
	
	@Autowired
	DataHandlerController controller;
	
	@Test
	public void testSumCheckReport(){
		CommonResult result =  controller.sumCheckReport("2014-01", "2014-05");
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	
	@Test
	public void testSumReportAnalysis(){
		CommonResult result =  controller.sumReportAnalysis(13L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testCalcStreetSafeIndex(){
		CommonResult result =  controller.calcStreetSafeIndex(1L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	
	
	
	
	
	

}
