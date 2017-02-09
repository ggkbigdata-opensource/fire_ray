package org.fire.platform.modules.building.vo;

import java.util.List;

import org.fire.platform.modules.building.domain.BuildingFloor;
import org.fire.platform.modules.building.domain.BuildingFloorPanorama;

public class BuildingFloorVo {
	private BuildingFloor buildingFloor;
	private List<BuildingFloorPanorama> buildingFloorPanoramaList;
	public BuildingFloor getBuildingFloor() {
		return buildingFloor;
	}
	public void setBuildingFloor(BuildingFloor buildingFloor) {
		this.buildingFloor = buildingFloor;
	}
	public List<BuildingFloorPanorama> getBuildingFloorPanoramaList() {
		return buildingFloorPanoramaList;
	}
	public void setBuildingFloorPanoramaList(
			List<BuildingFloorPanorama> buildingFloorPanoramaList) {
		this.buildingFloorPanoramaList = buildingFloorPanoramaList;
	}

}
