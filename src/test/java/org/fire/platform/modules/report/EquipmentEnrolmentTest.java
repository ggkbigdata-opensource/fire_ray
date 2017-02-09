package org.fire.platform.modules.report;

import org.fire.platform.modules.report.service.IEquipmentEnrolmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/5 005
 * Time: 13:35
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class EquipmentEnrolmentTest {

    @Autowired
    private IEquipmentEnrolmentService service;

    @Test
    public void testGetBeans(){
        service.getBeanList2(1L);
    }
}
