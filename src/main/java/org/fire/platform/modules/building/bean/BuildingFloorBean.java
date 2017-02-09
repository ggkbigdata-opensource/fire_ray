package org.fire.platform.modules.building.bean;

import org.fire.platform.modules.building.domain.BuildingFloor;

public class BuildingFloorBean extends BuildingFloor{

	
	String buildingName;
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
}
