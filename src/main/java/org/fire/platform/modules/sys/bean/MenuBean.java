package org.fire.platform.modules.sys.bean;

import org.fire.platform.modules.sys.domain.Menu;

public class MenuBean extends Menu{

	
	private String   iconCls;
	
	private String _parentId;
	
	private String state;

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
