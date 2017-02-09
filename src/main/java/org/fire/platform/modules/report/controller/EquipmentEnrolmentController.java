package org.fire.platform.modules.report.controller;

import org.fire.platform.common.base.BaseController;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.EquipmentEnrolmentBean;
import org.fire.platform.modules.report.bean.EquipmentTypeBean;
import org.fire.platform.modules.report.domain.EquipmentEnrolment;
import org.fire.platform.modules.report.service.IEquipmentEnrolmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Controller
@RequestMapping("/equipmentEnrolment")
public class EquipmentEnrolmentController extends BaseController {

	@Autowired
	private IEquipmentEnrolmentService service;
	private static Logger log = LoggerFactory.getLogger(EquipmentEnrolmentController.class);

	/*@RequestMapping(value = "/query")
	@ResponseBody
	public Page<EquipmentEnrolment> query(int page,int pageSize,EquipmentEnrolment param){
	    log.info("query,page={},pageSize={},param={}",page,pageSize,param);
	    PageInfo<EquipmentEnrolment> pageInfo = service.queryPageByParam(page,pageSize, param);
		return getPage(pageInfo);
	}*/
	
	@RequestMapping(value = "/get")
	@ResponseBody
	public EquipmentEnrolment get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insert")
	 @ResponseStatus(HttpStatus.CREATED)
	 @ResponseBody
	 public CommonResult create(@RequestBody EquipmentEnrolment bean){
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
	 public CommonResult update(@RequestBody EquipmentEnrolment bean){
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

	@RequestMapping(value = "/getBeans", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getBeans(@RequestParam(value = "page",defaultValue = "1")Integer page ,
								 @RequestParam(value = "rows",defaultValue = "10")Integer pageSize ,
								 Long reportId) {
		if(reportId == null){
			return CommonResult.fail("无效参数！");
		}
		PageInfo<EquipmentEnrolmentBean> pageInfo = service.getBeanList(page, pageSize, reportId);
		return CommonResult.success(pageInfo.getList(),pageInfo.getTotal());
	}


	@RequestMapping(value = "/getBeans2", method = RequestMethod.GET)
	@ResponseBody
	public CommonResult getBeans2(@RequestParam(value = "page",defaultValue = "1")Integer page ,
								 @RequestParam(value = "rows",defaultValue = "10")Integer pageSize ,
								 Long reportId) {
		if(reportId == null){
			return CommonResult.fail("无效参数！");
		}
		List<EquipmentTypeBean> list = service.getBeanList2(reportId);
		return CommonResult.success(list);
	}


}
