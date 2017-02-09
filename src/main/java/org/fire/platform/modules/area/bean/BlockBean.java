package org.fire.platform.modules.area.bean;

import org.fire.platform.modules.area.domain.Block;

public class BlockBean extends Block {
	
	String streetName;
	
	String typeName;
	
	String districtName;
	
	public String getStreetName() {
		return streetName;
	}
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	
}
