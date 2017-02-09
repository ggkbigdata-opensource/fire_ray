package org.fire.platform.modules.check.service;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class CheckReportServiceTest {
	
	@Autowired
	ICheckReportService service;
	

	@Test
	public void testInsert() throws Exception{
		CheckReport bean = new CheckReport();
		
		Date begin = DateUtil.parseDate("YYYY-mm", "2014-01");
		
		for(int i=1;i<=12;i++){
			bean.setBlockId(1L);
			bean.setStreetId(1L);
			bean.setBuildingId(1L);
			bean.setCode("TEST_"+i);
			bean.setDistrictId(1L);
			bean.setIsPass(1);
			bean.setModDate(new Date());
			bean.setName("CHECK_REPORT"+i);
			bean.setPlaceName("测试地址_"+i);
			bean.setPubTime(DateUtils.addMonths(begin, i-1));
			bean.setRemark("测试报告");
			bean.setReportFileUrl("");
			bean.setReportImage("");
			bean.setReportType(""+(i%2));
			service.insert(bean);
		}
		
		
	}


	

}
