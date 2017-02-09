package org.fire.platform.modules.building.controller;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.BuildingInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BuildingInfoControllerTest {
	
	@Autowired
	BuildingInfoController controller;
	
	@Test
	public void testQueryConcernBuilding(){
		
		CommonResult result =  controller.queryConcernBuilding(1L, 1, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testGetBuildingDetail(){
		
		CommonResult result =  controller.getDetail(1L,0L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testSearch(){
		
		CommonResult result =  controller.search("南", 0, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testAddConcern(){
		
		CommonResult result =  controller.addConcern(1L, 2L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	

	@Test
	public void testCancelConcern(){
		
		CommonResult result =  controller.cancelConcern(1L, 2L);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	@Test
	public void testQueryBuildingTypeCount(){
		
		CommonResult result =  controller.queryBuildingTypeCount(1L,1);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	

}
