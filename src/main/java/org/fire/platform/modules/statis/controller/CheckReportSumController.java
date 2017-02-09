package org.fire.platform.modules.statis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.CheckReportSumBean;
import org.fire.platform.modules.statis.domain.CheckReportSum;
import org.fire.platform.modules.statis.service.ICheckReportSumService;
import org.fire.platform.modules.sys.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

@Controller
@RequestMapping("/checkReportSum")
public class CheckReportSumController{

	@Autowired
	private ICheckReportSumService service;
	private static Logger log = LoggerFactory.getLogger(CheckReportSumController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String districtId
		){
		  log.info("query,page={},pageSize={},streetId={},blockId={},districtId={}",
				  pageNo,pageSize,streetId,blockId,districtId);
		    Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.hasText(streetId)) {
				map.put("streetId", streetId);
			}
			if (StringUtils.hasText(blockId)) {
				map.put("blockId", blockId);
			}
			if (StringUtils.hasText(districtId)) {
				map.put("districtId", districtId);
			}
		    PageInfo<CheckReportSumBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
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
	public CheckReportSum get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody CheckReportSum bean){
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
	 public CommonResult update(@RequestBody CheckReportSum bean){
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
