package org.fire.platform.modules.building.bean;

import org.fire.platform.modules.building.domain.BuildingPanorama;

public class BuildingPanoramaBean extends BuildingPanorama{

	String buildingName;
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
}
