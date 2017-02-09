package org.fire.platform.modules.check.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.WriterException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.service.IBuildingService;
import org.fire.platform.modules.check.bean.CheckReportBean;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.report.domain.CheckReportInfo;
import org.fire.platform.modules.report.service.ICheckReportInfoService;
import org.fire.platform.modules.statis.handler.XlsLibCheckReportHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Company: Scho Techonlogy Co. Ltd
 *
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Controller
@RequestMapping("/checkReport")
public class CheckReportController {

    @Autowired
    private IStreetService streetService;

    @Autowired
    private IBlockService blockService;

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private ICheckReportService service;

    @Autowired
    private XlsLibCheckReportHandler xlsLibCheckReportHandler;
    
    @Autowired
    private IDictService dictService;

    @Autowired
    private ICheckReportInfoService checkReportInfoService;

    private static Logger log = LoggerFactory.getLogger(CheckReportController.class);

    @RequestMapping(value = "/queryPage")
    @ResponseBody
    public Map<String, Object> queryPage(
            @RequestParam(value = "page", defaultValue = "1") int pageNo,
            @RequestParam(value = "rows", defaultValue = "10") int pageSize,
            HttpSession session,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String districtId,
            @RequestParam(required = false) String streetId,
            @RequestParam(required = false) String blockId,
            @RequestParam(required = false) String reportType,
            @RequestParam(required = false) String checkStartMonth,
            @RequestParam(required = false) String checkEndMonth
    ) {
        log.info("query,page={},pageSize={},baseName={},streetId={},blockId={},checkStartMonth={},checkEndMonth={}", pageNo, pageSize, name, streetId, blockId, checkStartMonth, checkEndMonth);
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.hasText(name)) {
            map.put("nameLike", name);
        }
        if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
            map.put("districtId", districtId);
        }
        if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
            map.put("streetId", streetId);
        }
        if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
            map.put("blockId", blockId);
        }
        if (StringUtils.hasText(reportType) && !reportType.equals("0")) {
            map.put("reportType", reportType);
        }
        if (StringUtils.hasText(checkStartMonth)) {
            map.put("checkStartMonth", checkStartMonth);
        }
        if (StringUtils.hasText(checkEndMonth)) {
            map.put("checkEndMonth", checkEndMonth);
        }
        map.put("extraOrderColumns", " pub_time DESC ");
        PageInfo<CheckReportBean> page = service.queryPageBeanByMap(map, pageNo, pageSize);
        Map<String, Object> data = new HashMap<String, Object>();
        User user = (User) session.getAttribute(App.USER_SESSION_KEY);
        if (user != null) {
            data.put("total", page.getTotal());
            data.put("rows", page.getList());
        }
        return data;
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public CheckReport get(@RequestParam(value = "id") Long id) {
        log.info("get,id={}", id);
        return service.get(id);
    }


    @RequestMapping(value = "/insertData")
    @ResponseBody
    public CommonResult create(CheckReport bean, HttpSession session,
                               @RequestParam(required = false) String pubTimeString
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatSdf = new SimpleDateFormat("yyyy-MM-dd");
        String pubTimeForCode = "";
        try {
            if (StringUtils.hasText(pubTimeString)) {
                Date pubTime = sdf.parse(pubTimeString);
                pubTimeForCode = formatSdf.format(pubTime);
                bean.setPubTime(pubTime);
            } else {
                pubTimeString = "";
            }
            String code = "";
            //报告编码根据建筑，报告时间，报告名称来生成
            Long buildingId = bean.getBuildingId();
            if (buildingId != null) {
                Building building = buildingService.get(buildingId);
                if (building != null) {
                    String buildingBaseName = building.getBaseName();
                    if (StringUtils.hasText(bean.getName()) && StringUtils.hasText(buildingBaseName)) {
                        code = ChineseToEnglish.getPinYinHeadUpperChar(buildingBaseName) + "_" +
                                ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()) + "_" + pubTimeForCode;
                        bean.setCode(code);
                        bean.setDistrictId(building.getDistrictId());
                        bean.setStreetId(building.getStreetId());
                    }
                }
            }
            CheckReport beanSql = service.queryBeanByCode(code);
            if (beanSql != null) {
                log.error("新增失败，已存在该记录");
                return CommonResult.fail("新增失败，已存在该记录");
            }
            User user = (User) session.getAttribute(App.USER_SESSION_KEY);
            if (user == null) {
                log.error("新增失败，请登陆");
                return CommonResult.fail("新增失败，请登陆");
            }
            String reportUrl = getReportUrl(user.getUid(),code);
            bean.setModUser(user.getUid());
            bean.setModDate(new Date());
            bean.setReportFileUrl(reportUrl);
            log.info("create,bean={}", bean);
            service.insert(bean);
            return CommonResult.success("新增成功");
        } catch (Exception e) {
            log.error("新增失败", e);
            return CommonResult.fail("新增失败");
        }

    }

    @RequestMapping(value = "/updateData")
    @ResponseBody
    public CommonResult update(CheckReport bean, HttpSession session,
                               @RequestParam(required = false) String pubTimeString
    ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat formatSdf = new SimpleDateFormat("yyyy-MM-dd");
        String pubTimeForCode = "";
        try {
            if (StringUtils.hasText(pubTimeString)) {
                Date pubTime = sdf.parse(pubTimeString);
                pubTimeForCode = formatSdf.format(pubTime);
                bean.setPubTime(pubTime);
            } else {
                pubTimeString = "";
            }
//            String code = "";
//            //报告编码根据建筑，报告时间，报告名称来生成
//            Long buildingId = bean.getBuildingId();
//            if (buildingId != null) {
//                Building building = buildingService.get(buildingId);
//                if (building != null) {
//                    String buildingBaseName = building.getBaseName();
//                    if (StringUtils.hasText(bean.getName()) && StringUtils.hasText(buildingBaseName)) {
//                        code = ChineseToEnglish.getPinYinHeadUpperChar(buildingBaseName) + "_" +
//                                ChineseToEnglish.getPinYinHeadUpperChar(bean.getName()) + "_" + pubTimeForCode;
//                        bean.setCode(code);
//                    }
//                }
//            }
//            CheckReport beanSql = service.queryBeanByCode(code);
//            if (beanSql == null) {
//                log.error("修改失败，该记录不存在");
//                return CommonResult.fail("修改失败，该记录不存在");
//            }
            User user = (User) session.getAttribute(App.USER_SESSION_KEY);
            if (user == null) {
                log.error("修改失败，请登陆");
                return CommonResult.fail("修改失败，请登陆");
            }
            bean.setModUser(user.getUid());
            bean.setModDate(new Date());
            log.info("update,bean={}", bean);
            service.update(bean);
            return CommonResult.success("修改成功");
        } catch (Exception e) {
            log.error("修改失败", e);
            return CommonResult.fail("修改失败");
        }

    }

    @RequestMapping(value = "/deleteData")
    @ResponseBody
    public CommonResult delete(@RequestParam(value = "id") Long id) {
        log.info("delete,id={}", id);
        try {
            service.delete(id);
            return CommonResult.success("删除成功");
        } catch (Exception e) {
            log.error("删除失败", e);
            return CommonResult.fail("删除失败");
        }
    }

    @RequestMapping(value = "/deleteDataByIds", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delDataByIds(String deleteIds, HttpSession session) {
        log.info("delete,deleteIds={}", deleteIds);
        int ok = 0;
        List<String> deleteIdsList = Arrays.asList(deleteIds.split(","));
        if (deleteIdsList == null || deleteIdsList.size() == 0) {
            return CommonResult.fail("批量删除失败");
        }

        User user = (User) session.getAttribute(App.USER_SESSION_KEY);
        if (user == null) {
            log.error("批量删除失败，请登陆");
            return CommonResult.fail("批量删除失败，请登陆");
        }
        for (String id : deleteIdsList) {
            int res = service.delete(Long.parseLong(id));
            if (res > 0) {
                ok++;
            }
        }
        return CommonResult.success("批量删除成功，删除记录：" + ok + "条");
    }



    /**
     * 生成二维码
     *
     * @return
     */
    @RequestMapping(value = "/createQRCode")
    public void createQRCodeNew(
            HttpServletResponse response,
            HttpServletRequest request,@RequestParam(value = "id", required = false) String id) {
        CheckReportInfo checkReportInfo = checkReportInfoService.get(Long.parseLong(id));
        if (checkReportInfo == null){
            log.error("生成二维码失败");
            return;
        }
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
            Date date = checkReportInfo.getDetectionTime();
            String time = "";
            if (date !=null){
                time=sdf.format(date);
            }
            String path = QRCodeWParam
                    .generateQRCode(FileUpload.PUNISH_REPORT_QRCODE_FILE_PATH, tempContextUrl+"fire/view/check/front/platform.jsp?id="+
                            id+"&code="+ URLEncoder.encode(checkReportInfo.getCode() == null?"test":checkReportInfo.getCode(),"utf-8")+"&name="+URLEncoder.encode(checkReportInfo.getName() == null ?"test":checkReportInfo.getName(),"utf-8")+"&detectionTime="+URLEncoder.encode(time,"utf-8"),
                            id);
            File img = new File(path);
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "gif", response.getOutputStream());
        } catch (IOException e1) {
            log.error("生成二维码失败:message:{}", e1.getMessage());
            e1.printStackTrace();
        } catch (WriterException e1) {
            log.error("生成二维码失败:message:{}", e1.getMessage());
            e1.printStackTrace();
        }

    }

    /**
     * 导入数据
     *
     * @param file
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/importStreet", method = RequestMethod.POST)
    @ResponseBody
    CommonResult batchImportStreet(@RequestParam(value = "file") MultipartFile file, HttpSession session) throws IOException {

		// 获取当前登录用户
		 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		 if (user == null) {
			 log.error("导入失败，请登陆"); 
		      return CommonResult.fail("导入失败，请登陆");
		}
		//如果是2007版本以上的文件HSSF无法解析
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!"xls".equals(extension)) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		//转换文档
		List<List<String>> dataList = ExcelUtil.excelList(file.getInputStream());
		if (dataList == null || dataList.isEmpty() || dataList.size() == 2) {
			return CommonResult.fail("Excel文件内容为空");
		}
		//检查格式
//		建筑名称	场所名称	报告名称	检测类型(初检0，复检1)	报告日期	报告人	报告人电话	是否通过	不通过项	风险指数	说明	报告封面	报告文件地址																		
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2016_10_28_checkReport")){
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		if (!title.get(0).startsWith("注意事项:")) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}
		// 删除第一行表头说明
		dataList.remove(0);
		// 删除第二行
	 	dataList.remove(0);
	 	
		//检查重复的名称
		List<String> errorList = new ArrayList<>();
		Set<String> nameSet = new TreeSet<>();
		int countInsert = 0;
		int countUpdate = 0;
		Long begin = System.currentTimeMillis();
		for(List<String> cellValue  : dataList){
			Boolean isNew = false;
			// 冗余数据，通过建筑查询
//			Long districtId = districtService.queryBeanByName(cellValue.get(0).trim());
//			Long streetId = streetService.queryBeanByName(cellValue.get(1).trim());
//			Long blockId = blockService.queryBeanByName(cellValue.get(2).trim());
			Long buildingId = buildingService.queryBeanByName(cellValue.get(0).trim());
			// 场所名称
 			String placeName = cellValue.get(1).trim();
			// 名称
			String name = cellValue.get(2).trim();
			// 编码
			String code = "";
			
			if (buildingId != null) {
				  Building building = buildingService.get(buildingId);
	              String buildingBaseName = building.getBaseName();
	              if (StringUtils.hasText(name) && StringUtils.hasText(buildingBaseName)) {
	                  code = ChineseToEnglish.getPinYinHeadUpperChar(buildingBaseName) + "_" +
	                          ChineseToEnglish.getPinYinHeadUpperChar(name) + "_" + cellValue.get(4).trim();
	              }
				if (StringUtils.hasText(name)) {
					if (!nameSet.add(name)) {
						errorList.add("出现重复的报告名称[" + name + "]，请检查所有报告名称不重复再上传Excel");
					}
				} else {
					errorList.add("报告名称不能为空，请确保Excel内容格式正确再上传");
				}
				
				//提示错误信息
				if (errorList.size() > 0) {
					StringBuilder sb = new StringBuilder();
					for (String s : errorList) {
						sb.append(s + "<br>");
					}
					return CommonResult.fail(sb.toString());
				}
				// 根据所选择的建筑获取区域信息
				Long districtId = building.getDistrictId();
				Long streetId = building.getStreetId();
				Long blockId = building.getBlockId();
				CheckReport checkReport = service.queryBeanByCode(code);
				CheckReport bean;
				if(checkReport == null){
					bean = new CheckReport();
					isNew = true;
				}else{
					bean = checkReport;
				}
	            bean.setReportImage(cellValue.get(6).trim());
	            // 检测类型
	            String typeName = cellValue.get(3).trim();
				String typeNameCode = dictService.getDicCodeByName(typeName,"report_type");
				if (typeNameCode == null) {
					log.error("导入失败，检测类型不存在，请联系系统管理员："+typeName); 
					return CommonResult.fail("导入失败，检测类型不存在，请联系系统管理员："+typeName);
				}
				bean.setReportType(typeNameCode);
	            // 报告日期
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	            Date pubTime;
	            try {
	                pubTime = sdf.parse(cellValue.get(4).trim());
	                bean.setPubTime(pubTime);
	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	            // 报告人？
//	            bean.setReporter(user.getUid());
	            bean.setReporterPhone(cellValue.get(5).trim());
//	            String isPassName = cellValue.get(6).trim();
	            // 1：通过  0：不通过
//	            if (StringUtils.hasText(isPassName)) {
//	            	if ("通过".equals(isPassName)) {
//	            		 bean.setIsPass(1);
//					}else if("不通过".equals(isPassName)){
//						 bean.setIsPass(0);
//					}else{
//						  bean.setIsPass(-1);
//					}
//	            }else{
//	            	log.error("导入失败，检测报告是否通过必填："+isPassName); 
//					return CommonResult.fail("导入失败，检测报告是否通过必填："+isPassName);
//	            }
//	            if (StringUtils.hasText(cellValue.get(7).trim())) {
//	                bean.setUnpassNum(Integer.parseInt(cellValue.get(7).trim()));
//	            }
//				//风险指数 report_risk_index
//				String riskIndexName = cellValue.get(8).trim();
//				String riskIndexCode = dictService.getDicCodeByName(riskIndexName,"report_risk_index");
//				if (riskIndexCode == null) {
//					log.error("导入失败，风险指数不存在，请联系系统管理员："+riskIndexName); 
//					return CommonResult.fail("导入失败，风险指数不存在，请联系系统管理员："+riskIndexName);
//				}
//	            bean.setRiskIndex(riskIndexCode);
	            bean.setCode(code);
	            bean.setBuildingId(buildingId);
	            bean.setName(name);
	            bean.setRemark(cellValue.get(6).trim());
//	            bean.setReportImage(cellValue.get(10).trim());
	            String reportUrl = getReportUrl(user.getUid(),code);
	            bean.setReportFileUrl(reportUrl);
	            bean.setPlaceName(placeName);
	            bean.setDistrictId(districtId);
	            bean.setStreetId(streetId);
	            bean.setBlockId(blockId);
	            bean.setModDate(new Date());
	            bean.setModUser(user.getUid());
				if (isNew) {
					service.insert(bean);
					countInsert++;
				}else {
					service.update(bean);
					countUpdate++;
					log.info("[IMPORT_CHECK_REPORT]插入数据，更新building，id="+bean.getId()+"，code="+bean.getCode());
				}
			}else{
				  log.error("导入失败，建筑不存在，执法名称："+name); 
	  		      return CommonResult.fail("导入失败，建筑不存在，执法名称："+name);
			}
		}
		Long end = System.currentTimeMillis();
		log.info("[IMPORT_CHECK_REPORT]插入数据消耗时间costTime："+(end-begin)+"，插入记录："+countInsert+" 条"+"，更新记录："+countUpdate+" 条");
		if (countInsert >0 || countUpdate >0) {
			return CommonResult.success("导入成功"+"，插入记录："+countInsert+" 条"+"，更新记录："+countUpdate+" 条");
		}else{
			return CommonResult.fail("Excel文件内容为空");
		}
    }


    @RequestMapping(value = "/exportLibStreet", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> exportLibCourse(
            HttpServletRequest request,
            HttpSession session) {
        HSSFWorkbook wb;
        String fName;
        try {
            wb = new HSSFWorkbook();  // 创建一个Excel文件
            Long begin = System.currentTimeMillis();
            Map<String, Object> map = new HashMap<String, Object>();
            // get方式，前端需要编码中文，java后台解码
            String name = request.getParameter("name");
            if (StringUtils.hasText(name)) {
                map.put("nameLike", name);
            }
            String streetId = request.getParameter("streetId");
            String blockId = request.getParameter("blockId");
            String districtId = request.getParameter("districtId");
            String reportType = request.getParameter("reportType");
            String checkStartMonth = request.getParameter("checkStartMonth");
            String checkEndMonth = request.getParameter("checkEndMonth");
            if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
                map.put("districtId", districtId);
            }
            if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
                map.put("streetId", streetId);
            }
            if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
                map.put("blockId", blockId);
            }
            if (StringUtils.hasText(reportType) && !reportType.equals("0")) {
                map.put("reportType", reportType);
            }
            if (StringUtils.hasText(checkStartMonth)) {
                map.put("checkStartMonth", checkStartMonth);
            }
            if (StringUtils.hasText(checkEndMonth)) {
                map.put("checkEndMonth", checkEndMonth);
            }
            log.info("export,name={},streetId={},blockId={},checkStartMonth={},checkEndMonth={}",name, streetId, blockId, checkStartMonth, checkEndMonth);
            List<CheckReportBean> xcbs = service.queryBeanByMap(map);
            xlsLibCheckReportHandler.createSheet(wb, "检测报告清单", xcbs);

            Long end = System.currentTimeMillis();
            log.info("查询生成表格消耗时间costTime：" + (end - begin));
            fName = "检测报告清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());

        } catch (Exception e) {
            wb = new HSSFWorkbook();
            String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
            log.info("导出清单失败,time:{}", date, e);
            XlsLibStreetHandler.createExceptionSheet(wb, e.toString());
            fName = "导出清单失败,请联系管理员" + date;
        }

        return ExportExcelUtils.createDownloadExcel(fName, wb, request);
    }

    /**
     * 替换掉在windows上不能用来建立文件的字符
     * @param uid
     * @param code
     * @return report/管理员uid/处理后的code/处理后的code.html
     */
    private String getReportUrl(Long uid, String code) {
        //  \/:*?"<>|（）
        code = code.replaceAll("\\\\|/|:|\\*|\\?|\"|<|>|\\||（|）","-");
        return "resources/reportFile/" + uid + "/" + code + "/" + code + ".html";
    }
}
