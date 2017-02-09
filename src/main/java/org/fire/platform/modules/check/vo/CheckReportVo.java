package org.fire.platform.modules.check.vo;

import java.util.Date;

import org.fire.platform.common.serialize.DictTransferAnnotation;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CheckReportVo {
	private Long id;
	private String placeName;
	private String name;
	@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
	private Date pubTime;
	private String streetName;
	@DictTransferAnnotation(param="report_type")
	private String reportType;
	private Integer isPass;
	private Integer unpassNum;
	private Double score;
	@DictTransferAnnotation(param="risk_level")
	private String riskIndex;
	
	@DictTransferAnnotation(param="building_level")
	private String buildingLevel;
	@DictTransferAnnotation(param="building_class")
	private String buildingClass;
	
	private String reportFileUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public Integer getUnpassNum() {
		return unpassNum;
	}
	public void setUnpassNum(Integer unpassNum) {
		this.unpassNum = unpassNum;
	}
	public String getRiskIndex() {
		return riskIndex;
	}
	public void setRiskIndex(String riskIndex) {
		this.riskIndex = riskIndex;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportFileUrl() {
		return reportFileUrl;
	}
	public void setReportFileUrl(String reportFileUrl) {
		this.reportFileUrl = reportFileUrl;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getBuildingLevel() {
		return buildingLevel;
	}
	public void setBuildingLevel(String buildingLevel) {
		this.buildingLevel = buildingLevel;
	}
	public String getBuildingClass() {
		return buildingClass;
	}
	public void setBuildingClass(String buildingClass) {
		this.buildingClass = buildingClass;
	}
	
	
	
	

}
