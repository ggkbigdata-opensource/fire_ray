package org.fire.platform.modules.area.bean;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
public class EasyuiAreaTreeNode {
	
	private String id;
	private String text;
	private String iconCls;
	private String parentId;
	private List<EasyuiAreaTreeNode> children = new ArrayList<EasyuiAreaTreeNode>();
	private boolean checked;
	
	private Area attributes;
	
	public void addChild(EasyuiAreaTreeNode node) {
		if (! this.children.contains(node)) {
			this.children.add(node);
		}
	}
	
	public boolean isLeaf() {
		return this.children.isEmpty();
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
	public List<EasyuiAreaTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EasyuiAreaTreeNode> children) {
		this.children = children;
	}

	public Area getAttributes() {
		return attributes;
	}

	public void setAttributes(Area attributes) {
		this.attributes = attributes;
	}
}
