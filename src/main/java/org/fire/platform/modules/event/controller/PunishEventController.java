package org.fire.platform.modules.event.controller;

import java.io.IOException;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.building.domain.BuildingSubject;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.fire.platform.modules.event.bean.PunishEventBean;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.domain.PunishRemove;
import org.fire.platform.modules.event.domain.PunishSealScene;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.event.service.IPunishRemoveService;
import org.fire.platform.modules.event.service.IPunishSealSceneService;
import org.fire.platform.modules.statis.handler.XlsLibPunishEventHandler;
import org.fire.platform.modules.statis.handler.XlsLibPunishEventReportHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.statis.service.IPunishEventSumService;
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


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */

@Controller
@RequestMapping("/punishEvent")
public class PunishEventController{

	@Autowired
	private IPunishEventService service;
	
	@Autowired
	private IStreetService streetService;
	
	@Autowired
	private IBlockService blockService;
	
	@Autowired
	private IDistrictService districtService;
	
	@Autowired
	private IBuildingSubjectService buildingSubjectService;
	
	@Autowired
	private XlsLibPunishEventHandler xlsLibPunishEventHandler;
	
	@Autowired
	private IDictService dictService;

	@Autowired
	private IPunishEventSumService punishEventSumService;

	@Autowired
	private IPunishSealSceneService punishSealSceneService;

	@Autowired
	private IPunishRemoveService punishRemoveService;

