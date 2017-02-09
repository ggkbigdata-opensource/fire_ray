package org.fire.platform.modules.building.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.bean.BuildingBean;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.service.IBuildingService;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.service.IFireEventService;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.statis.handler.XlsLibBuildingHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.ChineseToEnglish;
import org.fire.platform.util.DateUtil;
import org.fire.platform.util.ExcelUtil;
import org.fire.platform.util.ExportExcelUtils;
import org.fire.platform.util.FileUpload;
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
 * @author Administrator
 * @date 2016-9-18 18:46:44
 */

@Controller
@RequestMapping("/building")
public class BuildingController{

	@Autowired
	private IBuildingService service;
	
	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private IBlockService blockService;
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private IFireEventService fireEventService;
	
	@Autowired
	private IPunishEventService punishEventService;
	
	@Autowired
	private ICheckReportService checkReportService;
	
	@Autowired
	private XlsLibBuildingHandler xlsLibBuildingHandler;
	
	@Autowired
	private IDictService dictService;
	
	private static Logger log = LoggerFactory.getLogger(BuildingController.class);

    @RequestMapping(value = "/upload")  
	@ResponseBody
    public CommonResult upload(@RequestParam("thumb_img") MultipartFile file, 
    		HttpServletRequest request, HttpServletResponse response) throws IOException {  
    	
    	String fileSaveName = "";
    	if (file != null) {
	   		 BufferedImage sourceImg =ImageIO.read(file.getInputStream());
			 Long fileSize = file.getSize();
			 int fileWidth = sourceImg.getWidth();
			 int fileHeight = sourceImg.getHeight();
			 log.debug("上传图片的大小为:{} B,宽度为{},高度为{}", fileSize,fileWidth,fileHeight);
    		 fileSaveName = FileUpload.uploadFile(file, request);  
		}
        log.info("fileSaveName:" + fileSaveName); 
        return  CommonResult.success(fileSaveName);
    }
	
    @RequestMapping(value = "/download")  
    public void download(String fileName, HttpServletResponse response) throws IOException {  
        OutputStream os = response.getOutputStream();  
        try {  
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            response.setContentType("image/jpeg; charset=utf-8");  
            os.write(FileUtils.readFileToByteArray(FileUpload.getFile(fileName)));  
            os.flush();  
        } finally {  
            if (os != null) {  
                os.close();  
            }  
        }  
    } 
	
