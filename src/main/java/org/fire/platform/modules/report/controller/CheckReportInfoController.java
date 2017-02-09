package org.fire.platform.modules.report.controller;

import org.apache.commons.io.FileUtils;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.report.bean.CheckReportInfoBean;
import org.fire.platform.modules.report.bean.ReportInfoBean;
import org.fire.platform.modules.report.process.PdfCoverProcess;
import org.fire.platform.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.fire.platform.modules.report.service.ICheckReportInfoService;
import org.fire.platform.modules.report.domain.CheckReportInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Company: Scho Techonlogy Co. Ltd
 *
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Controller
@RequestMapping("/checkReportInfo")
public class CheckReportInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CheckReportInfoController.class);
    private static final Map<String,CommonResult> CONVERING_FILE_MAP = new HashMap<>();
    private final ExecutorService excutor = Executors.newFixedThreadPool(5);

    @Autowired
    private ICheckReportInfoService service;
    @Autowired
    private PdfCoverProcess pdfCoverProcess;
    @Autowired
    private IBlockService blockService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public CommonResult query(int page, int rows,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "streetId", required = false) Long streetId,
                              @RequestParam(value = "blockId", required = false) Long blockId,
                              @RequestParam(value = "districtId", required = false) Long districtId,
                              @RequestParam(value = "reportType", required = false) String reportType,
                              @RequestParam(value = "code", required = false) String code,
                              @RequestParam(value = "checkStartMonth", required = false) String checkStartMonth,
                              @RequestParam(value = "checkEndMonth", required = false) String checkEndMonth) {
        log.info("CheckReportInfoController -> query params -> page = {}, rows = {}, name = {}, streetId = {}, blockId = {}, districtId = {}, reportType = {}, code = {}, checkStartMonth = {}, checkEndMonth = {} ", page, rows, name, streetId, blockId, districtId, reportType, code, checkStartMonth, checkEndMonth);
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.hasText(name)) {
            params.put("name", name);
        }
        if (streetId != null && streetId.intValue() != 0) {
            params.put("streetId", streetId);
        }
        if (blockId != null && blockId.intValue() != 0) {
            params.put("blockId", blockId);
        }
        if (districtId != null && districtId.intValue() != 0) {
            params.put("districtId", districtId);
        }
        if (StringUtils.hasText(reportType) && !reportType.equals("0")) {
            params.put("type", reportType);
        }
        if (StringUtils.hasText(code)) {
            params.put("code", code);
        }
        if (StringUtils.hasText(checkEndMonth)) {
            params.put("checkEndMonth", checkEndMonth);
        }
        if (StringUtils.hasText(checkStartMonth)) {
            params.put("checkStartMonth", checkStartMonth);
        }
        PageInfo<CheckReportInfoBean> pageInfo = service.queryBeanPageByMap(params, page, rows);
        return CommonResult.success(pageInfo.getList(), pageInfo.getTotal());
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public CheckReportInfo get(@RequestParam(value = "id") Long id) {
        log.info("get,id={}", id);
        return service.get(id);
    }

    @RequestMapping(value = "/getBeanInfo",method = RequestMethod.GET)
    @ResponseBody
    public CheckReportInfoBean getBeanInfo(Long reportId) {
        log.info("CheckReportInfoController -> getBeanInfo params -> reportId = {} " , reportId );
        return service.getReportInfoBean(reportId);
    }

    @RequestMapping(value = "/getBean", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getBean(Long reportId) {
        log.info("CheckReportInfoController -> getBean params -> reportId = {} " , reportId );
        ReportInfoBean infoBean = service.getReportBean(reportId);
        if(infoBean != null){
            return CommonResult.success(infoBean);
        }else {
            return CommonResult.fail("根据检测报告ID找不到对应的检测报告");
        }
    }

    @RequestMapping(value = "/insert")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CommonResult create(@RequestBody CheckReportInfo bean) {
        log.info("create,bean={}", bean);
        try {
            service.insert(bean);
            return CommonResult.success("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return CommonResult.fail("新增失败");
        }

    }

    @RequestMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResult update(@RequestBody CheckReportInfo bean) {
        log.info("update,bean={}", bean);
        try {
            service.update(bean);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return CommonResult.fail("修改失败");
        }

    }

    @RequestMapping(value = "/deleteReport",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResult delete(@RequestBody List<Long> reportIds) {
        log.info("CheckReportInfoController -> delete params -> reportIds = {} " , reportIds );
        try {
            for (Long id : reportIds) {
                service.deleteReport(id);
            }
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            log.error("删除失败", e);
            return CommonResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/uploadReportFile", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadReportFile(@RequestBody MultipartFile uploadFile,@RequestParam final Long blockId) {
        if(!checkFile(uploadFile)){
            return CommonResult.fail("不正确的文件！");
        }
        final Block block = blockService.get(blockId);
        if(block == null){
            return CommonResult.fail("无效地址！");
        }
        final String fileKey = UUID.randomUUID().toString();
        CONVERING_FILE_MAP.put(fileKey, null);
        String dir = FileUtil.getTomcatPath()+"/reportPdf";
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        file = new File(file,uploadFile.getOriginalFilename()+".pdf");
        try {
            FileUtils.writeByteArrayToFile(file,uploadFile.getBytes(),false);
        } catch (IOException e) {
            log.error("保存文件失败！",e);
            return CommonResult.fail("保存文件失败！");
        }
        final File finalFile = file;
        excutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                CheckReportInfo info = new CheckReportInfo();
                info.setBlockId(block.getId());
                info.setStreetId(block.getStreetId());
                info.setDistrictId(block.getDistrictId());
                try {
                    CommonResult commonResult = pdfCoverProcess.coverPDFAndInsert(finalFile, info);
                    CONVERING_FILE_MAP.put(fileKey,commonResult);
                }catch (Exception e){
                    CONVERING_FILE_MAP.put(fileKey,CommonResult.fail(e.getMessage()));
                }
                return null;
            }
        });
        CommonResult commonResult = CommonResult.success(fileKey);
        commonResult.setMsg("上传成功。后台正在分析报告！");
        return commonResult;
    }

    private boolean checkFile(MultipartFile uploadFile) {
        String originalFileName = uploadFile.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
        if(".pdf".equalsIgnoreCase(suffix)){
            return true;
        }else {
            return false;
        }
    }

    @RequestMapping(value = "/progress", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getProgress(@RequestParam String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        if (CONVERING_FILE_MAP.containsKey(fileName)) {
            CommonResult result = CONVERING_FILE_MAP.get(fileName);
            if(result == null){
                return CommonResult.fail();
            }else{
                CONVERING_FILE_MAP.remove(fileName);
                return result;
            }
        } else {
            return null;
        }
    }
}
