package org.fire.platform.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.domain.Role;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Controller
@RequestMapping("/role")
public class RoleController{

	@Autowired
	private IRoleService service;
	private static Logger log = LoggerFactory.getLogger(RoleController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(
			@RequestParam(value="page", defaultValue="1") int pageNo, 
			@RequestParam(value="rows", defaultValue="10") int pageSize,
			HttpSession session,
			@RequestParam(required = false) String roleName
		){
	    log.info("query,page={},pageSize={},roleName={}",pageSize,pageSize,roleName);
	    Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(roleName)) {
			map.put("roleName", roleName);
		}
	    PageInfo<Role> page =  service.queryPageByMap(map, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
	    User user = (User) session.getAttribute(App.USER_SESSION_KEY);
	  	 if (user != null) {
	   		data.put("total", page.getTotal());
			data.put("rows", page.getList());
		}
		return data;
	}


	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(
			HttpSession session,
			@RequestParam(required = false) String roleName
	){
		log.info("queryAll,roleName={}",roleName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(roleName)) {
			map.put("roleName", roleName);
		}
		List<Role> roles = service.queryByMap(map);
		Map<String, Object> data = new HashMap<String, Object>();
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user != null) {
			data.put("total", roles.size());
			data.put("rows", roles);
		}
		return data;
	}
	
	@RequestMapping(value = "/queryAllForMenu")
	@ResponseBody
	public 	List<Role>  queryAllForMenu(
			HttpSession session,
			@RequestParam(required = false) String roleName
	){
		log.info("queryAllForMenu,roleName={}",roleName);
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.hasText(roleName)) {
			map.put("roleName", roleName);
		}
		List<Role> roles = service.queryByMap(map);
		return roles;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Role get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(Role bean){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
		    return CommonResult.success("新增成功",bean.getRoleId());
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(Role bean){
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
		  return CommonResult.success("删除成功记录："+ok+"条");
	}
}