    @RequestMapping(value = "/image/get")
    public void getImage(HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(required = false) String fileSaveName
    		) {
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = FileUpload.getFile(fileSaveName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            if (fis != null) {
	                try {
	                   fis.close();
	                } catch (IOException e) {
	    	        e.printStackTrace();
	                }   
              }
        }
    }
    
	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String baseName,
			@RequestParam(required = false) String baseCode,
			@RequestParam(required = false) String baseBuildingType,
			@RequestParam(required = false) String baseBuildingClass,
			@RequestParam(required = false) String baseLevel,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId
		){
	    log.info("query,page={},pageSize={},baseName={},baseBuildingClass={},baseLevel={},"
	    		+ "streetId={},blockId={}",pageNo,pageSize,baseName,baseBuildingClass,baseLevel,streetId,blockId);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(baseName)) {
			map.put("baseNameLike", baseName);
		}
		if (StringUtils.hasText(baseCode)) {
			map.put("baseCode", baseCode);
		}
		if (StringUtils.hasText(streetId)  && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(districtId)  && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
			map.put("blockId", blockId);
		}
		if (StringUtils.hasText(baseBuildingType) && !baseBuildingType.equals("0")) {
			map.put("baseBuildingType", baseBuildingType);
		}
		if (StringUtils.hasText(baseBuildingClass) && !baseBuildingClass.equals("0")) {
			map.put("baseBuildingClass", baseBuildingClass);
		}
		if (StringUtils.hasText(baseLevel) && !baseLevel.equals("0")) {
			map.put("baseLevel", baseLevel);
		}
		map.put("extraOrderColumns", " finish_time DESC ");
	    PageInfo<BuildingBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
	  	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	   		data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}
	
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(
			HttpSession session,
			@RequestParam(required = false) String baseName,
			@RequestParam(required = false) String baseCode,
			@RequestParam(required = false) String baseBuildingType,
			@RequestParam(required = false) String baseBuildingClass,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId
		){
	    log.info("query,baseName={},baseBuildingType={},"
	    		+ "streetId={},blockId={}",baseName,baseBuildingType,streetId,blockId);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(baseName)) {
			map.put("baseNameLike", baseName);
		}
		if (StringUtils.hasText(baseCode)) {
			map.put("baseCode", baseCode);
		}
		if (StringUtils.hasText(streetId)  && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(districtId)  && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
			map.put("blockId", blockId);
		}
		if (StringUtils.hasText(baseBuildingType) && !baseBuildingType.equals("0")) {
			map.put("baseBuildingType", baseBuildingType);
		}
		if (StringUtils.hasText(baseBuildingClass) && !baseBuildingClass.equals("0")) {
			map.put("baseBuildingClass", baseBuildingClass);
		}
	    List<Building> list =  service.queryByMap(map);
		map.put("extraOrderColumns", " finish_time DESC ");
	    Map<String, Object> data = new HashMap<String, Object>();
	  	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	  		data.put("total",list.size());
			data.put("rows", list);
		}
		return data;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Building get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(Building bean,HttpSession session,
			 @RequestParam(required = false) String finishTimeString,
			 @RequestParam(required = false) String supEstablishDateString,
			 @RequestParam(required = false) String legalEstablishDateString
			 ){
 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
 		try {
 			if (StringUtils.hasText(finishTimeString)) {
 				Date finishTime = sdf.parse(finishTimeString);
 				bean.setFinishTime(finishTime);
			}
// 		if (StringUtils.hasText(supEstablishDateString)) {
// 				Date supEstablishDate = sdf.parse(supEstablishDateString);
// 				bean.setSupEstablishDate(supEstablishDate);
//			}
//			if (StringUtils.hasText(legalEstablishDateString)) {
//				Date legalEstablishDate = sdf.parse(legalEstablishDateString);
//				bean.setLegalEstablishDate(legalEstablishDate);
//			}
 			String thumbImg = bean.getThumbImg();
 			if (StringUtils.hasText(thumbImg)) {
				bean.setThumbImg(FileUpload.FILE_PATH+"/"+thumbImg);
			}
		} catch (ParseException e1) {
		    log.error("新增失败",e1); 
		    return CommonResult.fail("新增失败");
		}
 		 log.info("create,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("新增失败，请登陆"); 
	  		      return CommonResult.fail("新增失败，请登陆");
			}
	    	//建筑的编码规则通过街道以及建筑拼音生成
//	    	 Long streetId= bean.getStreetId();
//	    	 if (streetId != null) {
//				Street street = streetService.get(streetId);
//				if (street != null) {
//					String streetCode = street.getCode();
//					if (StringUtils.hasText(streetCode) && StringUtils.hasText(bean.getBaseName())) {
//						bean.setBaseCode(streetCode + "_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getBaseName()));
//					}
//				}
//			}
	    	 Long blockId = bean.getBlockId();
	    	 if (blockId != null) {
	    		 Block block =blockService.get(blockId);
    			String blockCode = block.getCode();
				String code = "";
				if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getBaseName())) {
					code = blockCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getBaseName());
					bean.setBaseCode(code);
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
			}
	    	bean.setState(1);
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(Building bean,HttpSession session,
			 @RequestParam(required = false) String finishTimeString){
	     log.info("update,bean={}",bean);
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	 			
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("修改失败，请登陆"); 
	  		      return CommonResult.fail("修改失败，请登陆");
			}
	    	 if (StringUtils.hasText(finishTimeString)) {
	 				Date finishTime = sdf.parse(finishTimeString);
	 			bean.setFinishTime(finishTime);
			 }
	    		//建筑的编码规则通过街道以及建筑拼音生成
