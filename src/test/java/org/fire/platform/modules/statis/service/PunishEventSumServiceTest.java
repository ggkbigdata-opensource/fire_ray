package org.fire.platform.modules.statis.service;

import java.util.Date;

import org.fire.platform.modules.statis.domain.PunishEventSum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PunishEventSumServiceTest {
	
	@Autowired
	IPunishEventSumService service;
	
	@Test
	public void testInsert(){
		for( int i =1;i<=9;i++){
			
			PunishEventSum bean = new PunishEventSum();
			bean.setYear("2016");
			bean.setMonth("2016-"+trans2Str(i));
			bean.setDistrictId(1L);
			bean.setStreetId(1L);
			bean.setBlockId(1L);
			
			
			bean.setDetainedNum(110+i*10);
			bean.setFineNum(130+i*5);
			bean.setFineTotal(1500.0+i*100);
			bean.setThreeStopNum(8+2*i);
			bean.setCreateUser(1L);
			bean.setCreateDate(new Date());
			
			service.insert(bean);
		}
	}
	
	private String trans2Str(int i ){
		if( i < 10){
			return "0"+i;
		}else{
			return ""+i;
		}
	}

}
