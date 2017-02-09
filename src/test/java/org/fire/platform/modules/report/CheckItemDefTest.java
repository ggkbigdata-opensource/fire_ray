package org.fire.platform.modules.report;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.fire.platform.modules.report.domain.CheckItemDef;
import org.fire.platform.modules.report.service.ICheckItemDefService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/29 029
 * Time: 16:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-mvc.xml"})
public class CheckItemDefTest {

    @Autowired
    private ICheckItemDefService service;

    private List<String> tempString = new ArrayList<>();
    private List<CheckItemDef> defList = new ArrayList<>();
    private List<String> level = new ArrayList<>();

    {
        level.add("A");
        level.add("B");
        level.add("C");
    }


    /**
     * 根据pdf模版转换成word文档后的文件生成检测定义项
     */
    @Test
    public void insertDef(){
        readWord();
        for (CheckItemDef def : defList) {
            System.out.println(def);
//            service.insert(def);
        }
    }

    @Test
    public void TestGetDef(){
        service.getItemResult();
        service.getStatisItem();
    }

    //转换word
    private String readWord()
    {
        //word文档路径
        String pathword="C:\\Users\\ZKT\\Desktop\\检测报告样本-表格.doc";
        try {
            //创建存储word文档的对象
            HWPFDocument doc = new HWPFDocument(new FileInputStream(pathword));
            //用来获得word文档内容
            Range range=doc.getRange();
            //文档段落数目
            int paragraphCount=range.numParagraphs();
            //遍历段落读取数据
            for(int i=0;i<paragraphCount;i++)
            {
                Paragraph pph=range.getParagraph(i);
                String text = pph.text().trim();
                if(StringUtils.isEmpty(text)){
                    continue;
                }
                boolean isCode = checkCode(text);
                if(isCode) {
                    conversionObject();
                    tempString.clear();
                }
                tempString.add(text);
            }
            conversionObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean checkLevel(String levelString){
        return level.contains(levelString);
    }

    private boolean checkCode(String codeString){
        //检测大分类
        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcher = pattern.matcher(codeString);
        if(matcher.matches()){
            if(codeString.equals("1")){
                return false;
            }else{
                return true;
            }
        }
        //检测小分类
        pattern = Pattern.compile("[\\d]+\\.[\\d]+");
        Pattern pattern1 = Pattern.compile("[\\d]+\\.[\\d]+\\.[\\d]+");
        matcher = pattern.matcher(codeString);
        Matcher matcher1 = pattern1.matcher(codeString);
        return matcher.matches() || matcher1.matches();
    }

    private String getCodeLevel(String codeString){
        if(codeString.indexOf(".") == -1){
            return "0";
        }
        return codeString.substring(0,codeString.lastIndexOf("."));
    }

    private void conversionObject(){
        if(!tempString.isEmpty()){
            CheckItemDef def = new CheckItemDef();
            defList.add(def);
            def.setCode(tempString.get(0));
            def.setParentCode(getCodeLevel(def.getCode()));
            //移除CODE
            tempString.remove(0);
            //拼凑name
            StringBuilder builder = new StringBuilder();
            while (tempString.size() > 0 && !checkLevel(tempString.get(0))) {
                builder.append(tempString.get(0));
                tempString.remove(0);
            }
            def.setName(builder.toString());
            if(tempString.size() == 0){
                return;
            }
            def.setLevel(tempString.get(0));
            //移除LEVEL
            tempString.remove(0);
            //拼凑Standard
            builder = new StringBuilder();
            while (tempString.size() > 2){
                builder.append(tempString.get(0));
                tempString.remove(0);
            }
            def.setStandard(builder.toString());
        }
    }


}
