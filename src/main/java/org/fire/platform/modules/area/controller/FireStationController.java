package org.fire.platform.modules.area.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.Equipment;
import org.fire.platform.modules.area.bean.FireStationBean;
import org.fire.platform.modules.area.bean.Office;
import org.fire.platform.modules.area.bean.Vehicle;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IFireStationService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.statis.handler.XlsLibFireStationHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.ChineseToEnglish;
import org.fire.platform.util.DateUtil;
import org.fire.platform.util.ExcelUtil;
import org.fire.platform.util.ExportExcelUtils;
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

import com.google.gson.Gson;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

@Controller
@RequestMapping("area/fireStation")
public class FireStationController{

	@Autowired
	private IFireStationService service;
	
	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private IBlockService blockService;
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private IDictService dictService;
	
	@Autowired
	private XlsLibFireStationHandler xlsLibFireStationHandler;
	
	private static Logger log = LoggerFactory.getLogger(FireStationController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String code
			){
	    log.info("query,page={},pageSize={},name={},districtId={},streetId={},blockId={}",pageNo,pageSize,name,districtId,streetId,blockId);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(streetId)  && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(districtId)  && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (StringUtils.hasText(blockId)  && !blockId.equals("0")) {
			map.put("blockId", blockId);
		}
		if (StringUtils.hasText(code)) {
			map.put("code", code);
		}
		
	    PageInfo<FireStationBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
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
	public FireStation get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(
			 	FireStation bean,
			 	  String[] officeNames
				, String[] officeBusinesses // 
			 ){
	     log.info("create,bean={}",bean);
	     try{
	     	Long sqlId = service.queryBeanByName(bean.getName());
	    	if (sqlId != null) {
	    		   log.error("新增失败，已存在该数据"); 
	    		   return CommonResult.fail("新增失败，已存在该数据");
			}
	    	 // 职务
	    	 List<Office> officeList = new ArrayList<>();
	    	 if (officeNames != null && officeNames.length>0) {
	    		 for (int i = 0; i < officeNames.length; i++) {
	 				Office office = new Office();
	 				office.setOfficeName(officeNames[i]);
	 				office.setBusiness(officeBusinesses[i]);
	 				officeList.add(office);
	 			}
	 	    	Gson gson = new Gson();
	 	 		String officeJson = gson.toJson(officeList);
	 	 		bean.setOffice(officeJson);
			}
//	 		List<Office> offices = gson.fromJson(officeJson, new TypeToken<List<Office>>() {}.getType());
//	 		System.out.println(offices);
	    	 Long blockId = bean.getBlockId();
	    	 if (blockId != null) {
			 Block block =blockService.get(blockId);
   			 String blockCode = block.getCode();
				String code = "";
				if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getName())) {
					code = blockCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getName());
					bean.setCode(code);
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
			}
	    	bean.setModTime(new Date());
		    service.insert(bean);
		    return CommonResult.success("新增成功",bean.getId());
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
	 }
 	 
 	 @RequestMapping(value = "/insertOfficeData")
	 @ResponseBody
	 public CommonResult createOffice(
			 		@RequestParam(value = "id") Long id,
			 	  String[] officeNames
				, String[] officeBusinesses // 
			 ){
 		 FireStation bean = service.get(id);
 		 if (bean == null) {
 			  log.error("更新人员失败，不存在该实体"); 
 			  return CommonResult.fail("更新人员失败");
		}
	     log.info("create,bean={}",bean);
	     try{
	    	 // 职务
	    	 List<Office> officeList = new ArrayList<>();
	    	 if (officeNames != null && officeNames.length>0) {
	    		 for (int i = 0; i < officeNames.length; i++) {
	 				Office office = new Office();
	 				office.setOfficeName(officeNames[i]);
	 				office.setBusiness(officeBusinesses[i]);
	 				officeList.add(office);
	 			}
	 	    	Gson gson = new Gson();
	 	 		String officeJson = gson.toJson(officeList);
		    	bean.setOffice(officeJson);
		    	bean.setOfficeSum(officeList.size());
			}
	    	bean.setModTime(new Date());
		    service.update(bean);
		    return CommonResult.success("更新人员成功");
		 }catch(Exception e){
		    log.error("更新人员失败",e); 
		    return CommonResult.fail("更新人员失败");
		 }
	 }
 	 
