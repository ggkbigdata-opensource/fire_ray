package org.fire.platform.modules.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.bean.MenuBean;
import org.fire.platform.modules.sys.domain.Menu;
import org.fire.platform.modules.sys.domain.RoleMenu;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IMenuService;
import org.fire.platform.modules.sys.service.IRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:16:12
 */

@Controller
@RequestMapping("/menu")
public class MenuController{

	@Autowired
	private IMenuService service;
	
	@Autowired
	private IRoleMenuService roleMenuService;

	@Autowired
	private IMenuService menuService;
	
	private static Logger log = LoggerFactory.getLogger(MenuController.class);

	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(int pageNo,int pageSize,Map<String, Object> params){
	    log.info("query,page={},pageSize={},params={}",pageSize,pageSize,params);
	    pageNo = pageNo > 0 ? pageNo - 1 : pageNo;
	    PageInfo<Menu> page =  service.queryPageByMap(params, pageNo, pageSize);
	    Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", page.getTotal());
		data.put("rows", page.getList());
		return data;
	}

	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public Map<String, Object> queryAll(Map<String, Object> params){
		log.info("queryAll,params={}",params);
		List<MenuBean> lists =  service.queryBeanByMap(params);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("total", lists.size());
		data.put("rows", lists);
		return data;
	}
	
	@RequestMapping(value = "/queryAllByRoleId")
	@ResponseBody
	public CommonResult queryAllByRoleId(Map<String, Object> params, HttpSession session,	@RequestParam(required = false) String roleId){
	    log.info("queryAll,params={}",params);
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		if (user == null) {
			log.error("您还未登录，请登录！");
			return CommonResult.fail("您还未登录，请登录！");
		}
		 Map<String,Object> map = new HashMap<String,Object>();
		List<String> allMenusIds = new ArrayList<String>();
		if (roleId != null && StringUtils.hasText(roleId)){
			map.put("roleId", roleId);
			List<RoleMenu> roleMenus = this.roleMenuService.queryByMap(map);
			if(roleMenus != null && roleMenus.size() > 0){
				for (RoleMenu rm : roleMenus) {
					Long menuId = rm.getMenuId();
					Menu menu = menuService.get(menuId);
					// 编辑的时候如果还有子节点没有选中的，那父节点也不要被选中。easyui当子节点都选中的话，父节点会默认选中
					if (menu.getParentMenuId() != null){
						allMenusIds.add(rm.getMenuId()+"");
					}
				}
			}
		}
		List<Menu> menus = service.queryAll();
		EasyuiMenuTreeNode root = WebFrontHelper.buildMenuTreeForEasyuiTree(menus,allMenusIds);
		return CommonResult.success(root.getChildren());
	}

//	@RequestMapping(value = "/queryCurrentUserMneu")
//	@ResponseBody
//	public Map<String, Object>  queryCurrentUserMneu(HttpSession session){
//		log.info("queryCurrentUserMneu,session={}",session);
//		Map<String, Object> map = new HashMap<String, Object>();
//		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
//		Map<String, Object> data = new HashMap<String, Object>();
//		if (user == null){
//			log.error("新增失败，请登陆");
//			data.put("msg","新增失败，请登陆");
//			return data;
//		}
////		map.put("userId",user.getUid());
//		Long roleId = user.getRoleId();
//		Map<String,Object> roleMenuMap = new HashMap<String,Object>();
//		roleMenuMap.put("roleId", roleId);
//		List<RoleMenu> roleMenus = this.roleMenuService.queryByMap(roleMenuMap);
//		if( CollectionUtils.isEmpty(roleMenus)){
//			log.error("您暂无查看权限");
//			data.put("msg","您暂无查看权限，请联系系统管理员");
//			return data;
//		}
//
//		List<MenuBean> allMenusByRole = new ArrayList<MenuBean>();
//		for (RoleMenu rm : roleMenus) {
//			MenuBean menuBean = this.menuService.getBean(rm.getMenuId());
//			if (menuBean != null) {
//				allMenusByRole.add(menuBean);
//			}
//		}
//		data.put("total", allMenusByRole.size());
//		data.put("rows", allMenusByRole);
//		return data;
//	}
	
	
	@RequestMapping(value = "/getData")
	@ResponseBody
	public Menu get(@RequestParam(value = "id") Long id){
	    log.info("get,id={}",id);
	    return 	service.get(id);
	}


 	 @RequestMapping(value = "/insertData")
	 @ResponseBody
	 public CommonResult create(Menu bean,String[] roleIds){
	     log.info("create,bean={}",bean);
	     try{
		    service.insert(bean);
			 if (roleIds != null && roleIds.length >0){
				 for (int i=0;i<roleIds.length;i++){
					 RoleMenu roleMenu = new RoleMenu();
					 roleMenu.setMenuId(bean.getMenuId());
					 roleMenu.setRoleId(Long.parseLong(roleIds[i]));
					 roleMenuService.insert(roleMenu);
				 }
			 }
		    return CommonResult.success("新增成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("新增失败");
		 }
		
	 }
	 
	 @RequestMapping(value = "/updateData")
	 @ResponseBody
	 public CommonResult update(Menu bean){
	     log.info("update,bean={}",bean);
	     try{
		    service.update(bean);
		    return CommonResult.success("修改成功");
		 }catch(Exception e){
		    log.error("新增失败",e); 
		    return CommonResult.fail("修改失败");
		 }
		
	 }

	/**
	 * 如果有子节点，也一并删除
	 * @param id
	 * @return
     */
	 @RequestMapping(value = "/deleteData")
	 @ResponseBody
	 public CommonResult delete(@RequestParam(value = "id") Long id){
	     log.info("delete,id={}",id);
	     try{
			 delAllMenuByParentId(id);
			 service.delete(id);
		    return CommonResult.success("删除成功");
		 }catch(Exception e){
		    log.error("删除失败",e); 
		    return CommonResult.fail("删除失败");
		 }
	 }

	/**
	 * 递归
	 * @param menuId
     */
	public void delAllMenuByParentId(Long menuId) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentMenuId",menuId+"");
		List<Menu> lists = service.queryByMap(params);
		if (lists != null && lists.size() > 0){
			for(Menu menu :lists){
				service.delete(menu.getMenuId());
				delAllMenuByParentId(menu.getMenuId());
			}
		}
	}

}
