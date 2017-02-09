package org.fire.platform.modules.front;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.QueryInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class QueryInfoControllerTest {
	
	@Autowired
	QueryInfoController controller;
	
	@Test
	public void testQueryFireEvent(){
		
		CommonResult result =  controller.queryFireEvent(1L, 1L, "1", "2016","2016","","",0, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	
	@Test
	public void testQueryCheckReport(){
		
		CommonResult result =  controller.queryCheckReport(1L, 1L, "2015","2015",null, 0, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testQueryFireEventDetail(){
		
		CommonResult result =  controller.getFireEventDetail(1L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testGetReportDetail(){
		
		CommonResult result =  controller.reportDetail(1L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
		
	}
	
	@Test
	public void testQueryPunish(){
		
		CommonResult result =  controller.queryPunishEvent(null, null, null,null,null, 0, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	


}
