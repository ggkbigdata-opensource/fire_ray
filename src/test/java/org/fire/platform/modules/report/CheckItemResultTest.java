package org.fire.platform.modules.report;

import org.apache.commons.lang.math.RandomUtils;
import org.fire.platform.modules.report.domain.CheckItemDef;
import org.fire.platform.modules.report.domain.CheckItemResult;
import org.fire.platform.modules.report.service.ICheckItemDefService;
import org.fire.platform.modules.report.service.ICheckItemResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 9:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class CheckItemResultTest {

    @Autowired
    private ICheckItemDefService checkItemDefService;
    @Autowired
    private ICheckItemResultService checkItemResultService;

    /**
     * 根据检测定义项生成随机检测数据
     */
    @Test
    public void insertResult(){
        List<CheckItemDef> defList = checkItemDefService.getCheckItem();
        List<CheckItemResult> resultList = new ArrayList<>();
        for (CheckItemDef def : defList) {
            int checkNum = RandomUtils.nextInt(5) + 3;
            int un = RandomUtils.nextInt(checkNum);
            CheckItemResult result = new CheckItemResult();
            result.setItemId(def.getId());
            result.setReportId(1L);
            result.setCheckNum(checkNum);
            result.setUnqualifiedNum(un);
            resultList.add(result);
        }
//        checkItemResultService.batchInsert(1L, resultList);
    }

    @Test
    public void testGetResultBean(){
        checkItemResultService.queryResultBeanByReportId(1,20,1L);
    }
}
