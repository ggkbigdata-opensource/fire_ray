package org.fire.platform.modules.report;

import org.fire.platform.modules.report.service.ICheckReportInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 16:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class CheckReportInfoTest {
    @Autowired
    private ICheckReportInfoService checkReportInfoService;

    @Test
    public void TestGetReportInfoBean(){
        checkReportInfoService.getReportBean(1L);
    }
}
