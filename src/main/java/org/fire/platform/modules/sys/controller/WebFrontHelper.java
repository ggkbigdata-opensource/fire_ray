package org.fire.platform.modules.sys.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.fire.platform.modules.sys.domain.Menu;

public class WebFrontHelper {
	
	public static EasyuiMenuTreeNode buildMenuTreeForEasyuiTree(List<Menu> menus) {
		return buildMenuTreeForEasyuiTree(menus, null);
	}
	
	public static EasyuiMenuTreeNode buildMenuTreeForEasyuiTree(List<Menu> menus, List<String> menuIdsForChecked) {
		
		Map<String, EasyuiMenuTreeNode> map = new LinkedHashMap<String, EasyuiMenuTreeNode>();
		EasyuiMenuTreeNode root = new EasyuiMenuTreeNode();
		root.setId("root");
		root.setText("Root");
		map.put("root", root);
		
		for (Menu menu : menus) {
			EasyuiMenuTreeNode node = new EasyuiMenuTreeNode();
			node.setId(menu.getMenuId().toString());
			node.setText(menu.getMenuName());
			node.setMenuname(menu.getMenuName());
			node.setMenuid(menu.getMenuId().toString());
			if( menu.getParentMenuId() != null ){
				node.setParentId(menu.getParentMenuId().toString());
				node.setUrl(menu.getMenuUrl());
				node.setIcon("icon-nav");
			}else{
				node.setParentId(null);
				node.setIcon("icon-sys");
			}
			
//			node.setAttributes(menu);
			
			map.put(node.getId(), node);
		}
		
		for (Map.Entry<String, EasyuiMenuTreeNode> entry : map.entrySet()) {
			EasyuiMenuTreeNode node = entry.getValue();
			
			if ("root".equals(node.getId())) {
				continue;
			}
			
			String parentId = node.getParentId();
			
			if (parentId == null || "".equals(parentId)) {
				parentId = "root";
			}

			map.get(parentId).addChild(node);
		}
		
				
		if (menuIdsForChecked != null && menuIdsForChecked.size() > 0) {
			for (Map.Entry<String, EasyuiMenuTreeNode> entry : map.entrySet()) {
				EasyuiMenuTreeNode node = entry.getValue();
			
				if (menuIdsForChecked.contains(node.getId())) {
					node.setChecked(true);
				}
			}
		}

		return root;
	}
	
}
