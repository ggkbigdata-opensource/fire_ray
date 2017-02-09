package org.fire.platform.modules.event.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.aspectj.weaver.ast.And;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.constants.AreaConstants;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.service.IAreaService;
import org.fire.platform.modules.area.service.IBlockService;
import org.fire.platform.modules.area.service.IDistrictService;
import org.fire.platform.modules.area.service.IStreetService;
import org.fire.platform.modules.area.vo.AreaCompareStreetData;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.building.domain.Building;
import org.fire.platform.modules.building.service.IBuildingService;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.fire.platform.modules.event.bean.FireEventBean;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.service.IFireEventService;
import org.fire.platform.modules.statis.handler.XlsLibFireEventHandler;
import org.fire.platform.modules.statis.handler.XlsLibFireEventReportHandler;
import org.fire.platform.modules.statis.handler.XlsLibStreetHandler;
import org.fire.platform.modules.statis.service.IFireEventSumService;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IDictService;
import org.fire.platform.util.ChineseToEnglish;
import org.fire.platform.util.DateUtil;
import org.fire.platform.util.ExcelUtil;
import org.fire.platform.util.ExportExcelUtils;
import org.fire.platform.util.itext.ItextManager;
import org.fire.platform.util.itext.News;
import org.fire.platform.util.itext.SvgPngConverter;
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
@RequestMapping("/fireEvent")
public class FireEventController{

	@Autowired
	private IFireEventService service;

	@Autowired
	private IStreetService streetService;

	@Autowired
	private IBlockService blockService;

	@Autowired
	private IDistrictService districtService;

	@Autowired
	private IBuildingSubjectService buildingSubjectService;

	@Autowired
	private XlsLibFireEventHandler xlsLibFireEventHandler;

	@Autowired
	private XlsLibFireEventReportHandler xlsLibFireEventReportHandler;

	@Autowired
	private IDictService dictService;

	@Autowired
	private IAreaService areaService;

	@Autowired
	private IFireEventSumService fireEventSumService;


	private static Logger log = LoggerFactory.getLogger(FireEventController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo,
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) Long areaId,
			@RequestParam(required = false)  Integer areaType,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String placeName,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String buildingId,
			@RequestParam(required = false) String occurMonth,
            @RequestParam(required = false) String occurStartMonth,
            @RequestParam(required = false) String occurEndMonth,
        	@RequestParam(required = false) String fireType, // 火灾类型
			@RequestParam(required = false) String fireReasonType, // 起火原因分类
//			@RequestParam(required = false) String buildingClass, // 起火场所
			@RequestParam(required = false) String placeUseType,
			@RequestParam(required = false) String fireEventState,
			@RequestParam(required = false) String year
		){
        log.info("query,page={},pageSize={},placeName={},streetId={},blockId={},districtId={},buildingId={},occurMonth={},occurStartMonth={},occurEndMonth={}",
                pageNo, pageSize, placeName, streetId, blockId, districtId, buildingId, occurMonth, occurStartMonth, occurEndMonth);
        Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(name)) {
			map.put("nameLike", name);
		}
		if (StringUtils.hasText(placeName)) {
			map.put("placeNameLike", placeName);
		}
		if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
			map.put("streetId", streetId);
		}
		if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
			map.put("blockId", blockId);
		}
		if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
			map.put("districtId", districtId);
		}
		if (areaId != null && !areaId.equals("0") ) {
			if( areaType == AreaConstants.AREA_TYPE_DISTRICT){ //查行政区
				map.put("districtId", areaId);
			}
			if( areaType == AreaConstants.AREA_TYPE_STREET ){//查街道
				map.put("streetId", areaId);
			}
			if( areaType == AreaConstants.AREA_TYPE_BLOCK ){//查社区
				map.put("blockId", areaId);
			}
		}

		if (StringUtils.hasText(buildingId)) {
			map.put("buildingId", buildingId);
		}
		if(StringUtils.hasText(occurMonth)){
			map.put("occurMonth", occurMonth);
		}
        if(StringUtils.hasText(occurStartMonth)){
            map.put("occurStartMonth", occurStartMonth);
        }
        if(StringUtils.hasText(occurEndMonth)){
            map.put("occurEndMonth", occurEndMonth);
        }
		if (StringUtils.hasText(fireType) && !fireType.equals("0")) {
			map.put("fireType", fireType);
		}
		if (StringUtils.hasText(fireReasonType) && !fireReasonType.equals("0")) {
			map.put("fireReasonType", fireReasonType);
		}

