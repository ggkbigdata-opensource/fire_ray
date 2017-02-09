package org.fire.platform.modules.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.App;
import org.fire.platform.common.base.CommonResult;
import org.fire.platform.modules.sys.domain.Menu;
import org.fire.platform.modules.sys.domain.RoleMenu;
import org.fire.platform.modules.sys.domain.User;
import org.fire.platform.modules.sys.service.IMenuService;
import org.fire.platform.modules.sys.service.IRoleMenuService;
import org.fire.platform.modules.sys.service.IUserService;
import org.fire.platform.util.AppHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private IUserService userService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleMenuService roleMenuService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginPage(HttpSession session, Model model) {
		User user = (User) session.getAttribute(App.USER_SESSION_KEY);
		
		if (user == null) {			
			return "login";
		}
		
		/**
		Long roleId = user.getRoleId();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		List<RoleMenu> roleMenus = this.roleMenuService.queryByMap(map);
		if( CollectionUtils.isEmpty(roleMenus)){
			return "grant-tips";
		}
		**/
		
		//当前是查询所有菜单
		List<Menu> allMenus = this.menuService.queryAll();
		
		EasyuiMenuTreeNode root = WebFrontHelper.buildMenuTreeForEasyuiTree(allMenus);
		
		model.addAttribute("treeJson", new Gson().toJson(root.getChildren()));
		
		return "main";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public CommonResult login(
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "username", required=true) String username,
			@RequestParam(value = "password", required=true) String password,
			HttpSession session) throws UnsupportedEncodingException {
		
		password = AppHelper.encryptPassword(password);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password", password);
		List<User> users = this.userService.queryByMap(map);
		
		if( CollectionUtils.isEmpty(users)){
			return CommonResult.fail("用户名密码不匹配");
		}
		
		User user = users.get(0);
		logger.info("登陆成功：{}", user);
		session.setAttribute(App.USER_SESSION_KEY, user);
		
		Long roleId = user.getRoleId();
		Map<String,Object> roleMenuMap = new HashMap<String,Object>();
		roleMenuMap.put("roleId", roleId);
		List<RoleMenu> roleMenus = this.roleMenuService.queryByMap(roleMenuMap);
		if( CollectionUtils.isEmpty(roleMenus)){
			logger.error("您暂无查看权限");
			return CommonResult.fail("您暂无查看权限");
		}
		
		List<Menu> allMenusByRole = new ArrayList<Menu>();
		for (RoleMenu rm : roleMenus) {
			Menu menu = this.menuService.get(rm.getMenuId());
			if (menu != null) {
				allMenusByRole.add(menu);
			}
		}
		
		EasyuiMenuTreeNode root = WebFrontHelper.buildMenuTreeForEasyuiTree(allMenusByRole);
		
		String menutTreeJson = new Gson().toJson(root.getChildren());
		session.setAttribute("fire_menu",URLEncoder.encode(menutTreeJson, "UTF-8"));

		return CommonResult.success(user.getUid()+"");
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if (session != null) {
			session.invalidate();
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("checkSession")
	@ResponseBody
	public CommonResult checkSession(HttpSession session) {
		
		if (session.getAttribute(App.USER_SESSION_KEY) != null) {
			return CommonResult.success();
		}
		
		return CommonResult.fail();
	}
	
}
