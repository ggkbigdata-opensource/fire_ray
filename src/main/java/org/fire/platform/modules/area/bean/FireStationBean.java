package org.fire.platform.modules.area.bean;

import org.fire.platform.modules.area.domain.FireStation;

public class FireStationBean extends FireStation{

	
	   String districtName;
	   
	   String streetName;
	    
	   String blockName;

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	    
	
}
