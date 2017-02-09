package org.fire.platform.modules.area.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class AreaMapServiceTest {
	
	@Autowired
	IAreaMapService service;
	
	@Test
	public void testGenerateMap(){
		String str = service.generateMap();
		
		System.out.println(str);
	}
	
	@Test
	public void testGenerateWebMap(){
		String str = service.generateWebMap();
		
		System.out.println(str);
	}
	

}
