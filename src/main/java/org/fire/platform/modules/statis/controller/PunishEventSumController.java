package org.fire.platform.modules.statis.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.PunishEventSumBean;
import org.fire.platform.modules.statis.domain.PunishEventSum;
import org.fire.platform.modules.statis.service.IPunishEventSumService;
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
@RequestMapping("/punishEventSum")
public class PunishEventSumController{

	@Autowired
	private IPunishEventSumService service;
	private static Logger log = LoggerFactory.getLogger(PunishEventSumController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String streetId,
			@RequestParam(required = false) String blockId,
			@RequestParam(required = false) String districtId,
			@RequestParam(required = false) String punishMonthStart,
			@RequestParam(required = false) String punishMonthEnd
		){
		  log.info("query,page={},pageSize={},streetId={},blockId={},districtId={},punishMonthStart={},punishMonthEnd={}",
				  pageNo,pageSize,streetId,blockId,districtId,punishMonthStart,punishMonthEnd);
		    Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.hasText(streetId) && !streetId.equals("0")) {
				map.put("streetId", streetId);
			}
			if (StringUtils.hasText(blockId) && !blockId.equals("0")) {
				map.put("blockId", blockId);
			}
			if (StringUtils.hasText(districtId) && !districtId.equals("0")) {
				map.put("districtId", districtId);
			}
			if(StringUtils.hasText(punishMonthStart)){
				map.put("punishMonthStart", punishMonthStart);
			}
			if(StringUtils.hasText(punishMonthEnd)){
				map.put("punishMonthEnd", punishMonthEnd);
			}
			map.put("extraOrderColumns" , " year DESC , month DESC");
		    PageInfo<PunishEventSumBean> page =  service.queryPageBeanByMap(map, pageNo, pageSize);
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
	public PunishEventSum get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody PunishEventSum bean){
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
	 public CommonResult update(@RequestBody PunishEventSum bean){
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
