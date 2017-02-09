package org.fire.platform.modules.report.controller;

import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.bean.CheckItemResultStatisBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import  org.fire.platform.modules.report.service.ICheckItemResultStatisService;
import  org.fire.platform.modules.report.domain.CheckItemResultStatis;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Controller
@RequestMapping("/checkItemResultStatis")
public class CheckItemResultStatisController extends BaseController {

	@Autowired
	private ICheckItemResultStatisService service;
	private static Logger log = LoggerFactory.getLogger(CheckItemResultStatisController.class);

	/*@RequestMapping(value = "/query")
	@ResponseBody
	public Page<CheckItemResultStatis> query(int page,int pageSize,CheckItemResultStatis param){
	    log.info("query,page={},pageSize={},param={}",page,pageSize,param);
	    PageInfo<CheckItemResultStatis> pageInfo = service.queryPageByParam(page,pageSize, param);
		return getPage(pageInfo);
	}
	*/
	@RequestMapping(value = "/get")
	@ResponseBody
	public CheckItemResultStatis get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insert")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(@RequestBody CheckItemResultStatis bean){
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
	 public CommonResult update(@RequestBody CheckItemResultStatis bean){
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

	@RequestMapping(value = "/getResultBean", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getResultBean(@RequestParam(value = "page",defaultValue = "1") Integer page,
									  @RequestParam(value = "rows",defaultValue = "20") Integer pageSize, Long reportId) {
		PageInfo<CheckItemResultStatisBean> beanPageInfo = service.queryStatisBeanByReportId(page,pageSize,reportId);
		return CommonResult.success(beanPageInfo.getList(),beanPageInfo.getTotal());
	}
}
