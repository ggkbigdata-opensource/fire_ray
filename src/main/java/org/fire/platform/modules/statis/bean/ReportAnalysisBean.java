package org.fire.platform.modules.statis.bean;

import java.util.Date;

import org.fire.platform.modules.statis.domain.ReportAnalysis;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReportAnalysisBean extends ReportAnalysis {

	
  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String streetName;
    
    String blockName;
    
    String districtName;
    
    String checkReportName;
    
    String riskLevelName;
    
    @JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
	Date pubTime;
    
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

	public String getCheckReportName() {
		return checkReportName;
	}

	public void setCheckReportName(String checkReportName) {
		this.checkReportName = checkReportName;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public String getRiskLevelName() {
		return riskLevelName;
	}

	public void setRiskLevelName(String riskLevelName) {
		this.riskLevelName = riskLevelName;
	}

}
