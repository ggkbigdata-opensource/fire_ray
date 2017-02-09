package org.fire.platform.modules.sys.controller;

import org.fire.platform.common.base.CommonResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class DictControllerTest {
	
	@Autowired
	DictController controller;
	
	@Test
	public void testQueryConcernArea(){
		
		CommonResult result =  controller.queryByDictCode("block_type", 1, 10);
		Gson gson = new Gson();
		String ret = gson.toJson(result);
		System.out.println("结果为");
		System.out.println(ret);
	}

}
