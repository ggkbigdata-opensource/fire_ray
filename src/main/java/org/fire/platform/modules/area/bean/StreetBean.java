package org.fire.platform.modules.area.bean;

import org.fire.platform.modules.area.domain.Street;

/**
 * @author Max
 *
 */
public class StreetBean  extends Street{
	
	String disctrictName;
	
	public String getDisctrictName() {
		return disctrictName;
	}
	
	public void setDisctrictName(String disctrictName) {
		this.disctrictName = disctrictName;
	}
	
}