 	 @RequestMapping(value = "/insertVehicleData")
	 @ResponseBody
	 public CommonResult createVehicle(
			 		@RequestParam(value = "id") Long id,
			 		String[] vehicleTypes,
			 		String[] foamLoadinges,
			 		String[] waterAmounts,
			 		String[] amounts
			 ){
 		 FireStation bean = service.get(id);
 		 if (bean == null) {
			  log.error("更新人员失败，不存在该实体"); 
			  return CommonResult.fail("更新人员失败");
		}
	     log.info("create,bean={}",bean);
	     Integer totalAmount = 0;
	     try{
	    	 List<Vehicle> vehicleList = new ArrayList<>();
	    	 if (vehicleTypes != null && vehicleTypes.length>0) {
	    		 for (int i = 0; i < vehicleTypes.length; i++) {
	 				Vehicle vehicle = new Vehicle();
	 				vehicle.setVehicleType(vehicleTypes[i]);
	 				vehicle.setFoamLoadinge(foamLoadinges[i]);
	 				vehicle.setWaterAmount(waterAmounts[i]);
	 				vehicle.setAmount(amounts[i]);
	 				totalAmount+=Integer.parseInt(amounts[i]);
	 				vehicleList.add(vehicle);
	 			}
	 	    	Gson gson = new Gson();
	 	 		String vehicleJson = gson.toJson(vehicleList);
	 	 		bean.setVehicle(vehicleJson);
	 	 		bean.setVehicleSum(totalAmount);
			}
	    	bean.setModTime(new Date());
		    service.update(bean);
		    return CommonResult.success("更新配备车辆信息成功");
		 }catch(Exception e){
		    log.error("更新配备车辆信息失败",e); 
		    return CommonResult.fail("更新配备车辆信息失败");
		 }
	 }
 	 
