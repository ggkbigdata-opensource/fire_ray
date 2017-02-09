package org.fire.platform.modules.building.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fire.platform.App;
import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.building.domain.BuildingSubject;
import org.fire.platform.modules.building.domain.Management;
import org.fire.platform.modules.building.service.IBuildingSubjectService;
import org.fire.platform.modules.building.service.IManagementService;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.util.ChineseToEnglish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.fire.platform.modules.building.bean.ManagementBean;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */

@Controller
@RequestMapping("/management")
public class ManagementController extends BaseController{

	@Autowired
	private IManagementService service;

	@Autowired
	private IBuildingSubjectService buildingSubjectService;

	private static Logger log = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String managerUnitName,
			@RequestParam(required = false) String buildingId
		){
	    log.info("query,page={},pageSize={},managerUnitName={}",pageNo,pageSize,managerUnitName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(managerUnitName)) {
			map.put("managerUnitName", managerUnitName);
		}
		if (StringUtils.hasText(buildingId)) {
			map.put("buildingId", buildingId);
		}
	    PageInfo<Management> page =  service.queryPageByMap(map, pageNo, pageSize);
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
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public Management get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}

	@RequestMapping(value = "/getByBuildingId")
	@ResponseBody
	public ManagementBean getByBuildingId(@RequestParam(required = false) Long buildingId){
		log.info("getByBuildingId,buidlingId={}",buildingId);
		return 	service.getByBuildingId(buildingId);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(Management bean,
								@RequestParam(required = false) String publishTimeString,
								HttpSession session){
	     log.info("create,bean={}",bean);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	     try{
			 if (StringUtils.hasText(publishTimeString)) {
				 Date publishTime = sdf.parse(publishTimeString);
				 bean.setPublishTime(publishTime);
			 }
			 Long buildingId =bean.getBuildingId();
			 if (buildingId != null){
				 BuildingSubject buildingSubject = buildingSubjectService.get(buildingId);
				 if (buildingSubject != null){
					 String buildingCode = buildingSubject.getBaseCode();
					 String managementCode = buildingCode+ ChineseToEnglish.getPinYinHeadUpperChar(bean.getManagerUnitName());
					 bean.setBaseCode(managementCode);
				 }
			 }
	      	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		  log.error("新增失败，请重新登录"); 
				    return CommonResult.fail("新增失败，请重新登录");
			}
		  	bean.setUserId(user.getUid());
		  	bean.setCreateDate(new Date());
		  	bean.setModDate(new Date());
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult update(Management bean,
								@RequestParam(required = false) String publishTimeString,
								HttpSession session){
	     log.info("update,bean={}",bean);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	     try{
			 if (StringUtils.hasText(publishTimeString)) {
				 Date publishTime = sdf.parse(publishTimeString);
				 bean.setPublishTime(publishTime);
			 }
			 Long buildingId =bean.getBuildingId();
			 if (buildingId != null){
				 BuildingSubject buildingSubject = buildingSubjectService.get(buildingId);
				 if (buildingSubject != null){
					 String buildingCode = buildingSubject.getBaseCode();
					 String managementCode = buildingCode+ ChineseToEnglish.getPinYinHeadUpperChar(bean.getManagerUnitName());
					 bean.setBaseCode(managementCode);
				 }
			 }
	      	 User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		  	 if (user == null) {
		  		   log.error("修改失败，请重新登录"); 
				    return CommonResult.fail("修改失败，请重新登录");
			}
    	  	bean.setUserId(user.getUid());
    	  	bean.setModDate(new Date());
		    service.update(bean);
		    return CommonResult.success("修改成功");
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
}
