package org.fire.platform.modules.report.controller;

import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.service.ICheckItemDefService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import  org.fire.platform.modules.report.domain.CheckItemDef;

import java.util.HashMap;
import java.util.Map;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Controller
@RequestMapping("/checkItemDef")
public class CheckItemDefController extends BaseController {

	@Autowired
	private ICheckItemDefService service;
	private static Logger log = LoggerFactory.getLogger(CheckItemDefController.class);

	@RequestMapping(value = "/query")
	@ResponseBody
	public CommonResult query(int page, int rows, @RequestParam(value = "name", required = false) String name,
							  @RequestParam(value = "code", required = false) String code,
							  @RequestParam(value = "level", required = false) String level) {
		log.info("CheckItemDefController -> query params -> page = {}, rows = {}, name = {}, code = {}, level = {} ", page, rows, name, code, level);
		Map<String, Object> params = new HashMap<>();
		if (StringUtils.hasText(name)) {
			params.put("name", name);
		}
		if (StringUtils.hasText(code)) {
			params.put("code", code);
		}
		if (StringUtils.hasText(level)) {
			params.put("level", level);
		}
		PageInfo<CheckItemDef> pageInfo = service.queryPageByMap(params, page, rows);
		return CommonResult.success(pageInfo.getList(), pageInfo.getTotal());
	}
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public CheckItemDef get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insert")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(@RequestBody CheckItemDef bean){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/update")
	 @ResponseStatus(HttpStatus.OK)
	 @ResponseBody
	 public CommonResult update(@RequestBody CheckItemDef bean){
	     log.info("update,bean={}",bean);
	     try{
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/delete")
	 @ResponseStatus(HttpStatus.OK)
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
}
