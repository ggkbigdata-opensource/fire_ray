package org.fire.platform.modules.sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.fire.platform.modules.sys.domain.Menu;

public class EasyuiMenuTreeNode {
	
	private String id;
	private String text;
	private String iconCls;
	private String parentId;
	private List<EasyuiMenuTreeNode> children = new ArrayList<EasyuiMenuTreeNode>();
	private boolean checked;
	
	private Menu attributes;
	
	// add by wgm
	private String menuid;

	private String menuname;
	
	private String icon;
	
	private String url;
	
	public void addChild(EasyuiMenuTreeNode node) {
		if (! this.children.contains(node)) {
			this.children.add(node);
		}
	}
	
	public boolean isLeaf() {
		return this.children.isEmpty();
	}
	
	public Menu getAttributes() {
		return attributes;
	}

	public void setAttributes(Menu attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public List<EasyuiMenuTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyuiMenuTreeNode> children) {
		this.children = children;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
