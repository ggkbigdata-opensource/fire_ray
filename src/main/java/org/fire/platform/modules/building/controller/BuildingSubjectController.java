package org.fire.platform.modules.building.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.Equipment;
import org.fire.platform.modules.area.bean.Office;
import org.fire.platform.modules.area.bean.Vehicle;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.bean.BuildingSubjectBean;
import org.fire.platform.modules.building.domain.*;
import org.fire.platform.modules.building.service.*;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.service.IFireEventService;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.sys.domain.Dict;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.ChineseToEnglish;
import org.fire.platform.util.ExcelUtil;
import org.fire.platform.util.FileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 11:20:25
 */

@Controller
@RequestMapping("/buildingSubject")
public class BuildingSubjectController extends BaseController{

	@Autowired
	private IBuildingSubjectService service;

	@Autowired
	private IFireEventService fireEventService;

	@Autowired
	private IPunishEventService punishEventService;

	@Autowired
	private ICheckReportService checkReportService;

	@Autowired
	private IManagementService managementService;

	@Autowired
	private IKeypartService keypartService;

	@Autowired
	private IFireSystemService fireSystemService;

	@Autowired
	private IBuildingFunctionService buildingFunctionService;

	@Autowired
	private IStreetService streetService;

	@Autowired
	private IBlockService blockService;

	@Autowired
	private IDistrictService districtService;

	@Autowired
	private IDictService dictService;

