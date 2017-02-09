package org.fire.platform.modules.area.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaServiceTest {
	
	@Autowired
	IAreaService service;
	
	@Test
	public void testQueryConcernArea(){
		service.queryConcernArea(0L, 1, 10);
	}
	@Test
	public void testQueryFireCompareData(){
		service.queryFireCompareData(0L, 0);
	}
	@Test
	public void testQueryArea(){
		//service.queryArea("", 1, 1, 10);
	}
	

}
