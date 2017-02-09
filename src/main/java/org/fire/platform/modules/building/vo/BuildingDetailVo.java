package org.fire.platform.modules.building.vo;

import java.util.List;

import org.fire.platform.modules.building.domain.BuildingPanorama;
import org.fire.platform.modules.check.vo.CheckReportVo;
import org.fire.platform.modules.event.vo.FireEventVo;
import org.fire.platform.modules.event.vo.PunishEventVo;

public class BuildingDetailVo {
	private BuildingVo buildingInfo;
	private List<BuildingPanorama> buildingPanoramaList;
	private List<BuildingFloorVo> buildingFloorList;
	private List<CheckReportVo> reportList;
	private List<PunishEventVo> punishList;
	
	private List<FireEventVo> fireList;
	
	private Integer concernFlag = 0; //默认未关注
	
	public BuildingVo getBuildingInfo() {
		return buildingInfo;
	}
	public void setBuildingInfo(BuildingVo buildingInfo) {
		this.buildingInfo = buildingInfo;
	}
	public List<BuildingPanorama> getBuildingPanoramaList() {
		return buildingPanoramaList;
	}
	public void setBuildingPanoramaList(List<BuildingPanorama> buildingPanoramaList) {
		this.buildingPanoramaList = buildingPanoramaList;
	}
	public List<BuildingFloorVo> getBuildingFloorList() {
		return buildingFloorList;
	}
	public void setBuildingFloorList(List<BuildingFloorVo> buildingFloorList) {
		this.buildingFloorList = buildingFloorList;
	}
	public List<CheckReportVo> getReportList() {
		return reportList;
	}
	public void setReportList(List<CheckReportVo> reportList) {
		this.reportList = reportList;
	}
	public List<PunishEventVo> getPunishList() {
		return punishList;
	}
	public void setPunishList(List<PunishEventVo> punishList) {
		this.punishList = punishList;
	}
	
	public List<FireEventVo> getFireList() {
		return fireList;
	}
	public void setFireList(List<FireEventVo> fireList) {
		this.fireList = fireList;
	}
	public Integer getConcernFlag() {
		return concernFlag;
	}
	public void setConcernFlag(Integer concernFlag) {
		this.concernFlag = concernFlag;
	}
	
	
	
	

}