	@Autowired
	private XlsLibPunishEventReportHandler xlsLibPunishEventReportHandler;

	
	private static Logger log = LoggerFactory.getLogger(PunishEventController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String punishNumber,
			@RequestParam(required = false) String punishType,
			@RequestParam(required = false) String placeName,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String buildingId,
			@RequestParam(required = false) String punishStartMonth,
			@RequestParam(required = false) String punishEndMonth,
			@RequestParam(required = false) String punishTypeName,
			@RequestParam(required = false) String year
		){
		log.info("query,page={},pageSize={},name={},punishNumber={},placeName={},streetId={},blockId={},buildingId={}",
			  pageNo,pageSize,name,punishNumber,placeName,streetId,blockId,buildingId);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(punishNumber)) {
			map.put("punishNumber", punishNumber);
		}
		if (StringUtils.hasText(punishType) && !punishType.equals("0")) {
			map.put("punishType", punishType);
		}
		if (StringUtils.hasText(placeName)) {
			map.put("placeNameLike", placeName);
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
		if (StringUtils.hasText(buildingId)) {
			map.put("buildingId", buildingId);
		}
		if(StringUtils.hasText(punishStartMonth)){
			map.put("punishStartMonth", punishStartMonth);
		}
		if(StringUtils.hasText(punishEndMonth)){
			map.put("punishEndMonth", punishEndMonth);
		}
		if (StringUtils.hasText(punishTypeName)) {
			String punishTypeCode = dictService.getDicCodeByName(punishTypeName,"punish_type");
			if (punishTypeCode != null) {
				map.put("punishType",punishTypeCode);
			}
		}
		if(StringUtils.hasText(year)  && !"null".equals(year)){
			map.put("year", year);
		}
		
		map.put("extraOrderColumns"," punish_time DESC ");
	    PageInfo<PunishEventBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
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
	public PunishEvent get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(PunishEvent bean,
			 @RequestParam(required = false) String punishTimeString,
			 HttpSession session){
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  		SimpleDateFormat formatSdf = new SimpleDateFormat("yyyy-MM-dd");
 		String punishTimeForCode = "";
  		try {
 			String punishNumber = "";
 			if (StringUtils.hasText(punishTimeString)) {
 				Date punishTime = sdf.parse(punishTimeString);
 				punishTimeForCode = formatSdf.format(punishTime);
 				bean.setPunishTime(punishTime);
			}else{
				punishTimeString = "";
			}
			 //执法编码根据时间、建筑场所、当事人、执法人来生成
 			String placeName = bean.getPlaceName();
 			String placeOwner = bean.getPlaceOwner();
 			String punishPersonName = bean.getPunishPersonName();
			if (StringUtils.hasText(placeName) && StringUtils.hasText(placeOwner) && StringUtils.hasText(punishPersonName)) {
				punishNumber = ChineseToEnglish.getPinYinHeadUpperChar(placeName)+"_"+
				ChineseToEnglish.getPinYinHeadUpperChar(placeOwner)+"_"+	ChineseToEnglish.getPinYinHeadUpperChar(punishPersonName)
				+"_"+punishTimeForCode;
				bean.setPunishNumber(punishNumber);
			}
			Long blockId = bean.getBlockId();
			if (blockId != null) {
				Block block =blockService.get(blockId);
				if (block != null) {
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
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
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(PunishEvent bean,
			 @RequestParam(required = false) String punishTimeString,
			 HttpSession session){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  		SimpleDateFormat formatSdf = new SimpleDateFormat("yyyy-MM-dd");
 		String punishTimeForCode = "";
  		try {
 			String punishNumber = "";
 			if (StringUtils.hasText(punishTimeString)) {
 				Date punishTime = sdf.parse(punishTimeString);
 				punishTimeForCode = formatSdf.format(punishTime);
 				bean.setPunishTime(punishTime);
			}else{
				punishTimeString = "";
			}
			 //执法编码根据时间、建筑场所、当事人、执法人来生成
 			String placeName = bean.getPlaceName();
 			String placeOwner = bean.getPlaceOwner();
 			String punishPersonName = bean.getPunishPersonName();
			if (StringUtils.hasText(placeName) && StringUtils.hasText(placeOwner) && StringUtils.hasText(punishPersonName)) {
				punishNumber = ChineseToEnglish.getPinYinHeadUpperChar(placeName)+"_"+
				ChineseToEnglish.getPinYinHeadUpperChar(placeOwner)+"_"+	ChineseToEnglish.getPinYinHeadUpperChar(punishPersonName)
				+"_"+punishTimeForCode;
				bean.setPunishNumber(punishNumber);
			}
			Long blockId = bean.getBlockId();
			if (blockId != null) {
				Block block =blockService.get(blockId);
				if (block != null) {
					bean.setDistrictId(block.getDistrictId());
					bean.setStreetId(block.getStreetId());
				}
			}
 		} catch (ParseException e1) {
 		    log.error("修改失败",e1); 
 		    return CommonResult.fail("修改失败");
 		}
	     log.info("update,bean={}",bean);
	     try{
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("修改失败，请登陆"); 
	  		      return CommonResult.fail("修改失败，请登陆");
			}
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
		  return CommonResult.success("批量删除成功，删除记录："+ok+"条");
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
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2017_01_18_punishEvent")){
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
			Long districtId = districtService.queryBeanByName(cellValue.get(0).trim()); // TODO 必填
			Long streetId = streetService.queryBeanByName(cellValue.get(1).trim()); // TODO 必填
			Long blockId = blockService.queryBeanByName(cellValue.get(2).trim()); // TODO 必填
			Long buildingId = 0L;
			// 行政处罚、查封决定书文号 TODO 必填
			String decisionNumber = cellValue.get(6).trim();

			if (StringUtils.hasText(decisionNumber)) {
				if (!nameSet.add(decisionNumber)) {
					errorList.add("出现重复的决定书文号[" + decisionNumber + "]，请检查所有决定书文号不重复再上传Excel");
				}
			} else {
				errorList.add("决定书文号不能为空，请确保Excel内容格式正确再上传");
			}

			//提示错误信息
			if (errorList.size() > 0) {
				StringBuilder sb = new StringBuilder();
				for (String s : errorList) {
					sb.append(s + "<br>");
				}
				return CommonResult.fail(sb.toString());
			}
			// 行政区
			if(districtId != null){
				// 街道
				if (streetId != null) {
					// 社区
					if(blockId != null){
						// 如果有填写则验证数据库是否存在该建筑，如果没填写就不验证
						String buildingName = cellValue.get(3);
						if (StringUtils.hasText(buildingName)) {
							buildingId = buildingSubjectService.queryBeanByName(buildingName.trim());
							if (buildingId  ==  null) {
								log.error("导入失败，建筑不存在，建筑名称："+buildingName);
								return CommonResult.fail("导入失败，建筑不存在，建筑名称："+buildingName);
							}
						}
						// 违法单位名称 TODO 必填
						String placeName = cellValue.get(4).trim();
						// 违法单位地址 TODO 必填
						String punishAddress = cellValue.get(5).trim();


						// 处罚/查封依据 TODO 必填
						String basis = cellValue.get(7).trim();
						// 执法名称
						String name = cellValue.get(8).trim();
						// 执法人名称
						String punishPersonName = cellValue.get(9).trim();
						// 发生时间 TODO 必填
						String punishTimeString = cellValue.get(10).trim();
						Date punishTime;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						try {
							punishTime = sdf.parse(punishTimeString);
						} catch (ParseException e) {
							log.error("导入失败，时间格式填写错误："+punishTimeString);
							return CommonResult.fail("导入失败，时间格式填写错误："+punishTimeString);
						}

						//执法类型 punish_type
						String punishTypeName = cellValue.get(11);
						String punishTypeCode = "";
						if (StringUtils.hasText(punishTypeName)){
							punishTypeCode = dictService.getDicCodeByName(punishTypeName,"punish_type");
							if (punishTypeCode == null) {
								log.error("导入失败，处罚方式不存在，请联系系统管理员："+punishTypeName);
								return CommonResult.fail("导入失败，处罚方式不存在，请联系系统管理员："+punishTypeName);
							}
						}

						// 法定代表人/责任人
						String placeOwner  = cellValue.get(12).trim();
						// 责任人身份证号码
						String placeOwnerIdcard =  cellValue.get(13).trim();
						// 金额
						Double punishAmount = 0.0;
						if(StringUtils.hasText(cellValue.get(14))){
							punishAmount = Double.parseDouble(cellValue.get(14).trim());
						}


						// 查封部位、范围
						String sealedParts =  cellValue.get(15).trim();
						// 查封开始时间
						String sealedStartTimeString = cellValue.get(16).trim();
						Date sealedStartTime;
						try {
							sealedStartTime = sdf.parse(sealedStartTimeString);
						} catch (ParseException e) {
							log.error("导入失败，时间格式填写错误："+sealedStartTimeString);
							return CommonResult.fail("导入失败，时间格式填写错误："+sealedStartTimeString);
						}
						// 查封结束时间
						String sealedEndTimeString = cellValue.get(17).trim();
						Date sealedEndTime;
						try {
							sealedEndTime = sdf.parse(sealedEndTimeString);
						} catch (ParseException e) {
							log.error("导入失败，时间格式填写错误："+sealedEndTimeString);
							return CommonResult.fail("导入失败，时间格式填写错误："+sealedEndTimeString);
						}
						//----------------解封开始--------------
						// 解封决定书文号
						String removeDecisionNumber = cellValue.get(18).trim();
						// 解除依据
						String removeBasis = cellValue.get(19).trim();
						// 解封时间
						String removeTimeString = cellValue.get(20).trim();
						Date removeTime;
						try {
							removeTime = sdf.parse(removeTimeString);
						} catch (ParseException e) {
							log.error("导入失败，解封时间格式填写错误："+removeTimeString);
							return CommonResult.fail("导入失败，解封时间格式填写错误："+removeTimeString);
						}
						// 解封复查情况
						String checkSituation = cellValue.get(21).trim();
						//----------------解封结束--------------


						//------------------临时进入现场开始--------------------------------
						//临时进入决定书文号
						String sealSceneDecisionNumber =  cellValue.get(22).trim();
						// 临时进入时间
						String entryTimeString = cellValue.get(23).trim();
						Date entryTime;
						try {
							entryTime = sdf.parse(entryTimeString);
						} catch (ParseException e) {
							log.error("导入失败，临时进入时间格式填写错误："+entryTimeString);
							return CommonResult.fail("导入失败，临时进入时间格式填写错误："+entryTimeString);
						}
						// 临时进入依据
						String sealSceneBasis = cellValue.get(24).trim();
						// 临时进入现场范围
						String sceneRange = cellValue.get(25).trim();
						// 备注
						String remark = cellValue.get(26).trim();
						//------------------临时进入现场结束--------------------------------

						Long id = service.queryBeanByDecisionNumber(decisionNumber);
						PunishEvent bean;
						if(id == null){
							bean = new PunishEvent();
							isNew = true;
						}else{
							PunishEvent beanSql = service.get(id);
							bean = beanSql;
						}
						bean.setDistrictId(districtId);
						bean.setStreetId(streetId);
						bean.setBlockId(blockId);
						bean.setBuildingId(buildingId);
						bean.setName(name);
						bean.setDecisionNumber(decisionNumber);
						bean.setPunishPersonName(punishPersonName);
						bean.setPlaceName(placeName);
						bean.setPunishAddress(punishAddress);
						bean.setPlaceOwner(placeOwner);
						bean.setPlaceOwnerIdcard(placeOwnerIdcard);
						bean.setBasis(basis);
						bean.setPunishType(punishTypeCode);
						bean.setPunishTime(punishTime);
						bean.setPunishAmount(punishAmount);
						bean.setSealedParts(sealedParts);
						bean.setSealedStartTime(sealedStartTime);
						bean.setSealedEndTime(sealedEndTime);
						bean.setRemark(remark);
						bean.setCreateTime(new Date());
						if (isNew) {
							service.insert(bean);
							countInsert++;
						}else {
							service.update(bean);
							countUpdate++;
							log.info("[IMPORT_STREET]插入数据，更新执法数据，id="+bean.getId()+"，decisionNumber="+bean.getDecisionNumber());
						}
						if (StringUtils.hasText(removeDecisionNumber)){
							PunishRemove pr = new PunishRemove();
							pr.setPunishEventId(bean.getId());
							pr.setDecisionNumber(removeDecisionNumber);
							pr.setRemoveTime(removeTime);
							pr.setCheckSituation(checkSituation);
							pr.setBasis(removeBasis);
							pr.setCreateTime(new Date());
							punishRemoveService.insert(pr);
						}

						if (StringUtils.hasText(sealSceneDecisionNumber)){
							PunishSealScene pss = new PunishSealScene();
							pss.setPunishEventId(bean.getId());
							pss.setDecisionNumber(sealSceneDecisionNumber);
							pss.setBasis(sealSceneBasis);
							pss.setEntryTime(entryTime);
							pss.setSceneRange(sceneRange);
							pss.setCreateTime(new Date());
							punishSealSceneService.insert(pss);
						}
					}else {
						log.error("导入失败，区域不存在，处罚/查封决定文号："+decisionNumber);
						return CommonResult.fail("导入失败，区域不存在，处罚/查封决定文号："+decisionNumber);
					}
				}else {
					log.error("导入失败，街道不存在，处罚/查封决定文号："+decisionNumber);
					return CommonResult.fail("导入失败，街道不存在，处罚/查封决定文号："+decisionNumber);
				}
			}else {
				log.error("导入失败，行政区不存在，处罚/查封决定文号："+decisionNumber);
				return CommonResult.fail("导入失败，行政区不存在，处罚/查封决定文号："+decisionNumber);
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
			String name = request.getParameter("name");
			if (StringUtils.hasText(name)) {
				map.put("nameLike", name);
			}
			String placeName = request.getParameter("placeName");
			if (StringUtils.hasText(placeName)) {
				map.put("placeNameLike", placeName);
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
			
			String punishType = request.getParameter("punishType");
			if (StringUtils.hasText(punishType)  && !punishType.equals("0")) {
				map.put("punishType",punishType);
			}
			log.info("export ,name={},placeName={},streetId={},blockId={},punishType={}",name,placeName,streetId,blockId,punishType);
			List<PunishEvent> xcbs = service.queryByMap(map);
			xlsLibPunishEventHandler.createSheet(wb, "执法清单", xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime："+(end-begin));
			fName = "执法清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			
		}catch (Exception e){
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出清单失败,time:{}",date,e);
			XlsLibStreetHandler.createExceptionSheet(wb,e.toString());
			fName = "导出清单失败,请联系管理员"+date;
		}

		return ExportExcelUtils.createDownloadExcel(fName,wb,request);
	}

	@RequestMapping(value = "/exportReport", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> exportReport(HttpServletRequest request, HttpSession session) {
		HSSFWorkbook wb;
		String fName;
		try {
			wb = new HSSFWorkbook();  // 创建一个Excel文件
			Long begin = System.currentTimeMillis();
			String districtId = request.getParameter("districtId");
			String monthBegin = request.getParameter("monthBegin");
			String monthEnd = request.getParameter("monthEnd");
			Map<String, Object> params = new HashMap<>();
			params.put("districtId", districtId);
			params.put("startMonth", monthBegin);
			params.put("endMonth", monthEnd);
			params.put("lastStartMonth", getLastYear(monthBegin));
			params.put("lastEndMonth", getLastYear(monthEnd));
			List<AreaTypeDataVo> fireSumDataList = punishEventSumService.queryPunishSumStatis(params);
			xlsLibPunishEventReportHandler.createSheet(districtId, monthBegin, monthEnd, wb, "执法情况报表", fireSumDataList);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime：" + (end - begin));
			fName = "执法情况报表_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());

		} catch (Exception e) {
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出报表失败,time:{}", date, e);
			XlsLibStreetHandler.createExceptionSheet(wb, e.toString());
			fName = "导出报表失败,请联系管理员" + date;
		}

		return ExportExcelUtils.createDownloadExcel(fName, wb, request);
	}

	private String getLastYear(String month) {
		if (StringUtils.hasText(month) && month.contains("-")) {
			String[] months = month.split("-");
			return (Integer.parseInt(months[0]) - 1) + "-" + months[1];
		}
		return null;
	}
	 
}
