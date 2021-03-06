package org.fire.platform.modules.area.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.domain.AreaMap;
import org.fire.platform.modules.area.service.IAreaMapService;
import org.fire.platform.modules.sys.domain.AppVersion;
import org.fire.platform.modules.sys.service.IAppVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-30 14:55:37
 */

@Controller
@RequestMapping("/areaMap")
public class AreaMapController{

	@Autowired
	private IAreaMapService service;
	
	@Autowired
	private IAppVersionService appVersionService;
	
	private static Logger log = LoggerFactory.getLogger(AreaMapController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(int pageNo,int pageSize,Map<String, Object> params){
	    log.info("query,page={},pageSize={},params={}",pageNo,pageSize,params);
	    pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
	    PageInfo<AreaMap> page =  service.queryPageByMap(params, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotal());
		data.put("rows", page.getList());
		return data;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public AreaMap get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody AreaMap bean){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(@RequestBody AreaMap bean){
	     log.info("update,bean={}",bean);
	     try{
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
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
	 
	 @RequestMapping(value = "/generateMap")
	 @ResponseBody
	 public CommonResult generateMap(){
		 
		 String str = service.generateMap();
		 ////TODO 更新Map的版本
		 updateMapVersion(str);
	     return CommonResult.success(str);
	 }
	 
	 private void updateMapVersion(String filePath){
		//TOOD 取之前的版本号,要求版本号用三位整数来定义
		 Map param = new HashMap();
		 param.put("state", 0);
		 List<AppVersion> list = appVersionService.queryAPPVersionByMap(param);
		 if(CollectionUtils.isEmpty(list)){
			 log.warn("没有取到地图版本");
			 return;
		 }
		 
		 AppVersion appVersion = list.get(0);
		 String newVersion =  getNewVersion(appVersion.getMapVersion());
		 //TOOD更新版本
		 AppVersion newAppVersion = new  AppVersion();

		 newAppVersion.setId(appVersion.getId());
		 newAppVersion.setMapVersion(newVersion);
		 //newAppVersion.setMapDownUrl(filePath);
		 appVersionService.update(newAppVersion);
	 }
	 
	 private String getNewVersion(String oldVersion){
		 Integer num = Integer.valueOf(oldVersion);
		 Integer nNum = num + 1;
		 return nNum.toString();
	 }
}
