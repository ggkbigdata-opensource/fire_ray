package org.fire.platform.modules.front.vo;

import org.fire.platform.modules.area.vo.Latlng;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AreaRiskLevelVo {
	private String areaCode;
	private String areaName;
	private Latlng center;
	@JsonIgnore
	private String areaCenter;
	private Integer riskLevel = 1;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Latlng getCenter() {
		return center;
	}
	public void setCenter(Latlng center) {
		this.center = center;
	}

	public Integer getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(Integer riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getAreaCenter() {
		return areaCenter;
	}
	public void setAreaCenter(String areaCenter) {
		this.areaCenter = areaCenter;
	}

}
