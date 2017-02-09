package org.fire.platform.modules.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.domain.Dict;
import org.fire.platform.modules.sys.service.IDictService;
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
 * @date 2016-9-18 17:24:48
 */

@Controller
@RequestMapping("/dict")
public class DictController{

	@Autowired
	private IDictService service;
	private static Logger log = LoggerFactory.getLogger(DictController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(int pageNo,int pageSize,Map<String, Object> params){
	    log.info("query,page={},pageSize={},params={}",pageNo,pageSize,params);
	    pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
	    PageInfo<Dict> page =  service.queryPageByMap(params, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotal());
		data.put("rows", page.getList());
		return data;
	}
	
	@RequestMapping(value = "/queryByTypeCode")
	@ResponseBody
	public CommonResult queryByDictCode(String typeCode, Integer pageNo,Integer pageSize){
	    log.info("query,page={},pageSize={},dictCode={}",pageNo,pageSize,typeCode);
	    pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
	    Map<String,Object> params = new HashMap<String,Object>();
	    params.put("typeCode", typeCode);
	    PageInfo<Dict> page =  service.queryPageByMap(params, pageNo, pageSize);
		return CommonResult.success(page.getList());
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Dict get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody Dict bean){
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
	 public CommonResult update(@RequestBody Dict bean){
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
}
