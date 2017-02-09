package org.fire.platform.modules.building.bean;

import org.fire.platform.modules.building.domain.BuildingFloorPanorama;

public class BuildingFloorPanoramaBean extends BuildingFloorPanorama{

	
	String buildingFloorName;
	
	public void setBuildingFloorName(String buildingFloorName) {
		this.buildingFloorName = buildingFloorName;
	}
	
	public String getBuildingFloorName() {
		return buildingFloorName;
	}
}
