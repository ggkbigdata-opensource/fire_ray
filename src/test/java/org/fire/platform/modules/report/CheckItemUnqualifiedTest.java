package org.fire.platform.modules.report;

import com.google.common.collect.Lists;
import com.ray.pdfparser.PDFParser;
import com.ray.pdfparser.PDFParserResult;
import org.apache.commons.lang.math.RandomUtils;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.domain.CheckItemResult;
import org.fire.platform.modules.report.domain.CheckItemUnqualified;
import org.fire.platform.modules.report.service.ICheckItemResultService;
import org.fire.platform.modules.report.service.ICheckItemUnqualifiedService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/1 001
 * Time: 9:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class CheckItemUnqualifiedTest {
    @Autowired
    private ICheckItemUnqualifiedService checkItemUnqualifiedService;
    @Autowired
    private ICheckItemResultService checkItemResultService;

    /**
     *根据检测结果生成测试数据
     */
    @Test
    public void insertUnqualifiedItem(){
        PageInfo<CheckItemResultBean> list = checkItemResultService.queryResultBeanByReportId(1,22222,1L);
        List<CheckItemResultBean> beanList = list.getList();
        List<CheckItemUnqualified> unqualifiedList = Lists.newArrayList();
        for (CheckItemResultBean resultBean : beanList) {
            if(resultBean.getUnqualifiedNum() != null && resultBean.getUnqualifiedNum().intValue() > 0) {
                for (Integer i = 0; i < resultBean.getUnqualifiedNum(); i++) {
                    String o = "0000000000";
                    String code = "" + RandomUtils.nextInt(100000000);
                    String positionCode = "" + RandomUtils.nextInt(999);
                    String position = "第" + RandomUtils.nextInt(30) + "层、第" + o.substring(0, 3 - positionCode.length()) + positionCode + "号检测点 ";
                    CheckItemUnqualified unqualified = new CheckItemUnqualified();
                    unqualified.setReportId(resultBean.getReportId());
                    unqualified.setItemId(resultBean.getItemId());
                    unqualified.setCode(o.substring(0, 10 - code.length()) + code);
                    unqualified.setPosition(position);
                    System.out.println("code --> position  " + unqualified.getCode() + " --> " + unqualified.getPosition());
                    unqualifiedList.add(unqualified);
                }
            }
        }
//        checkItemUnqualifiedService.batchInsert(unqualifiedList);
    }

    @Test
    public void getBeanByMap(){
        checkItemUnqualifiedService.getUnqualifiedBeanByReportId(1,100,1L);
    }
}