//		if (StringUtils.hasText(buildingClass) && !"0".equals(buildingClass)) {
//			map.put("buildingClass", buildingClass);
//		}

		if (StringUtils.hasText(placeUseType) && !"0".equals(placeUseType)) {
			map.put("placeUseType", placeUseType);
		}
		if (StringUtils.hasText(fireEventState) && !"0".equals(fireEventState)) {
			map.put("state", fireEventState);
		}
		if(StringUtils.hasText(year)  && !"null".equals(year)){
			map.put("year", year);
		}

		map.put("extraOrderColumns", " occur_time DESC ");
	    PageInfo<FireEventBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
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
	public FireEvent get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}

 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(FireEvent bean,
			 @RequestParam(required = false) String occurTimeString,
			 HttpSession session
			 ){
 		 Boolean isNew = false;
 		 try{
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  			if (StringUtils.hasText(occurTimeString)) {
  				Date occurTime = sdf.parse(occurTimeString);
  	 			bean.setOccurTime(occurTime);
			}
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("新增失败，请登陆");
	  		      return CommonResult.fail("新增失败，请登陆");
			}
	    	 Map<String, Object> map = new HashMap<String, Object>();
	    	 map.put("caseNumber",  bean.getCaseNumber());
	    	 List<FireEvent>fireEvents =  service.queryByMap(map);
	    	 if (fireEvents != null && fireEvents.size() >0) {
				FireEvent fireEvent = fireEvents.get(0);
				if (fireEvent != null) {
					bean = fireEvent;
				}else {
					isNew = true;
				}
			}else{
				isNew = true;
			}
			 Long blockId = bean.getBlockId();
			 if (blockId != null) {
				 Block block =blockService.get(blockId);
				 if (block != null) {
					 bean.setDistrictId(block.getDistrictId());
					 bean.setStreetId(block.getStreetId());
				 }
			 }
		    log.info("create,bean={}",bean);
			 bean.setCreateDate(new Date());
			 bean.setModDate(new Date());
	    	if (isNew) {
	    		  service.insert(bean);
			}else {
				  service.update(bean);
			}

		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e);
		    return CommonResult.fail("新增失败");
		 }

	 }

	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(FireEvent bean,HttpSession session,
			 @RequestParam(required = false) String occurTimeString){
 		 try{
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  			if (StringUtils.hasText(occurTimeString)) {
  				Date occurTime = sdf.parse(occurTimeString);
  	 			bean.setOccurTime(occurTime);
			}
			 Long blockId = bean.getBlockId();
			 if (blockId != null) {
				 Block block =blockService.get(blockId);
				 if (block != null) {
					 bean.setDistrictId(block.getDistrictId());
					 bean.setStreetId(block.getStreetId());
				 }
			 }
	    	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	    	 if (user == null) {
	    		  log.error("修改失败，请登陆");
	  		      return CommonResult.fail("修改失败，请登陆");
			}
		     log.info("update,bean={}",bean);
			 bean.setModDate(new Date());
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
//		建筑名称	火灾事故名称	场所名称	案号(唯一性)	消防手续	地理位置（城区、城中村）	工程性质（三合一、住改仓）	使用性质（酒店、厂房、学校、仓库、办公室）	火灾类型（冒烟警情、确认警情、原始警情）	案情	起火位置	起火物	起火原因分类（人为、自然）	起火原因	发生时间	经济损失	死亡人数	受伤人数
		List<String> title = dataList.get(0);
		// 修改模版的时候记得修改版本号
		if(!title.get(title.size()-1).equals("2016_10_31_fireEvent")){
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
		//行政区名称	街道名称	社区名称	城市区域	建筑名称	火灾事故名称	场所名称	火情案号(唯一性)	消防手续（下拉选择）	地理位置（下拉选择）	工程性质（下拉选择）	使用性质（下拉选择）	企业性质	火灾类型（下拉选择）	现场警情	案情	起火位置	起火物	起火原因分类（下拉选择）	起火原因	发生时间（例：2016/1/1 11:58:00）	经济损失	死亡人数	受伤人数	值班组	微型消防站参与	消防执法案号	是否自救	移交部门
		for(List<String> cellValue  : dataList){
			Boolean isNew = false;
			Long districtId = districtService.queryBeanByName(cellValue.get(0).trim());
			Long streetId = streetService.queryBeanByName(cellValue.get(1).trim());
			Long blockId = blockService.queryBeanByName(cellValue.get(2).trim());
			Long buildingId = 0L;
			//城市区域 必填
			String cityArea = cellValue.get(3).trim();
			// 名称
			String name = cellValue.get(5).trim();
			// 场所名称
 			String placeName = cellValue.get(6).trim();
 			// 案号（唯一性）外部系统传进来
 			String caseNumber = cellValue.get(7).trim();
			// 行政区
			if(districtId != null){
				// 街道
				if (streetId != null) {
					// 社区
					if(blockId != null){

							//城市区域
							if(!StringUtils.hasText(cityArea)){
								log.error("导入失败，城市区域必填,请检查数据");
								return CommonResult.fail("导入失败，城市区域必填,请检查数据");
							}

							// 如果有填写则验证数据库是否存在该建筑，如果没填写就不验证
							if (StringUtils.hasText(cellValue.get(4))) {
									buildingId = buildingSubjectService.queryBeanByName(cellValue.get(4).trim());
									if (buildingId  ==  null) {
										  log.error("导入失败，建筑不存在，建筑名称："+cellValue.get(4).trim());
							  		      return CommonResult.fail("导入失败，建筑不存在，建筑名称："+cellValue.get(4).trim());
									}
							}


							if (StringUtils.hasText(caseNumber)) {
								if (!nameSet.add(caseNumber)) {
									errorList.add("出现重复的火情案号[" + caseNumber + "]，请检查所有火情案号不重复再上传Excel");
								}
							} else {
								errorList.add("火情案号不能为空，请确保Excel内容格式正确再上传");
							}

							//提示错误信息
							if (errorList.size() > 0) {
								StringBuilder sb = new StringBuilder();
								for (String s : errorList) {
									sb.append(s + "<br>");
								}
								return CommonResult.fail(sb.toString());
							}
							Long id = service.queryBeanByCaseNumber(caseNumber);
							FireEvent bean;
							if(id == null){
								bean = new FireEvent();
								isNew = true;
							}else{
								FireEvent beanSql = service.get(id);
								bean = beanSql;
							}
							//消防手续 place_fire_type
							String placeFireTypeName = cellValue.get(8).trim();
							String placeFireTypeCode = "";
							if (StringUtils.hasText(placeFireTypeName)){
								placeFireTypeCode = dictService.getDicCodeByName(placeFireTypeName,"place_fire_type");
								if (placeFireTypeCode == null) {
									log.error("导入失败，消防手续不存在，请联系系统管理员："+placeFireTypeName);
									return CommonResult.fail("导入失败，消防手续不存在，请联系系统管理员："+placeFireTypeName);
								}
							}


							// 地理位置 place_position_type
							String placePositionTypeName = cellValue.get(9).trim();
							String placePositionTypeCode = "";
							if (StringUtils.hasText(placePositionTypeName)){
								placePositionTypeCode = dictService.getDicCodeByName(placePositionTypeName,"place_position_type");
								if (placePositionTypeCode == null) {
									log.error("导入失败，地理位置不存在，请联系系统管理员："+placePositionTypeName);
									return CommonResult.fail("导入失败，地理位置不存在，请联系系统管理员："+placePositionTypeName);
								}
							}

							// 工程性质 place_build_type 必填
							String placeBuildTypeName = cellValue.get(10).trim();
							String placeBuildTypeCode = "";
							if(StringUtils.hasText(placeBuildTypeName)){
								placeBuildTypeCode = dictService.getDicCodeByName(placeBuildTypeName,"place_build_type");
								if (placeBuildTypeCode == null) {
									log.error("导入失败，工程性质不存在，请联系系统管理员："+placeBuildTypeName);
									return CommonResult.fail("导入失败，工程性质不存在，请联系系统管理员："+placeBuildTypeName);
								}
							}else{
								log.error("导入失败，工程性质必填,请检查数据");
								return CommonResult.fail("导入失败，工程性质必填,请检查数据");
							}


						   //使用性质（酒店、厂房、学校、仓库、办公室）	 place_use_type
							String placeUseTypeName = cellValue.get(11).trim();
							String placeUseTypeCode = "";
							if(StringUtils.hasText(placeUseTypeName)){
								placeUseTypeCode = dictService.getDicCodeByName(placeUseTypeName,"place_use_type");
								if (placeUseTypeCode == null) {
									log.error("导入失败，使用性质不存在，请联系系统管理员："+placeUseTypeName);
									return CommonResult.fail("导入失败，使用性质不存在，请联系系统管理员："+placeUseTypeName);
								}
							}else{
								log.error("导入失败，使用性质必填,请检查数据");
								return CommonResult.fail("导入失败，使用性质必填,请检查数据");
							}


							// 企业性质
							String enterpriseNature = cellValue.get(12).trim();


							//火灾类型（冒烟火灾、确认火灾、原始火灾） fire_type
							String fireTypeName = cellValue.get(13).trim();
							String fireTypeCode = "";
							if(StringUtils.hasText(fireTypeName)){
								fireTypeCode = dictService.getDicCodeByName(fireTypeName,"fire_type");
								if (fireTypeCode == null) {
									log.error("导入失败，火灾类型不存在，请联系系统管理员："+fireTypeName);
									return CommonResult.fail("导入失败，火灾类型不存在，请联系系统管理员："+fireTypeName);
								}
							}else{
								log.error("导入失败，火灾类型必填,请检查数据");
								return CommonResult.fail("导入失败，火灾类型必填,请检查数据");
							}


							// 现场警情
							String sceneDesc = cellValue.get(14).trim();

							// 案情
							String description = cellValue.get(15).trim();

							// 起火位置
							String firePosition = cellValue.get(16).trim();

							// 起火物
							String fireObject = cellValue.get(17).trim();

							//起火原因分类  fire_reason_type
							String fireReasonTypeName = cellValue.get(18).trim();
							String fireyReasonTypeCode = "";
							if(StringUtils.hasText(fireReasonTypeName)){
								fireyReasonTypeCode = dictService.getDicCodeByName(fireReasonTypeName,"fire_reason_type");

								if (fireyReasonTypeCode == null) {
									log.error("导入失败，起火原因分类不存在，请联系系统管理员："+fireReasonTypeName);
									return CommonResult.fail("导入失败，起火原因分类不存在，请联系系统管理员："+fireReasonTypeName);
								}
							}else{
								log.error("导入失败，起火原因分类必填,请检查数据");
								return CommonResult.fail("导入失败，起火原因分类必填,请检查数据");
							}

							bean.setDistrictId(districtId);
							bean.setStreetId(streetId);
							bean.setBlockId(blockId);
							bean.setBuildingId(buildingId);
							bean.setCityArea(cityArea);
							bean.setName(name);
							bean.setPlaceName(placeName);
							bean.setCaseNumber(caseNumber);
							bean.setPlaceFireType(placeFireTypeCode);
							bean.setPlacePositionType(placePositionTypeCode);
							bean.setPlaceBuildType(placeBuildTypeCode);
							bean.setPlaceUseType(placeUseTypeCode);

							bean.setEnterpriseNature(enterpriseNature);

							bean.setFireType(fireTypeCode);
							bean.setSceneDesc(sceneDesc);
							bean.setDescription(description);
							bean.setFirePosition(firePosition);
							bean.setFireObject(fireObject);
							bean.setFireReasonType(fireyReasonTypeCode);
							bean.setFireReason(cellValue.get(19).trim());
							String punishTime = cellValue.get(20).trim();

							if (StringUtils.hasText(punishTime)) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								Date occurTimeDate;
								try {
									occurTimeDate = sdf.parse(punishTime);
									bean.setOccurTime(occurTimeDate);
								} catch (ParseException e) {
										log.error("导入失败，时间格式填写错误："+punishTime);
										return CommonResult.fail("导入失败，时间格式填写错误："+punishTime);
								}
							}

							if(StringUtils.hasText(cellValue.get(21).trim())){
								bean.setLoss(Double.parseDouble(cellValue.get(21).trim()));
							}

							if (StringUtils.hasText(cellValue.get(22).trim())) {
								bean.setDeadNum(Integer.parseInt(cellValue.get(22).trim()));
							}
							if (StringUtils.hasText(cellValue.get(23).trim())) {
								bean.setHurtNum(Integer.parseInt(cellValue.get(23).trim()));
							}
							// 值班组
							bean.setDutyPart(cellValue.get(24).trim());

							// 微信消防站 是否参与 25
							String fireStationName = cellValue.get(24).trim();
							if (StringUtils.hasText(fireStationName)){
								if ("是".equals(fireStationName)){
									bean.setFireStation(1);
								}else{
									bean.setFireStation(0);
								}
							}

							// 消防执法案号
							bean.setPunishCaseNumber(cellValue.get(26).trim());

							// 是否自救 27
							String selfSaveName = cellValue.get(27).trim();
							if (StringUtils.hasText(selfSaveName)){
								if ("是".equals(selfSaveName)){
									bean.setSelfSave(1);
								}else{
									bean.setSelfSave(0);
								}
							}

							// 移交部门
							bean.setHandleDepart(cellValue.get(28).trim());

							if (isNew) {
								service.insert(bean);
								countInsert++;
							}else {
								service.update(bean);
								countUpdate++;
								log.info("[IMPORT_STREET]插入数据，更新火情，id="+bean.getId()+"，caseNumber="+bean.getCaseNumber());
							}
					}else {
						  log.error("导入失败，区域不存在，执法名称："+name);
			  		      return CommonResult.fail("导入失败，区域不存在，执法名称："+name);
					}
				}else {
					  log.error("导入失败，街道不存在，区域名称："+name);
		  		      return CommonResult.fail("导入失败，街道不存在，执法名称："+name);
				}
			}else {
				  log.error("导入失败，行政区不存在，建筑名称："+name);
	  		      return CommonResult.fail("导入失败，行政区不存在，执法名称："+name);
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
	public ResponseEntity<byte[]> exportLibCourse(HttpServletRequest request,
			HttpSession session
										) {
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
			String districtId = request.getParameter("districtId");
			if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
				map.put("districtId", districtId);
			}
			String streetId = request.getParameter("streetId");
			if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
				map.put("streetId", streetId);
			}
			String blockId = request.getParameter("blockId");
			if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
				map.put("blockId", blockId);
			}

			String fireType = request.getParameter("fireType");
			if (StringUtils.hasText(fireType) && !fireType.equals("0")) {
				map.put("fireType", fireType);
			}
			String fireReasonType = request.getParameter("fireReasonType");
			if (StringUtils.hasText(fireReasonType) && !fireReasonType.equals("0")) {
				map.put("fireReasonType", fireReasonType);
			}

			String occurMonth = request.getParameter("occurTime");
			if (StringUtils.hasText(occurMonth)) {
				map.put("occurMonth", occurMonth);
			}
			map.put("extraOrderColumns", " occur_time DESC ");
			log.info("export ,name={},placeName={},streetId={},blockId={}", name, placeName, streetId, blockId);
			List<FireEvent> xcbs = service.queryFireEventByMap(map);
			xlsLibFireEventHandler.createSheet(wb, "火灾事故清单", xcbs);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime：" + (end - begin));
			fName = "火灾事故清单_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());

		} catch (Exception e) {
			wb = new HSSFWorkbook();
			String date = DateUtil.getTime("yyyyMMdd_HHmmss", new Date());
			log.info("导出清单失败,time:{}", date, e);
			XlsLibStreetHandler.createExceptionSheet(wb, e.toString());
			fName = "导出清单失败,请联系管理员" + date;
		}

		return ExportExcelUtils.createDownloadExcel(fName, wb, request);
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

//			List<AreaCompareStreetData> xcbs = areaService.queryFireDataGroup(Long.parseLong(districtId), 0,monthBegin,monthEnd);
//			xlsLibFireEventReportHandler.createSheet(districtId,monthBegin,monthEnd,wb, "火灾警情报表", xcbs);

            Map<String, Object> params = new HashMap<>();
            params.put("districtId", districtId);
            params.put("startMonth", monthBegin);
            params.put("endMonth", monthEnd);
            params.put("lastStartMonth", getLastYear(monthBegin));
            params.put("lastEndMonth", getLastYear(monthEnd));
            List<AreaTypeDataVo> fireSumDataList = fireEventSumService.queryFireSumStatis(params);
            xlsLibFireEventReportHandler.createSheet(districtId, monthBegin, monthEnd, wb, "火灾警情报表", fireSumDataList);

			Long end = System.currentTimeMillis();
			log.info("查询生成表格消耗时间costTime：" + (end - begin));
			fName = "火灾警情报表_" + DateUtil.getTime("yyyyMMdd_HHmmss", new Date());

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


	@RequestMapping(value="/exportword")
	public String exportWord(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String type = request.getParameter("type");
		response.setContentType("application/octet-stream; charset=UTF-8");
		if("word".equals(type)){
			response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".doc");
		}else if("pdf".equals(type)){
			response.setHeader("content-disposition", "attachment;filename=" + new SimpleDateFormat("yyyyMMddHH:mm:ss").format(new Date()) + ".pdf");
		}

		String svgCode = request.getParameter("svg");//highcharts图表svgCode
		String svg [] = svgCode.split("_");
		String path[] = new String[svg.length];
		//注意：我的svg是一个数组，因为我有多个图表，而且我首先将svg转换成图片然后存储到如下路径：
//		path[k] = request.getSession().getServletContext().getRealPath("/upload/"+picName);  

		OutputStream out = response.getOutputStream();
		ItextManager tm = ItextManager.getInstance();
//		最后，将图片写入到word中（图片数组）：
//		tm.createRtfContext(newsList,imageList,out,type);
		List<News> newsList = getData();
		List<String> imageList = new ArrayList<String>();
		File file =new File(request.getSession().getServletContext().getRealPath("/uploadCharts"));
		//如果文件夹不存在则创建
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		if(svg!=null){
			for(int k=0;k<svg.length;k++){
				String picName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+".png";
				path[k] = request.getSession().getServletContext().getRealPath("/uploadCharts/"+picName);
				imageList.add(path[k]);
				SvgPngConverter.convertToPng(svg[k], path[k]);
			}
		}
		tm.createRtfContext(newsList,imageList,out,type);

		out.flush();
		out.close();
		return null;
	}

	public List<News> getData() {
		List<News> newsList = new ArrayList<News>();
		News news1 = new News();
		news1.setTitle("标题：消防图表查看");
		news1.setContent("正文：XXXXXXXXXXXX");
		news1.setSite("站点：天河消防安全委员会");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String exportTime = sdf.format(new Date());
		news1.setPublishTime("导出时间："+exportTime);

//		News news2 = new News();
//		news2.setTitle("标题：[申万销售夏敬慧] 基金仓位周报----开基仓位下降1.51%");
//		news2.setContent("正文：理财产品部分析师: 杨鹏（18930809297） 开基仓位有所下降：本周，开放式基金平均仓位继续下降。");
//		news2.setSite("站点：腾讯网");
//		news2.setPublishTime("发布时间：2014-05-25");

		newsList.add(news1);
//		newsList.add(news2);

		return newsList;
	}
}