//	    	 Long streetId= bean.getStreetId();
//	    	 if (streetId != null) {
//				Street street = streetService.get(streetId);
//				if (street != null) {
//					String streetCode = street.getCode();
//					if (StringUtils.hasText(streetCode) && StringUtils.hasText(bean.getBaseName())) {
//						bean.setBaseCode(streetCode + "_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getBaseName()));
//					}
//				}
//			}
 			String thumbImg = bean.getThumbImg();
 			if (StringUtils.hasText(thumbImg)) {
				bean.setThumbImg(FileUpload.FILE_PATH+"/"+thumbImg);
			}
	    	 Long blockId = bean.getBlockId();
	    	 if (blockId != null) {
	    		 Block block =blockService.get(blockId);
    			String blockCode = block.getCode();
				String code = "";
				if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getBaseName())) {
					code = blockCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getBaseName());
					bean.setBaseCode(code);
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
			}
	    	bean.setState(1);
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("修改失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id,HttpSession session){
	     log.info("delete,id={}",id);
	     try{
	    	 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("删除失败，请登陆"); 
	  		      return CommonResult.fail("删除失败，请登陆");
			}
		    service.delete(id);
		    return CommonResult.success("删除成功");
		 }catch(Exception e){
		    log.error("删除失败",e); 
		    return CommonResult.fail("删除失败");
		 }
	 }
	 
	@RequestMapping(value = "/deleteDataByIds", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult delDataByIds(String deleteIds,HttpSession session) {
		log.info("delete,deleteIds={}",deleteIds);
		int ok = 0;
		int no = 0;
		List<String> deleteIdsList = Arrays.asList(deleteIds.split(",")); 
		if(deleteIdsList==null || deleteIdsList.size()==0){
			 return CommonResult.fail("批量删除失败");
		}
		
		 User user = (User)session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
   		  log.error("批量删除失败，请登陆"); 
 		      return CommonResult.fail("批量删除失败，请登陆");
		}
		for(String id: deleteIdsList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("buildingId", id);
			// 火情
			List<FireEvent> fireLists = fireEventService.queryByMap(map);
			// 执法
			List<PunishEvent> punishLists = punishEventService.queryByMap(map);
			// 报告
			List<CheckReport> reportsList = checkReportService.queryByMap(map);
			if ((fireLists != null && fireLists.size() >0) || (punishLists != null && punishLists.size() >0) || (reportsList != null && reportsList.size() >0)) {
				  log.error("建筑主体批量删除失败，关联有火情/执法/报告数据，请先去掉关联再删除！"); 
				  no++;
			}else{
				int res =  service.delete(Long.parseLong(id));
				if(res>0){
					ok++;
				}
			}
		}
		  return CommonResult.success("删除成功记录："+ok+"条"+"，删除失败记录："+no+" 条，请检查火情/执法/报告关联数据！");
	}

	
	
	/**
	 * 导入数据
	 * @param file
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/importStreet" , method = RequestMethod.POST)
	@ResponseBody CommonResult batchImportStreet(@RequestParam(value = "file") MultipartFile file,HttpSession session) throws IOException{
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
//		行政区名称	街道名称	社区名称	建筑名称	建筑类别(下拉框进行选择)	级别（默认高级别）	工程类别（默认办公区）	地址	经度	纬度	竣工时间（例：2014-10-10）	消防责任人	消防责任人电话	消防联系人	消防联系电话	建筑面积(输入数字)	建筑高度(输入整数)	占地面积(输入数字)	地表层数(输入整数)	地下层数(输入整数)	说明
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2016_10_11_building")){
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
			Long districtId = districtService.queryBeanByName(cellValue.get(0).trim());
			Long streetId = streetService.queryBeanByName(cellValue.get(1).trim());
			Long blockId = blockService.queryBeanByName(cellValue.get(2).trim());
			// 名称
			String name = cellValue.get(3).trim();
			// 行政区
			if(districtId != null){
				// 街道
				if (streetId != null) {
					// 社区
					if(blockId != null){
						Block block = blockService.get(blockId);
						String blockCode = block.getCode();
						String code = "";
						if (StringUtils.hasText(blockCode) && StringUtils.hasText(name)) {
							code = blockCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(name);
						}
						if (StringUtils.hasText(name)) {
							if (!nameSet.add(name)) {
								errorList.add("出现重复的建筑名称[" + name + "]，请检查所有建筑名称不重复再上传Excel");
							}
						} else {
							errorList.add("社区名称不能为空，请确保Excel内容格式正确再上传");
						}
						
						//提示错误信息
						if (errorList.size() > 0) {
							StringBuilder sb = new StringBuilder();
							for (String s : errorList) {
								sb.append(s + "<br>");
							}
							return CommonResult.fail(sb.toString());
						}
						// 建筑类别 building_class
						String baseBuildingClassName = cellValue.get(4).trim();
						String baseBuildingClassCode = dictService.getDicCodeByName(baseBuildingClassName,"building_class");
						if (baseBuildingClassCode == null) {
							log.error("导入失败，建筑类别不存在："+baseBuildingClassName); 
							return CommonResult.fail("导入失败，建筑类别不存在："+baseBuildingClassName+"，请联系系统管理员");
						}
						
						// 级别 building_level  默认是？
						String levelCode = "";
						String levelName =  cellValue.get(5).trim();
						if (StringUtils.hasText(levelName)) {
							levelCode = dictService.getDicCodeByName(levelName,"building_level");
						}
						if (levelCode == null) {
							log.error("导入失败，建筑级别不存在："+levelName); 
							return CommonResult.fail("导入失败，建筑级别不存在："+levelName+"，请联系系统管理员");
						}
						
						// 工程类别 building_con_type  默认是？
						String conTypeCode = "";
						String conTypeName =  cellValue.get(6).trim();
						if (StringUtils.hasText(conTypeName)) {
							conTypeCode = dictService.getDicCodeByName(conTypeName,"building_con_type");
						}
						
						if (conTypeCode == null) {
							log.error("导入失败，工程类别不存在："+conTypeName); 
							return CommonResult.fail("导入失败，工程类别不存在："+conTypeName+"，请联系系统管理员");
						}
						
						Boolean isNew = false;
						Long id = service.queryBeanByBaseCode(code);
						Building bean;
						if(id == null){
							bean = new Building();
							isNew = true;
						}else{
							Building beanSql = service.get(id);
							bean = beanSql;
						}
						bean.setDistrictId(districtId);
						bean.setStreetId(streetId);
						bean.setBlockId(blockId);
						bean.setBaseName(name);
						bean.setBaseCode(code);
						bean.setBaseBuildingClass(baseBuildingClassCode);
						bean.setBaseLevel(levelCode);
						bean.setConType(conTypeCode);
						bean.setBaseAddress(cellValue.get(7).trim());
						// 验证输入的经度纬度是数字
						String reg ="\\d+\\.{0,1}\\d*";
						String longitude = cellValue.get(8);
						if(StringUtils.hasText(longitude)){
							if (longitude.matches(reg)) {
								bean.setLongitude(Double.parseDouble(longitude));
							}else{
								log.error("导入失败，经度只能输入数字，请重新输入："+longitude); 
								return CommonResult.fail("导入失败，经度只能输入数字，请重新输入："+longitude);
							}
						}
						String latitude = cellValue.get(9);
						if(StringUtils.hasText(latitude)){
							if (latitude.matches(reg)) {
								bean.setLatitude(Double.parseDouble(latitude));
							}else{
								log.error("导入失败，纬度只能输入数字，请重新输入："+latitude); 
								return CommonResult.fail("导入失败，纬度只能输入数字，请重新输入："+latitude);
							}
						}
						
						// 2016/1/1 11:58:00
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						String finishTimeString = cellValue.get(10).trim();
						Date finishTime;
						if (StringUtils.hasText(finishTimeString)) {
							try {
								finishTime = sdf.parse(finishTimeString);
								bean.setFinishTime(finishTime);
							} catch (ParseException e) {
								log.error("导入失败，时间格式填写错误："+finishTimeString); 
								return CommonResult.fail("导入失败，时间格式填写错误："+finishTimeString);
							}
						}
						bean.setFireManager(cellValue.get(11).trim());
						String fireManagerPhoneString = cellValue.get(12).trim();
						if(StringUtils.hasText(fireManagerPhoneString)){
							boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(fireManagerPhoneString);
							if (isNumber && fireManagerPhoneString.length() <=11) {
								bean.setFireManagerPhone(fireManagerPhoneString);
							}else{
								log.error("导入失败，消防责任人电话填写错误："+fireManagerPhoneString); 
								return CommonResult.fail("导入失败，消防责任人电话填写错误："+fireManagerPhoneString);
							}
						}
						bean.setFireContact(cellValue.get(13).trim());
						bean.setFireContactPhone(cellValue.get(14).trim());
						String fireContactPhoneString = cellValue.get(14).trim();
						if(StringUtils.hasText(fireContactPhoneString)){
							boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(fireContactPhoneString);
							if (isNumber && fireContactPhoneString.length() <=11) {
								bean.setFireContactPhone(fireContactPhoneString);
							}else{
								log.error("导入失败，消防联系电话填写错误："+fireContactPhoneString); 
								return CommonResult.fail("导入失败，消防联系电话填写错误："+fireContactPhoneString);
							}
						}
						// 建筑面积
						if(StringUtils.hasText(cellValue.get(15).trim())){
							bean.setConBuildArea(Double.parseDouble(cellValue.get(15).trim()));
						}
						if (StringUtils.hasText(cellValue.get(16).trim())) {
							bean.setConBuildHight(Integer.parseInt(cellValue.get(16).trim()));
						}
						// 占地面积
						if(StringUtils.hasText(cellValue.get(17).trim())){
							bean.setConCoverArea(Double.parseDouble(cellValue.get(17).trim()));
						}
						if (StringUtils.hasText(cellValue.get(18).trim())) {
							bean.setConFloors(Integer.parseInt(cellValue.get(18).trim()));
						}
						if (StringUtils.hasText(cellValue.get(19).trim())) {
							bean.setConFloors(Integer.parseInt(cellValue.get(19).trim()));
						}
						if (StringUtils.hasText(cellValue.get(20).trim())) {
							bean.setConUnderFloors(Integer.parseInt(cellValue.get(20).trim()));
						}
						if (isNew) {
							service.insert(bean);
							countInsert++;
						}else {
							service.update(bean);
							countUpdate++;
							log.info("[IMPORT_STREET]插入数据，更新building，id="+bean.getId()+"，baseCode="+bean.getBaseCode());
						}
					}else {
						  log.error("导入失败，社区不存在，建筑名称："+name);
			  		      return CommonResult.fail("导入失败，社区不存在，建筑名称："+name);
					}
				}else {
					  log.error("导入失败，街道不存在，社区名称："+name);
		  		      return CommonResult.fail("导入失败，街道不存在，建筑名称："+name);
				}
			}else {
				  log.error("导入失败，行政区不存在，建筑名称："+name); 
	  		      return CommonResult.fail("导入失败，行政区不存在，建筑名称："+name);
			}
		}
		
		Long end = System.currentTimeMillis();
		log.info("[IMPORT_STREET]插入数据消耗时间costTime："+(end-begin)+"，插入记录："+countInsert+" 条"+"，更新记录："+countUpdate+" 条");
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
			String baseName = request.getParameter("baseName");
			if (StringUtils.hasText(baseName)) {
				map.put("baseNameLike", baseName);
			}
			String streetId = request.getParameter("streetId");
			if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
				map.put("streetId", streetId);
			}
			String districtId = request.getParameter("districtId");
			if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
				map.put("districtId", districtId);
			}
			String blockId = request.getParameter("blockId");
			if (StringUtils.hasText(blockId)  && !blockId.equals("0")) {
				map.put("blockId", blockId);
			}
			String baseBuildingClass = request.getParameter("baseBuildingClass");
			if (StringUtils.hasText(baseBuildingClass) && !baseBuildingClass.equals("0")) {
				map.put("baseBuildingClass", baseBuildingClass);
			}
			String baseBuildingType = request.getParameter("baseBuildingType");
			if (StringUtils.hasText(baseBuildingType) && !baseBuildingType.equals("0")) {
				map.put("baseBuildingType", baseBuildingType);
			}
			String baseLevel = request.getParameter("baseLevel");
			if (StringUtils.hasText(baseLevel) && !baseLevel.equals("0")) {
				map.put("baseLevel", baseLevel);
			}
			log.info("export ,baseName={},streetd={},districtId={}",baseName,streetId,districtId);
			List<Building> xcbs = service.queryByMap(map);
			xlsLibBuildingHandler.createSheet(wb, "建筑清单", xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime："+(end-begin));
			fName = "建筑清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			
		}catch (Exception e){
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出清单失败,time:{}",date,e);
			XlsLibStreetHandler.createExceptionSheet(wb,e.toString());
			fName = "导出清单失败,请联系管理员"+date;
		}

		return ExportExcelUtils.createDownloadExcel(fName,wb,request);
	}
	
	
	/**
	 * 上传建筑缩略图
	 * @param file
	 * @param fileType
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/buildingThumbImgUpload", method = RequestMethod.POST)
	public @ResponseBody void filesUpload(
			@RequestParam(value = "thumb_img") MultipartFile file, 
			@RequestParam String fileType,
			HttpServletResponse response,
			HttpServletRequest request) {
		try {
			
			response.setCharacterEncoding("utf-8"); 
			response.setContentType("text/html;charset=UTF-8");
			
	        PrintWriter out = response.getWriter(); 
			String callback = request.getParameter("CKEditorFuncNum");  
            
			//首先获取文件类型
			String originalName = file.getOriginalFilename();		//原文件名
			String suffix = originalName.substring(originalName.lastIndexOf(".")+1);
			//获取上传文件的资源类型信息
//			ResourceType resourceTypeFrom = resourceTypeService.getByCodeLike(suffix.toLowerCase());
//			if(resourceTypeFrom == null){
//				out.println("<script type=\"text/javascript\">");  
//	            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
//	                    + ",''," + "'文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）');");  
//	            out.println("</script>");
//	            return;
//			}
			
			 BufferedImage sourceImg =ImageIO.read(file.getInputStream());
			 Long fileSize = file.getSize();
			 int fileWidth = sourceImg.getWidth();
			 int fileHeight = sourceImg.getHeight();
			 log.debug("上传图片的大小为:{} B,宽度为{},高度为{}", fileSize,fileWidth,fileHeight);
			 if((fileSize/1024.0) > 1024){
				 out.println("<script type=\"text/javascript\">");  
		            out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
		                    + ",''," + "'上传图片限制在1M以内');");  
		            out.println("</script>");
		            return;
			 }
			
			String fileName = System.currentTimeMillis() + "."+suffix;
			String fileSaveName = FileUpload.uploadFile(file, request);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("fileSaveName",fileSaveName);
			log.info("上传成功!");
			 // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
	        out.println("<script type=\"text/javascript\">");  
	        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
	                + ",'" + fileSaveName + "','')");  
	        out.println("</script>");  
	        
		} catch (Exception e) {
			log.error("上传失败!", e);
		}
	}
	
	
}