	private static Logger log = LoggerFactory.getLogger(BuildingSubjectController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String ownerUnitName,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String baseBuildingClass
		){
		log.info("BuildingSubjectController -> queryPage params -> pageNo = {}, pageSize = {}, session = {}, ownerUnitName = {}, districtId = {}, streetId = {}, blockId = {}, baseBuildingClass = {} " , pageNo , pageSize , session , ownerUnitName , districtId , streetId , blockId , baseBuildingClass );
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(ownerUnitName)) {
			map.put("ownerUnitName", ownerUnitName);
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
		if (StringUtils.hasText(baseBuildingClass) && !baseBuildingClass.equals("0")) {
			map.put("baseBuildingClass", baseBuildingClass);
		}
		map.put("extraOrderColumns", " create_date DESC ");
	    PageInfo<BuildingSubjectBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
	  	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
			 if (page != null){
				 data.put("total", page.getTotal());
				 data.put("rows", page.getList());
			 }
		}
		return data;
	}

	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(
			HttpSession session,
			@RequestParam(required = false) String ownerUnitName,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String baseBuildingClass
	){
		log.info("BuildingSubjectController -> queryAll params -> session = {}, ownerUnitName = {}, districtId = {}, streetId = {}, blockId = {}, baseBuildingClass = {} " , session , ownerUnitName , districtId , streetId , blockId , baseBuildingClass );
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(ownerUnitName)) {
			map.put("ownerUnitName", ownerUnitName);
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
		if (StringUtils.hasText(baseBuildingClass) && !baseBuildingClass.equals("0")) {
			map.put("baseBuildingClass", baseBuildingClass);
		}
		List<BuildingSubject> list =  service.queryByMap(map);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			if (list != null && list.size()>0){
				data.put("total", list.size());
				data.put("rows", list);
			}
		}
		return data;
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public BuildingSubject get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(BuildingSubject bean,HttpSession session){
	     log.info("create,bean={}",bean);
	     try{
	      	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		  log.error("新增失败，请重新登录"); 
				    return CommonResult.fail("新增失败，请重新登录");
			}
		  	bean.setUserId(user.getUid());
		  	bean.setCreateDate(new Date());
		  	bean.setModDate(new Date());
			 Long blockId = bean.getBlockId();
			 if (blockId != null) {
				 Block block =blockService.get(blockId);
				 String blockCode = block.getCode();
				 String code = "";
				 if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getOwnerUnitName())) {
					 code = blockCode+"_"+ ChineseToEnglish.getPinYinHeadUpperChar(bean.getOwnerUnitName());
					 // 编码
					 bean.setBaseCode(code);
					 bean.setDistrictId(block.getDistrictId());
					 bean.setStreetId(block.getStreetId());
				 }
			 }
		    service.insert(bean);
		    return CommonResult.success("新增成功",bean.getId());
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult update(BuildingSubject bean,HttpSession session){
	     log.info("update,bean={}",bean);
	     try{
	      	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		   log.error("修改失败，请重新登录"); 
				    return CommonResult.fail("修改失败，请重新登录");
			}
			 Long blockId = bean.getBlockId();
			 if (blockId != null) {
				 Block block =blockService.get(blockId);
				 String blockCode = block.getCode();
				 String code = "";
				 if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getOwnerUnitName())) {
					 code = blockCode+"_"+ ChineseToEnglish.getPinYinHeadUpperChar(bean.getOwnerUnitName());
					 // 编码
					 bean.setBaseCode(code);
					 bean.setDistrictId(block.getDistrictId());
					 bean.setStreetId(block.getStreetId());
				 }
			 }
    	  	bean.setUserId(user.getUid());
    	  	bean.setModDate(new Date());
		    service.update(bean);
		    return CommonResult.success("修改成功",bean.getId());
		 }catch(Exception e){
		    log.error("修改失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id,HttpSession session){
	     log.info("delete,id={}",id);
	     try{
	     	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		   log.error("删除失败，请重新登录"); 
				    return CommonResult.fail("删除失败，请重新登录");
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
				// other tab need to delete
				// 建筑物主要使用功能表
				Map<String,Object> functionMap = new HashMap<String,Object>();
				functionMap.put("buildingId",id+"");
				List<BuildingFunction> buildingFunctions = buildingFunctionService.queryByMap(functionMap);
				if (buildingFunctions != null && buildingFunctions.size() > 0 ){
					for (BuildingFunction buildingFunction:buildingFunctions){
						if (buildingFunction != null){
							buildingFunctionService.delete(buildingFunction.getId());
						}
					}
				}

				Management management = managementService.getByBuildingId(Long.parseLong(id));
				// 物业管理单位概况表
				if (management !=null){
					managementService.delete(management.getId());
				}
				// 消防重点部位概况表
				Map<String,Object> keypartMap = new HashMap<String,Object>();
				keypartMap.put("buildingId",id+"");
				List<Keypart> keyparts = keypartService.queryByMap(keypartMap);
				if (keyparts != null && keyparts.size() > 0 ){
					for (Keypart keypart:keyparts){
						if (keypart != null){
							keypartService.delete(keypart.getId());
						}
					}
				}
				// 主要消防系统概况表
				Map<String,Object> fireSystemMap = new HashMap<String,Object>();
				fireSystemMap.put("buildingId",id+"");
				List<FireSystem> fireSystems = fireSystemService.queryByMap(fireSystemMap);
				if (fireSystems != null && fireSystems.size() > 0){
					for (FireSystem fireSystem : fireSystems){
						if (fireSystem != null){
							fireSystemService.delete(fireSystem.getId());
						}
					}
				}


				int res =  service.delete(Long.parseLong(id));
				if(res>0){
					ok++;
				}
			}
		}
		return CommonResult.success("删除成功记录："+ok+"条"+"，删除失败记录："+no+" 条，请检查火情/执法/报告关联数据！");
	}

	@RequestMapping(value = "/saveChanges", method = RequestMethod.POST)
	@ResponseBody
	public CommonResult saveChanges(HttpServletRequest req) {

		//获取编辑数据 这里获取到的是json字符串
		String deleted = req.getParameter("deleted");
		String inserted = req.getParameter("inserted");
		String updated = req.getParameter("updated");

		if (deleted != null) {
			//把json字符串转换成对象
			List<BuildingSubject> listDeleted = JSON.parseArray(deleted, BuildingSubject.class);
			//TODO 下面就可以根据转换后的对象进行相应的操作了
		}

		if (inserted != null) {
			//把json字符串转换成对象
			List<BuildingSubject> listInserted = JSON.parseArray(inserted, BuildingSubject.class);
			for (BuildingSubject bs:listInserted){
				service.insert(bs);
			}
		}

		if(updated != null){
			//把json字符串转换成对象
			List<BuildingSubject> listUpdated = JSON.parseArray(updated, BuildingSubject.class);
			for (BuildingSubject bs:listUpdated){
				service.update(bs);
			}
		}
		return CommonResult.success("操作成功");
	}

	@RequestMapping(value = "/upload")
	@ResponseBody
	public CommonResult upload(@RequestParam("thumb_img") MultipartFile file,
							   HttpServletRequest request, HttpServletResponse response) throws IOException {

		String fileSaveName = "";
		if (file != null) {
			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			Long fileSize = file.getSize();
			int fileWidth = sourceImg.getWidth();
			int fileHeight = sourceImg.getHeight();
			log.debug("上传图片的大小为:{} B,宽度为{},高度为{}", fileSize,fileWidth,fileHeight);
			fileSaveName = FileUpload.uploadFile(file, request);
		}
		log.info("fileSaveName:" + fileSaveName);
		return  CommonResult.success(FileUpload.FILE_PATH+"/"+fileSaveName);
	}

	@RequestMapping(value = "/uploadTemplate")
	@ResponseBody
	public CommonResult uploadTemplate(@RequestParam("file") MultipartFile file,
							   HttpServletRequest request, HttpServletResponse response) throws IOException {
		//如果是2007版本以上的文件HSSF无法解析
		String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
		if (!"xls".equals(extension)) {
			return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
		}

		String fileSaveName = "";
		if (file != null) {
			BufferedImage sourceImg = ImageIO.read(file.getInputStream());
			Long fileSize = file.getSize();
			//允许上传的文件最大大小(1M,单位为byte)
			int maxSize = 1024*1024;
			log.info("uploadTemplate-->fileSize:" + fileSize+"maxSize："+maxSize);
			if (fileSize <= maxSize){
				fileSaveName = FileUpload.uploadBuildingFile(file, request);
			}else{
				return CommonResult.fail("Excel文件大小需不大于1M");
			}
		}
		log.info("fileSaveName:" + fileSaveName);
		return  CommonResult.success("上传成功！");
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
		int countInsert = 0;
		Long begin = System.currentTimeMillis();
		// 数据字典
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("typeCode", "fire_system_name_code");
		List<Dict> fireSystemCodeDict = dictService.queryByMap(param);
		Map<String,Object> dictMap = new HashMap<String,Object>();
		for(Dict dict : fireSystemCodeDict){
			dictMap.put(dict.getName(),dict.getCode());
		}
		//转换文档
		List<List<List<String>>> dataListAllSheet = ExcelUtil.excelList(file.getInputStream(),true);
		for (List<List<String>> dataList : dataListAllSheet) {
			if (dataList == null || dataList.isEmpty() || dataList.size() == 2) {
				return CommonResult.fail("Excel文件内容为空");
			}
			//检查格式
			List<String> title = dataList.get(0);
			// 修改模版的时候记得修改版本号
			if(!title.get(title.size()-1).equals("2017_01_03_building_subject")){
				return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
			}
			if (!title.get(0).startsWith("注意事项:")) {
				return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
			}
			for (int i = 0 ;i < 3;i++){
				dataList.remove(0);
			}
			List<String> buildingSubjectInfo = dataList.get(0);
			Long streetId = streetService.queryBeanByName(buildingSubjectInfo.get(1).trim());
			Long blockId = blockService.queryBeanByName(buildingSubjectInfo.get(2).trim());
			// 产权单位名称
			String name = buildingSubjectInfo.get(3).trim();
			Long sqlId = service.queryBeanByName(name);
			if (sqlId != null) {
				log.error("导入失败，已存在该数据，名称："+name);
				return CommonResult.fail("导入失败，已存在该数据，名称："+name);
			}
			Long districtId = districtService.queryBeanByName(buildingSubjectInfo.get(0).trim());
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
						}else{
							log.error("产权单位名称必填，请重新填写再上传！");
							return CommonResult.fail("产权单位名称必填，请重新填写再上传！");
						}
						// 消防安全负责人
						String fireManager = buildingSubjectInfo.get(4).trim();
						// 联系人姓名
						String contactName = buildingSubjectInfo.get(5).trim();
						// 联系人电话校验
						String contactPhoneString =  buildingSubjectInfo.get(6).trim();
						String contactPhone = "";
						if(StringUtils.hasText(contactPhoneString)){
							boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(contactPhoneString);
							if (isNumber && contactPhoneString.length() <=11) {
								contactPhone = contactPhoneString;
							}else{
								log.error("导入失败，联系人电话填写错误："+contactPhoneString);
								return CommonResult.fail("导入失败，联系人电话填写错误："+contactPhoneString);
							}
						}
						//上级主管单位
						String supChargeUnitName = buildingSubjectInfo.get(7).trim();
						//行业监管部门
						String industrySupervisionDepart = buildingSubjectInfo.get(8).trim();

						// 经度纬度校验
						String longitude =  buildingSubjectInfo.get(9).trim();
						String latitude =  buildingSubjectInfo.get(10).trim();
						// 验证输入的经度纬度是数字
						String reg ="\\d+\\.{0,1}\\d*";
						if(StringUtils.hasText(longitude)){
							if (!longitude.matches(reg)) {
								log.error("导入失败，经度只能输入数字，请重新输入："+longitude);
								return CommonResult.fail("导入失败，经度只能输入数字，请重新输入："+longitude);
							}
						}
						if(StringUtils.hasText(latitude)){
							if (!latitude.matches(reg)) {
								log.error("导入失败，纬度只能输入数字，请重新输入："+latitude);
								return CommonResult.fail("导入失败，纬度只能输入数字，请重新输入："+latitude);
							}
						}
						// 投入使用时间
						String useTime =  buildingSubjectInfo.get(11).trim();
						// 建筑类别 数据字典
						String baseBuildingClassString = buildingSubjectInfo.get(12).trim();
						String baseBuildingClassCode = dictService.getDicCodeByName(baseBuildingClassString,"building_class");
						if (baseBuildingClassCode == null) {
							log.error("导入失败，建筑类别不存在，请联系系统管理员："+baseBuildingClassString);
							return CommonResult.fail("导入失败，建筑类别不存在，请联系系统管理员："+baseBuildingClassString);
						}
						// 占地面积
						String conCoverAreaString  = buildingSubjectInfo.get(13).trim();
						Double conCoverArea = 0.0;
						if (StringUtils.hasText(conCoverAreaString)) {
							conCoverArea = Double.parseDouble(conCoverAreaString);
						}
						// 建筑面积
						String conBuildAreaString  = buildingSubjectInfo.get(14).trim();
						Double conBuildArea = 0.0;
						if (StringUtils.hasText(conBuildAreaString)) {
							conBuildArea = Double.parseDouble(conBuildAreaString);
						}
						// 建筑高度
						String conBuildHightString  = buildingSubjectInfo.get(15).trim();
						int conBuildHight = 0;
						if (StringUtils.hasText(conBuildHightString)) {
							conBuildHight = Integer.parseInt(conBuildHightString);
						}
						// 地表层数
						String conFloorsString  = buildingSubjectInfo.get(16).trim();
						int conFloors = 0;
						if (StringUtils.hasText(conFloorsString)) {
							conFloors = Integer.parseInt(conFloorsString);
						}
						// 地下层数
						String conUnderFloorsString  = buildingSubjectInfo.get(17).trim();
						int conUnderFloors = 0;
						if (StringUtils.hasText(conUnderFloorsString)) {
							conUnderFloors = Integer.parseInt(conUnderFloorsString);
						}
						// 地表使用功能  数据字典
						String surfaceFunctionString =  buildingSubjectInfo.get(18).trim();
						String surfaceFunctionCode = dictService.getDicCodeByName(surfaceFunctionString,"surface_function");
						if (surfaceFunctionCode == null) {
							log.error("导入失败，地表使用功能不存在，请联系系统管理员："+surfaceFunctionString);
							return CommonResult.fail("导入失败，地表使用功能不存在，请联系系统管理员："+surfaceFunctionString);
						}
						// 地下使用功能  数据字典
						String undergroundFunctionString =  buildingSubjectInfo.get(19).trim();
						String undergroundFunctionCode = dictService.getDicCodeByName(undergroundFunctionString,"underground_function");
						if (undergroundFunctionCode == null) {
							log.error("导入失败，地下使用功能不存在，请联系系统管理员："+undergroundFunctionString);
							return CommonResult.fail("导入失败，地下使用功能不存在，请联系系统管理员："+undergroundFunctionString);
						}
						// 土地使用性质
						String conClassString =  buildingSubjectInfo.get(20).trim();
						String conClassCode = dictService.getDicCodeByName(conClassString,"building_con_class");
						if (conClassCode == null) {
							log.error("导入失败，土地使用性质不存在，请联系系统管理员："+conClassString);
							return CommonResult.fail("导入失败，土地使用性质不存在，请联系系统管理员："+conClassString);
						}
						// 使用单位数量
						String useUnitNumString = buildingSubjectInfo.get(21).trim();
						int useUnitNum = 0;
						if (StringUtils.hasText(useUnitNumString)) {
							useUnitNum = Integer.parseInt(useUnitNumString);
						}
						// 维保单位
						String maintenanceUnit =  buildingSubjectInfo.get(22).trim();
						// 三方消检
						String thirdPartyFireDetection =  buildingSubjectInfo.get(23).trim();
						// 地址
						String address =  buildingSubjectInfo.get(24).trim();
						// 备注
						String remark = buildingSubjectInfo.get(25).trim();

						BuildingSubject bean = new BuildingSubject();
						bean.setDistrictId(districtId);
						bean.setStreetId(streetId);
						bean.setBlockId(blockId);
						bean.setOwnerUnitName(name);
						bean.setFireManager(fireManager);
						bean.setContactName(contactName);
						bean.setContactPhone(contactPhone);
						bean.setSupChargeUnitName(supChargeUnitName);
						bean.setIndustrySupervisionDepart(industrySupervisionDepart);
						bean.setLongitude(Double.parseDouble(longitude));
						bean.setLatitude(Double.parseDouble(latitude));
						bean.setUseTime(useTime);
						bean.setBaseBuildingClass(baseBuildingClassCode);
						bean.setConCoverArea(conCoverArea);
						bean.setConBuildArea(conBuildArea);
						bean.setConBuildHight(conBuildHight);
						bean.setSurfaceFunction(surfaceFunctionCode);
						bean.setUndergroundFunction(undergroundFunctionCode);
						bean.setConClass(conClassCode);
						bean.setUseUnitNum(useUnitNum);
						bean.setMaintenanceUnit(maintenanceUnit);
						bean.setThirdPartyFireDetection(thirdPartyFireDetection);
						bean.setAddress(address);
						bean.setRemark(remark);
						bean.setCreateDate(new Date());
						bean.setUserId(user.getUid());
						bean.setBaseCode(code);
						service.insert(bean);

//						dataList.remove(0);  // 移除第一行的基础信息
//						dataList.remove(0);  // 移除第二行的信息头
//						dataList.remove(0);	 // 移除录入信息提示字段
						for (int i = 0 ;i < 3;i++){
							dataList.remove(0);
						}
						// 物业管理 一行数据
						List<String> buildingManagementInfo = dataList.get(0);
						Management management = new Management();
						management.setBuildingId(bean.getId());
						management.setManagerUnitName(buildingManagementInfo.get(0).trim());
						management.setChargePerson(buildingManagementInfo.get(1).trim());
						management.setContactName(buildingManagementInfo.get(2).trim());
						// 联系人电话校验
						String managementContactPhoneString =  buildingManagementInfo.get(3).trim();
						String managementContactPhone = "";
						if(StringUtils.hasText(managementContactPhoneString)){
							boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(managementContactPhoneString);
							if (isNumber && managementContactPhoneString.length() <=11) {
								managementContactPhone = managementContactPhoneString;
							}else{
								log.error("导入失败，物业管理单位联系人电话填写错误："+managementContactPhoneString);
								return CommonResult.fail("导入失败，物业管理单位联系人电话填写错误："+managementContactPhoneString);
							}
						}
						management.setContactPhone(managementContactPhone);
						management.setSupChargeUnitName(buildingManagementInfo.get(4).trim());
						management.setIndustrySupervisionDepart( buildingManagementInfo.get(5).trim());

						String publishTimeString = buildingManagementInfo.get(6).trim();
						Date publishTime = null;
						if (StringUtils.hasText(publishTimeString)) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							try {
								publishTime = sdf.parse(publishTimeString);
							} catch (ParseException e) {
								log.error("导入失败，物业管理单位成立时间格式填写错误："+publishTimeString);
								return CommonResult.fail("导入失败，物业管理单位成立时间格式填写错误："+publishTimeString);
							}
						}
						management.setPublishTime(publishTime);
						String registeredMoneyString =  buildingManagementInfo.get(7).trim();
						Double registeredMoney = 0.0;
						if (StringUtils.hasText(registeredMoneyString)){
							registeredMoney = Double.parseDouble(registeredMoneyString);
							management.setRegisteredMoney(registeredMoney);
						}
						String employeesNumString = buildingManagementInfo.get(8).trim();
						String fireWitnessNumString = buildingManagementInfo.get(9).trim();
						int employeesNum = 0;
						if (StringUtils.hasText(employeesNumString)) {
							employeesNum = Integer.parseInt(employeesNumString);
							management.setEmployeesNum(employeesNum);
						}
						int fireWitnessNum = 0;
						if (StringUtils.hasText(fireWitnessNumString)) {
							fireWitnessNum = Integer.parseInt(fireWitnessNumString);
							management.setFireWitnessNum(fireWitnessNum);
						}

						management.setManagerAddress(buildingManagementInfo.get(10).trim());
						management.setRemark(buildingManagementInfo.get(11).trim());
						management.setCreateDate(new Date());
						management.setUserId(user.getUid());
						managementService.insert(management);

						// 消防重点部位
						for (int i = 0 ;i < 3;i++){
							dataList.remove(0);
						}

						for (int i = 0; i < 6 ; i++) {
							Keypart keypart = new Keypart();
							keypart.setBuildingId(bean.getId());
							keypart.setManagementId(management.getId());
							keypart.setKeypartName(dataList.get(i).get(0).trim());
							keypart.setPosition(dataList.get(i).get(1).trim());
							String areaString = dataList.get(i).get(2).trim();
							Double area = 0.0;
							if (StringUtils.hasText(areaString)){
								area = Double.parseDouble(areaString);
								keypart.setArea(area);
							}

							keypart.setFireEquipment(dataList.get(i).get(3));
							String dutyNumString = dataList.get(i).get(4).trim();
							int dutyNum = 0;
							if (StringUtils.hasText(dutyNumString)){
								dutyNum = Integer.parseInt(dutyNumString);
								keypart.setDutyNum(dutyNum);
							}

							String diplomaNumString = dataList.get(i).get(5).trim();
							int diplomaNum = 0;
							if (StringUtils.hasText(diplomaNumString)){
								diplomaNum = Integer.parseInt(diplomaNumString);
								keypart.setDiplomaNum(diplomaNum);
							}

							String firePumpNumString = dataList.get(i).get(6).trim();
							int firePumpNum = 0;
							if (StringUtils.hasText(firePumpNumString)){
								firePumpNum = Integer.parseInt(firePumpNumString);
								keypart.setFirePumpNum(firePumpNum);
							}

							String sprayPumpNumString = dataList.get(i).get(7).trim();
							int sprayPumpNum = 0;
							if (StringUtils.hasText(sprayPumpNumString)){
								sprayPumpNum = Integer.parseInt(sprayPumpNumString);
								keypart.setSprayPumpNum(sprayPumpNum);
							}


							String pressurePumpNumString = dataList.get(i).get(8).trim();
							int pressurePumpNum = 0;
							if (StringUtils.hasText(pressurePumpNumString)){
								pressurePumpNum = Integer.parseInt(pressurePumpNumString);
								keypart.setPressurePumpNum(pressurePumpNum);
							}

							String airTankVolumeString = dataList.get(i).get(9).trim();
							Double airTankVolume = 0.0;
							if (StringUtils.hasText(airTankVolumeString)){
								airTankVolume = Double.parseDouble(airTankVolumeString);
								keypart.setAirTankVolume(airTankVolume);
							}

							String storageAreaString = dataList.get(i).get(10).trim();
							Double storageArea = 0.0;
							if (StringUtils.hasText(storageAreaString)){
								storageArea = Double.parseDouble(storageAreaString);
								keypart.setStorageArea(storageArea);
							}
							keypart.setOilVolume(dataList.get(i).get(11).trim());
							keypart.setCreateDate(new Date());
							keypart.setUserId(user.getUid());
							keypartService.insert(keypart);
						}

						for (int i = 0 ;i < 8;i++){
							dataList.remove(0);
						}
						// 主要消防系统概况表
						String mergeValue  = "";
						for (int i = 0; i < 73; i++) {
							FireSystem fireSystem = new FireSystem();
							fireSystem.setBuildingId(bean.getId());
							fireSystem.setManagementId(management.getId());
							String systemName = dataList.get(i).get(0).trim();
							String systemNameCode = "";
							// 有值就是合并的单元格
							if (StringUtils.hasText(systemName)){
								mergeValue = systemName;
							}else{
								systemName = mergeValue;
							}
							systemNameCode = (String)dictMap.get(systemName);
							fireSystem.setSystemName(systemNameCode);
							fireSystem.setSystemConstituentName(dataList.get(i).get(1).trim());
							fireSystem.setModelSize(dataList.get(i).get(2).trim());
							String amountString = dataList.get(i).get(3).trim();
							Integer amount = 0;
							if (StringUtils.hasText(amountString)){
								amount = Integer.parseInt(amountString);
								fireSystem.setAmount(amount);
							}
							fireSystem.setManufacturer(dataList.get(i).get(4).trim());
							fireSystem.setPosition(dataList.get(i).get(5).trim());
							fireSystem.setUseTime(dataList.get(i).get(6).trim());
							fireSystem.setUseSituation(dataList.get(i).get(7).trim());
							String volumeString = dataList.get(i).get(8).trim();
							Double volume = 0.0;
							if (StringUtils.hasText(volumeString)){
								volume = Double.parseDouble(volumeString);
								fireSystem.setVolume(volume);
							}
							fireSystem.setRemark(dataList.get(i).get(9).trim());
							fireSystem.setCreateDate(new Date());
							fireSystem.setUserId(user.getUid());
							fireSystemService.insert(fireSystem);
						}

						for (int i = 0 ;i < 75;i++){
							dataList.remove(0);
						}
						// 建筑物主要功能表
						for (List<String> functionCellValue : dataList){
							BuildingFunction buildingFunction = new BuildingFunction();
							buildingFunction.setBuildingId(bean.getId());
							buildingFunction.setBuildingFloor(functionCellValue.get(0).trim());
							buildingFunction.setFunction(functionCellValue.get(1).trim());
							buildingFunction.setFunBusinessName(functionCellValue.get(3).trim());
							String funBuildAreaString = functionCellValue.get(4).trim();
							Double funBuildArea = 0.0;
							if (StringUtils.hasText(funBuildAreaString)){
								funBuildArea = Double.parseDouble(funBuildAreaString);
								buildingFunction.setFunBuildArea(funBuildArea);
							}
							buildingFunction.setCreateDate(new Date());
							buildingFunction.setUserId(user.getUid());
							buildingFunctionService.insert(buildingFunction);
						}
						countInsert++;
					}else{
						log.error("导入失败，社区不存在，建筑名称："+name);
						return CommonResult.fail("导入失败，社区不存在，建筑名称："+name);
					}
				}else{
					log.error("导入失败，街道不存在，消防站名称："+name);
					return CommonResult.fail("导入失败，街道不存在，消防站名称："+name);
				}
			}else{
				log.error("导入失败，行政区不存在，消防站名称："+name);
				return CommonResult.fail("导入失败，行政区不存在，消防站名称："+name);
			}
		}
		Long end = System.currentTimeMillis();
		log.info("[IMPORT_STREET]插入数据消耗时间costTime："+(end-begin)+"，插入记录："+countInsert+" 条！");
		if (countInsert >0) {
			return CommonResult.success("导入成功"+"，插入记录："+countInsert+" 条 ！");
		}else{
			return CommonResult.fail("Excel文件内容为空");
		}
	}

}
