package org.fire.platform.modules.report;

import com.ray.pdfparser.ListResult;
import com.ray.pdfparser.PDFParser;
import com.ray.pdfparser.PDFParserResult;
import org.fire.platform.modules.report.domain.EquipmentType;
import org.fire.platform.modules.report.service.IEquipmentTypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/2 002
 * Time: 14:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class EquipmentTypeTest {

    @Autowired
    private IEquipmentTypeService typeService;

    /**
     * 初始化设备分类
     */
    @Test
    public void insertType() throws IOException {
        PDFParser parser = new PDFParser();
        PDFParserResult parse = parser.parse(new File("C:\\Users\\ZKT\\Desktop\\消防\\检测报告\\检测报告样本.pdf"));
        List<ListResult> fifthPart = parse.getFifthPart();
        List<EquipmentType> typeList = new ArrayList<>();
        for (ListResult listResult : fifthPart) {
            String key = listResult.getLabel();
            Pattern pattern = Pattern.compile("^(\\d+)");
            Matcher matcher = pattern.matcher(key);
            if(matcher.find()) {
                String code = matcher.group(1);
                String name = key.replace(code,"").trim();
                EquipmentType type = new EquipmentType();
                type.setCode(code);
                type.setName(name);
                typeList.add(type);
            }
        }
//        typeService.batchInsert(typeList);
    }

}
