package org.fire.platform.modules.report.process;

import com.ray.pdfparser.*;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.domain.*;
import org.fire.platform.modules.report.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/1 001
 * Time: 17:20
 */
@Component
@Transactional
public class PdfCoverProcess {
    private static final Logger log = LoggerFactory.getLogger(PdfCoverProcess.class);
    private List<CheckItemResultStatis> defStatusList = null;
    private Map<String,CheckItemResultBean> itemResultBeanMap = null;

    @Autowired
    private ICheckReportInfoService infoService;
    @Autowired
    private ICheckItemDefService defService;
    @Autowired
    private ICheckItemResultStatisService statisService;
    @Autowired
    private ICheckItemResultService resultService;
    @Autowired
    private ICheckItemUnqualifiedService unqualifiedService;
    @Autowired
    private IEquipmentEnrolmentService enrolmentService;

    @Transactional(rollbackFor = Exception.class)
    public CommonResult coverPDFAndInsert(File pdf, CheckReportInfo reportInfo) throws Exception {
        PDFParser parser = new PDFParser();
        PDFParserResult result = null;
        try {
            log.debug("解析PDF --> {}",pdf.getAbsolutePath());
            result = parser.parse(pdf);
        } catch (IOException e) {
            log.error("解析PDF失败！--> file={} ", pdf.getAbsolutePath(), e);
            return CommonResult.fail("解析PDF出错！");
        }
        Long reportId = null;
        //保存报告主信息
        log.debug("开始保存主信息 --> {}", pdf.getAbsolutePath());
        boolean sInfo = saveInfo(result.getCover(), reportInfo, result.getSecondPart());
        if (!sInfo) {
            log.error("保存报告失败 --> reportInfo={}", reportInfo);
            throw new Exception("保存报告失败！");
        }
        reportId = reportInfo.getId();
        //初始化定义数据
        initDef();
        //保存单项评定结果
        boolean sStatis = saveStatis(reportId, result.getFirstPart());
        if (!sStatis) {
            log.error("保存单项评定结果失败 --> FirstPart={}", Arrays.toString(result.getFirstPart().toArray()));
            throw new Exception("保存单项评定结果失败！");
        }
        boolean sItem = saveItem(reportId, result.getThirdPart());
        if (!sItem) {
            log.error("保存检测情况统计表失败 --> ThirdPart={}", Arrays.toString(result.getThirdPart().toArray()));
            throw new Exception("保存消防设施检测不符合规范要求项目失败！");
        }
        boolean sUnqualified = saveUnqualified(reportId, result.getForthPart());
        if (!sUnqualified) {
            log.error("保存消防设施检测不符合规范要求项目失败 --> ForthPart={}", Arrays.toString(result.getForthPart().toArray()));
            throw new Exception("保存消防设施检测不符合规范要求项目失败！");
        }
        boolean sEquipment = saveEquipment(reportId, result.getFifthPart());
        if (!sEquipment) {
            log.error("保存消防设备登记表失败 --> FifthPart={}", Arrays.toString(result.getFifthPart().toArray()));
            throw new Exception("保存消防设备登记表失败！");
        }
        return CommonResult.success(reportId);
    }

    /**
     * 初始化定义数据
     */
    private void initDef(){
        defStatusList = defService.getStatisItem();
        List<CheckItemResultBean> itemResult = defService.getItemResult();
        itemResultBeanMap = new HashMap<>();
        for (CheckItemResultBean resultBean : itemResult) {
            itemResultBeanMap.put(resultBean.getCode(),resultBean);
        }
    }

    /**
     * 保存封面信息
     * @param cover 解析出来的数据
     * @param reportInfo 封面domain
     * @param secondPart 检测结论说明
     * @return 是否成功
     */
    private boolean saveInfo(Cover cover, CheckReportInfo reportInfo, String secondPart) {
        reportInfo.setCode(cover.getReportNum());
//        reportInfo.setProjectCode();
        reportInfo.setName(cover.getProjectName());
        reportInfo.setAddress(cover.getProjectAddress());
        reportInfo.setDelegate(cover.getAgentName());
        reportInfo.setDetectionUnit(cover.getQaName());
        reportInfo.setDuAddress(cover.getQaAddress());
        reportInfo.setDuTel(cover.getContactTel());
        reportInfo.setDuFax(cover.getContactFax());
        reportInfo.setDuZipCode(cover.getContactPostcode());
        reportInfo.setResultDesc(secondPart);
        int res = infoService.insert(reportInfo);
        return res == 1;
    }


