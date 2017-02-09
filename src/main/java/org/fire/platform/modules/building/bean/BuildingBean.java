package org.fire.platform.modules.building.bean;

import org.fire.platform.modules.building.domain.Building;

public class BuildingBean extends Building{
	
    String streetName;
    
    String blockName;
    
    String districtName;
    
    String  buildingClassName;
    
    String  baseLevelName;
    
    String finishTimeString;

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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBuildingClassName() {
		return buildingClassName;
	}

	public void setBuildingClassName(String buildingClassName) {
		this.buildingClassName = buildingClassName;
	}

	public String getBaseLevelName() {
		return baseLevelName;
	}

	public void setBaseLevelName(String baseLevelName) {
		this.baseLevelName = baseLevelName;
	}

	public String getFinishTimeString() {
		return finishTimeString;
	}

	public void setFinishTimeString(String finishTimeString) {
		this.finishTimeString = finishTimeString;
	}

}
