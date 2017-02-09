package org.fire.platform.modules.report.controller;

import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.Page;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemUnqualified;
import org.fire.platform.modules.report.service.ICheckItemUnqualifiedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Controller
@RequestMapping("/checkItemUnqualified")
public class CheckItemUnqualifiedController extends BaseController {

	@Autowired
	private ICheckItemUnqualifiedService service;
	private static Logger log = LoggerFactory.getLogger(CheckItemUnqualifiedController.class);

/*	@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CheckItemUnqualified> query(int page,int pageSize,CheckItemUnqualified param){
	    log.info("query,page={},pageSize={},param={}",page,pageSize,param);
	    PageInfo<CheckItemUnqualified> pageInfo = service.queryPageByParam(page,pageSize, param);
		return getPage(pageInfo);
	}*//*
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public CheckItemUnqualified get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insert")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(@RequestBody CheckItemUnqualified bean){
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
	 public CommonResult update(@RequestBody CheckItemUnqualified bean){
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
	 }*/

	@RequestMapping(value = "/getUnqualifiedBean", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getBean(@RequestParam(value = "page",defaultValue = "1")Integer page,
								@RequestParam(value = "rows",defaultValue = "20")Integer pageSize,Long reportId) {
		if(reportId == null){
			return CommonResult.fail("参数错误！");
		}
		PageInfo<CheckItemUnqualifiedBean> unqualifiedBeanPageInfo = service.getUnqualifiedBeanByReportId(page, pageSize, reportId);
		return CommonResult.success(unqualifiedBeanPageInfo.getList(),unqualifiedBeanPageInfo.getTotal());
	}
}
