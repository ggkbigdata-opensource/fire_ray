package org.fire.platform.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.domain.Menu;
import org.fire.platform.modules.sys.domain.RoleMenu;
import org.fire.platform.modules.sys.service.IMenuService;
import org.fire.platform.modules.sys.service.IRoleMenuService;
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
 * @date 2016-9-18 15:16:12
 */

@Controller
@RequestMapping("/roleMenu")
public class RoleMenuController{

	@Autowired
	private IRoleMenuService service;

	@Autowired
	private IMenuService menuService;


	private static Logger log = LoggerFactory.getLogger(RoleMenuController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(int pageNo,int pageSize,Map<String, Object> params){
	    log.info("query,page={},pageSize={},params={}",pageSize,pageSize,params);
	    pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
	    PageInfo<RoleMenu> page =  service.queryPageByMap(params, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotal());
		data.put("rows", page.getList());
		return data;
	}
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public RoleMenu get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}
	
 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(@RequestBody RoleMenu bean){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }

	@RequestMapping(value = "/insertRoleMenus")
	@ResponseBody
	public CommonResult createRoleMenus(
			@RequestParam(value = "roleId") Long roleId,
			@RequestBody List<String> menuIds
			){
		log.info("RoleMenuController -> createRoleMenus params -> roleId = {}, menuIds = {} " , roleId , menuIds );
		try{
			//简单进行：先删后增 by ZKT
			int delOld = service.deleteByRoleId(roleId);
			log.info("RoleMenuController -> createRoleMenus -> delOldMenuRoleRealationship size = "+ delOld);
			// 批量新增
			List<RoleMenu> batchInsertRoleMenus = new ArrayList<RoleMenu>();

			for (String menuId : menuIds){
				Menu menu = menuService.get(Long.parseLong(menuId));
				//父级ID没有传，根据子级来添加
				Long parentId = menu.getParentMenuId();
				if (parentId != null){
					List<RoleMenu> rms = service.queryByRoleMenu(roleId,parentId);
					// 父
					if (rms != null && rms.size() == 0){
						RoleMenu bean = new RoleMenu();
						bean.setRoleId(roleId);
						bean.setMenuId(parentId);
//						batchInsertRoleMenus.add(bean);
						service.insert(bean);
					}
				}
				RoleMenu rm = new RoleMenu();
				rm.setRoleId(roleId);
				rm.setMenuId(Long.parseLong(menuId));
				batchInsertRoleMenus.add(rm);
			}
			service.batchInsert(batchInsertRoleMenus);
			return CommonResult.success("更新成功");
		}catch(Exception e){
			log.error("更新失败",e);
			return CommonResult.fail("更新失败");
		}

	}
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(@RequestBody RoleMenu bean){
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