    /**
     * 保存 单项评定结果 表数据
     * @param id 检测报告ID
     * @param firstPart 解析出来的数据
     * @return 是否成功
     */
    private boolean saveStatis(Long id, List<Result> firstPart) {
        List<CheckItemResultStatis> statisList = new ArrayList<>();
        Map<String, CheckItemResultStatis> statisMap = new HashMap<>();
        for (CheckItemResultStatis checkItemResultStatis : defStatusList) {
            String key = checkItemResultStatis.getCode() + checkItemResultStatis.getLevel();
            statisMap.put(key,checkItemResultStatis);
        }
        String value = null;
        try {
            for (Result result : firstPart) {
                String key = result.getLabel();
                if (StringUtils.hasText(key) && statisMap.get(key) != null) {
                    CheckItemResultStatis statis = statisMap.get(key);
                    statis.setReportId(id);
                    value = result.getValue1();
                    statis.setCheckNum(Integer.valueOf(value));
                    value = result.getValue2();
                    statis.setUnqualifiedNum(Integer.valueOf(value));
                    statisList.add(statis);
                }
            }
        }catch (NumberFormatException nfe){
            log.error("转换【单项评定结果】错误，无法转换数字，value={}",value);
            return false;
        }
        int rst = statisService.batchInsert(statisList);
        return rst==statisList.size();
    }

    /**
     * 检测情况统计表
     * @param id 检测报告ID
     * @param thirdPart 解析出来的数据
     * @return 是否成功
     */
    private boolean saveItem(Long id, List<Result> thirdPart) {
        List<CheckItemResult> resultList = new ArrayList<>();
        String value = null;
        try {
            for (Result result : thirdPart) {
                String key = result.getLabel();
                if (StringUtils.hasText(key) && itemResultBeanMap.get(key) != null) {
                    CheckItemResultBean resultBean = itemResultBeanMap.get(key);
                    CheckItemResult itemResult = new CheckItemResult();
                    itemResult.setReportId(id);
                    itemResult.setItemId(resultBean.getItemId());
                    value = result.getValue1();
                    itemResult.setCheckNum(Integer.valueOf(value));
                    value = result.getValue2();
                    itemResult.setUnqualifiedNum(Integer.valueOf(value));
                    resultList.add(itemResult);
                }
            }
        }catch (NumberFormatException nfe){
            log.error("转换【检测情况统计表】错误，无法转换数字，value={}",value);
            return false;
        }
        int rst = resultService.batchInsert(id, resultList);
        return rst == resultList.size();
    }

    /**
     * 保存 消防设施检测不符合规范要求项目 表数据
     * @param id 检测报告ID
     * @param forthPart 解析出来的数据
     * @return 是否成功
     */
    private boolean saveUnqualified(Long id, List<ListResult> forthPart){
        List<CheckItemUnqualified> unqualifiedList = new ArrayList<>();
        for (ListResult listResult : forthPart) {
            String key = listResult.getLabel();
            if(StringUtils.hasText(key) && itemResultBeanMap.get(key) != null){
                CheckItemResult itemResult = itemResultBeanMap.get(key);
                List<String> lines = listResult.getStrings();
                Pattern codePatten = Pattern.compile("(\\d{10})");
                for (String line : lines) {
                    Matcher matcher = codePatten.matcher(line);
                    if(matcher.find()){
                        CheckItemUnqualified unqualified = new CheckItemUnqualified();
                        unqualified.setReportId(id);
                        unqualified.setItemId(itemResult.getItemId());
                        unqualified.setCode(matcher.group(1));
                        unqualified.setPosition(line.replace(unqualified.getCode(),"").trim());
                        unqualifiedList.add(unqualified);
                    }else{
                        log.error("没有找到---> {}",line);
                    }
                }
            }
        }
        int rst = unqualifiedService.batchInsert(unqualifiedList);
        return rst == unqualifiedList.size();
    }

    /**
     * 保存消防设备登记表
     * @param reportId 检测报告ID
     * @param fifthPart 解析出来的数据
     * @return  是否成功
     */
    private boolean saveEquipment(Long reportId ,List<ListResult> fifthPart){
        List<EquipmentEnrolment> enrolmentList = new ArrayList<>();
        for (ListResult listResult : fifthPart) {
            String key = listResult.getLabel();
            Pattern pattern = Pattern.compile("^(\\d+)");
            Matcher matcher = pattern.matcher(key);
            if(matcher.find()) {
                String code = matcher.group(1);
                List<String> strings = listResult.getStrings();
                for (String string : strings) {
                    EquipmentEnrolment equipment = new EquipmentEnrolment();
                    equipment.setTypeCode(code);
                    equipment.setReportId(reportId);
                    equipment.setInfo(string);
                    enrolmentList.add(equipment);
                }
            }
        }
        int rst = enrolmentService.batchInsert(enrolmentList);
        return rst == enrolmentList.size();
    }

//    public static void main(String[] args) {
//        String test = "0000001001 第1层、第001号检测点";
//        Pattern codePatten = Pattern.compile("(\\d{10})");
//        Matcher matcher = codePatten.matcher(test);
//        System.out.println(matcher.find());
//        System.out.println(matcher.group(1));
//    }

}