 	 @RequestMapping(value = "/insertEquipmentData")
	 @ResponseBody
	 public CommonResult createEquipment(
			 		@RequestParam(value = "id") Long id,
			 	  String[] equipmentNames
				, String[] equipmentNums // 
			 ){
 		 FireStation bean = service.get(id);
 		 if (bean == null) {
			  log.error("更新人员失败，不存在该实体"); 
			  return CommonResult.fail("更新人员失败");
		}
 		 Integer totalNum = 0;
	     log.info("create,bean={}",bean);
	     try{
	    	 List<Equipment> equipmentList = new ArrayList<>();
	    	 if (equipmentNames != null && equipmentNames.length>0) {
	    		 for (int i = 0; i < equipmentNames.length; i++) {
	    			 Equipment equipment = new Equipment();
	    			 equipment.setEquipmentName(equipmentNames[i]);
	    			 equipment.setEquipmentNum(equipmentNums[i]);
	    			 totalNum +=Integer.parseInt(equipmentNums[i]);
	 				 equipmentList.add(equipment);
	 			}
	 	    	Gson gson = new Gson();
	 	 		String equipmentJson = gson.toJson(equipmentList);
	 	 		bean.setEquipment(equipmentJson);
	 	 		bean.setEquipmentSum(totalNum);
			}
	    	bean.setModTime(new Date());
		    service.update(bean);
		    return CommonResult.success("更新配备装备信息成功");
		 }catch(Exception e){
		    log.error("更新配备装备信息失败",e); 
		    return CommonResult.fail("更新配备装备信息失败");
		 }
	 }
 	 
 	 
 	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(FireStation bean){
	     log.info("update,bean={}",bean);
	     try{
	    	 Long blockId = bean.getBlockId();
	    	 if (blockId != null) {
	    		 Block block =blockService.get(blockId);
   			String blockCode = block.getCode();
				String code = "";
				if (StringUtils.hasText(blockCode) && StringUtils.hasText(bean.getName())) {
					code = blockCode+"_"+ChineseToEnglish.getPinYinHeadUpperChar(bean.getName());
					bean.setCode(code);
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
			}
	    	bean.setModTime(new Date());
		    service.update(bean);
		    return CommonResult.success("修改成功",bean.getId());
		 }catch(Exception e){
		    log.error("修改失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/deleteData")
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id){
	     log.info("delete,id={}",id);
	     try{
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
				int res =  service.delete(Long.parseLong(id));
				if(res>0){
					ok++;
				}
		}
		  return CommonResult.success("删除成功记录："+ok+"条"+"！");
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
		//转换文档
		List<List<List<String>>> dataListAllSheet = ExcelUtil.excelList(file.getInputStream(),true);
		for (List<List<String>> dataList : dataListAllSheet) {
			if (dataList == null || dataList.isEmpty() || dataList.size() == 2) {
				return CommonResult.fail("Excel文件内容为空");
			}
			//检查格式
			List<String> title = dataList.get(0);
			// 修改模版的时候记得修改版本号
			if(!title.get(title.size()-1).equals("2016_11_10_fire_station")){
				return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
			}
			if (!title.get(0).startsWith("注意事项:")) {
				return CommonResult.fail("Excel导入格式不对，请使用模板格式导入");
			}
			// 删除第一行表头说明
	    	 dataList.remove(0);
			// 删除第二行
		 	dataList.remove(0);
				List<String> fireStationInfo = dataList.get(0);
				Long streetId = streetService.queryBeanByName(fireStationInfo.get(1).trim());
				Long blockId = blockService.queryBeanByName(fireStationInfo.get(2).trim());
				// 名称
				String name = fireStationInfo.get(3).trim();
				Long sqlId = service.queryBeanByName(name);
		    	if (sqlId != null) {
		    		   log.error("导入失败，已存在该数据，名称："+name); 
		    		   return CommonResult.fail("导入失败，已存在该数据，名称："+name);
				}
				Long districtId = districtService.queryBeanByName(fireStationInfo.get(0).trim());
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
								 log.error("消防站名称必填，请重新填写再上传！");
					  		      return CommonResult.fail("消防站名称必填，请重新填写再上传！");
							}
							String remark = fireStationInfo.get(12).trim();
							String committeesDirector =  fireStationInfo.get(4).trim();
							String committeesPhoneString =  fireStationInfo.get(5).trim();
							String committeesPhone = "";
							if(StringUtils.hasText(committeesPhoneString)){
								boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(committeesPhoneString);
								if (isNumber && committeesPhoneString.length() <=11) {
									committeesPhone = committeesPhoneString;
								}else{
									log.error("导入失败，居委会电话填写错误："+committeesPhoneString); 
									return CommonResult.fail("导入失败，居委会电话填写错误："+committeesPhoneString);
								}
							}
							String stationMaster =  fireStationInfo.get(6).trim();
							String stationPhoneString =  fireStationInfo.get(7).trim();
							String stationPhone = "";
							if(StringUtils.hasText(stationPhoneString)){
								boolean isNumber = org.fire.platform.util.StringUtils.isNumeric(stationPhoneString);
								if (isNumber && stationPhoneString.length() <=11) {
									stationPhone = stationPhoneString;
								}else{
									log.error("导入失败，居委会电话填写错误："+stationPhoneString); 
									return CommonResult.fail("导入失败，居委会电话填写错误："+stationPhoneString);
								}
							}
							String areaString  = fireStationInfo.get(8).trim();
							Double area = 0.0;
							if (StringUtils.hasText(areaString)) {
								area = Double.parseDouble(fireStationInfo.get(8).trim());
							}
							
							String emergencyServiceName = fireStationInfo.get(9).trim();
							String emergencyServiceCode = dictService.getDicCodeByName(emergencyServiceName,"emergency_service");
							if (emergencyServiceCode == null) {
								log.error("导入失败，数据字典应急支援服务救援站不存在："+emergencyServiceName); 
								return CommonResult.fail("导入失败，数据字典应急支援服务救援站不存在："+emergencyServiceName+"，请联系系统管理员");
							}
						
							String committeesAddress =  fireStationInfo.get(10).trim();
							String stationAddress =  fireStationInfo.get(11).trim();
							
							
							dataList.remove(0);  // 移除第一行的基础信息
							dataList.remove(0);  // 移除第二行的信息头
							dataList.remove(0);	 // 移除录入信息提示字段
							 List<Office> officeList = new ArrayList<>();
							 List<Vehicle> vehicleList = new ArrayList<>();
							 List<Equipment> equipmentList = new ArrayList<>();
						     Integer totalAmount = 0;
						     Integer totalNum = 0;
						     
							for (List<String> cellValue  : dataList) {
								// 人员姓名
								String officeName = cellValue.get(0).trim();
								// 人员职务
								String officeBusiness = cellValue.get(1).trim();
								if (StringUtils.hasText(officeName) && StringUtils.hasText(officeBusiness) ) {
									Office office = new Office();
									office.setOfficeName(officeName);
									office.setBusiness(officeBusiness);
									officeList.add(office);
								}

								// 车辆类型
								String vehicleType =  cellValue.get(3).trim();
								String foamLoadinge =   cellValue.get(4).trim();
								String waterAmount = cellValue.get(5).trim();
								String amount = cellValue.get(6).trim();
								if(StringUtils.hasText(vehicleType) && StringUtils.hasText(foamLoadinge) && StringUtils.hasText(waterAmount)  && StringUtils.hasText(amount)){
									Vehicle vehicle = new Vehicle();
					 				vehicle.setVehicleType(vehicleType);
					 				vehicle.setFoamLoadinge(foamLoadinge);
					 				vehicle.setWaterAmount(waterAmount);
					 				vehicle.setAmount(amount);
					 				totalAmount+=Integer.parseInt(amount);
					 				vehicleList.add(vehicle);
								}

				 				// 配备装备
				 				String equipmentName =  cellValue.get(8).trim();
								String equipmentNum =   cellValue.get(9).trim();
								if (StringUtils.hasText(equipmentName) && StringUtils.hasText(equipmentNum)) {
									 Equipment equipment = new Equipment();
					    			 equipment.setEquipmentName(equipmentName);
					    			 equipment.setEquipmentNum(equipmentNum);
					    			 totalNum +=Integer.parseInt(equipmentNum);
					 				 equipmentList.add(equipment);
								}
							}
							
							Gson gson = new Gson();
				 	 		String officeJson = gson.toJson(officeList);
							String vehicleJson = gson.toJson(vehicleList);
							String equipmentJson = gson.toJson(equipmentList);
							FireStation bean = new FireStation();
							bean.setDistrictId(districtId);
							bean.setStreetId(streetId);
							bean.setBlockId(blockId);
							bean.setName(name);
							bean.setCommitteesDirector(committeesDirector);
							bean.setCommitteesPhone(committeesPhone);
							bean.setCommitteesAddress(committeesAddress);
							bean.setStationMaster(stationMaster);
							bean.setStationPhone(stationPhone);
							bean.setStationAddress(stationAddress);
							bean.setArea(area);
							bean.setEmergencyService(emergencyServiceCode);
							bean.setCode(code);
							bean.setRemark(remark);
							bean.setOffice(officeJson);
							bean.setOfficeSum(officeList.size());
							bean.setVehicle(vehicleJson);
							bean.setVehicleSum(totalAmount);
							bean.setEquipment(equipmentJson);
							bean.setEquipmentSum(totalNum);
							bean.setModTime(new Date());
							service.insert(bean);
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
			
			String code = request.getParameter("code");
			if (StringUtils.hasText(code)) {
				map.put("code", code);
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
			log.info("export ,name={},streetd={},districtId={}",name,streetId,districtId);
			List<FireStation> xcbs = service.queryByMap(map);
			xlsLibFireStationHandler.createSheet(wb, xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime："+(end-begin));
			fName = "消防站清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			
		}catch (Exception e){
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出清单失败,time:{}",date,e);
			XlsLibStreetHandler.createExceptionSheet(wb,e.toString());
			fName = "导出清单失败,请联系管理员"+date;
		}

		return ExportExcelUtils.createDownloadExcel(fName,wb,request);
	}
	 
}
