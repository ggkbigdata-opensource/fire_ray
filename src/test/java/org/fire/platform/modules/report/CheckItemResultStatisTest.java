package org.fire.platform.modules.report;

import org.fire.platform.modules.report.service.ICheckItemResultStatisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 11:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class CheckItemResultStatisTest {

    @Autowired
    private ICheckItemResultStatisService checkItemResultStatisService;

    @Test
    public void insertStatis(){
//        checkItemResultStatisService.sumStatisByReportId(1L);
    }

    @Test
    public void getStatisBean(){
        checkItemResultStatisService.queryStatisBeanByReportId(2, 20, 1L);
    }

}
