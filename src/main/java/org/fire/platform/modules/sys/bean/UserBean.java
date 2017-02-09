package org.fire.platform.modules.sys.bean;

import org.fire.platform.modules.sys.domain.User;

public class UserBean extends User{

	
	private String disctrictName;
	
	public String getDisctrictName() {
		return disctrictName;
	}
	
	public void setDisctrictName(String disctrictName) {
		this.disctrictName = disctrictName;
	}
}
