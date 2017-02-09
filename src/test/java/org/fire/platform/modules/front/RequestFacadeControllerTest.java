package org.fire.platform.modules.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.front.controller.RequestFacadeController;
import org.fire.platform.modules.front.vo.RequestVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;


@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class RequestFacadeControllerTest {
	
	@Autowired
	RequestFacadeController controller;
	
	@Test
	public void testQueryFireEvent(){
		List<RequestVo> batchRequest = new ArrayList<RequestVo>();
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("areaId", 1L);
		params.put("areaType", 0);
		RequestVo requestVo = new RequestVo();
		requestVo.setApiKey("app/areaInfo/compareFire");
		requestVo.setParams(params);
		
		RequestVo requestVo1 = new RequestVo();
		requestVo1.setApiKey("app/areaInfo/compareCheckReport");
		requestVo1.setParams(params);
		
		batchRequest.add(requestVo);
		batchRequest.add(requestVo1);
		
		Map<String,CommonResult> result =  controller.batchInvoke(batchRequest);
		Gson gson = new Gson();
		String req = gson.toJson(batchRequest);
		String ret = gson.toJson(result);
		System.out.println("请求为");
		System.out.println(req);
		System.out.println("结果为");
		System.out.println(ret);
	}
	
	
	
	


}
