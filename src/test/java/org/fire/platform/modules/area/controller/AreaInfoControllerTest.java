package org.fire.platform.modules.area.controller;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.AreaInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaInfoControllerTest {
	
	@Autowired
	AreaInfoController controller;
	
	@Test
	public void testQueryConcernArea(){
		
		CommonResult result =  controller.queryConcernArea(1L, 1, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testAddConcern(){
		
		CommonResult result =  controller.addConcern(1L, 0, 2L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testCancelConcern(){
		
		CommonResult result =  controller.cancelConcern(1L, 0, 2L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testQueryDistrict(){
		
		CommonResult result =  controller.queryDistrict("", 0, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testQueryStreet(){
		
		CommonResult result =  controller.queryStreet(null, null, 1, -1);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testQueryArea(){
		
		CommonResult result =  controller.queryArea("",1L,1L,null, 1, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testTrendReport(){
		
		CommonResult result =  controller.trendReport(1L, 0);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testCompareFire(){
		
		CommonResult result =  controller.compareFire(1L, 0);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testCompareCheckReport(){
		
		CommonResult result =  controller.compareCheckReport(1L, 0);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testComparePunish(){
		
		CommonResult result =  controller.comparePunish(1L, 0);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testGetDistrictDetail(){
		CommonResult result =  controller.getDistrictDetail(1L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testGetAreaDetail(){
		CommonResult result =  controller.queryDetail(1L, 1, null,1L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	

}
