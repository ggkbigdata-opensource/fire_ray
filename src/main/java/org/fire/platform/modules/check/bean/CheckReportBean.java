package org.fire.platform.modules.check.bean;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.check.domain.CheckReport;

public class CheckReportBean extends CheckReport{
	
    String streetName;
    
    String blockName;
    
    String districtName;
    
    @DictTransferAnnotation(param = "report_risk_index")
    String riskIndex;
    
    @DictTransferAnnotation(param = "report_type")
    String reportType;
    
    String pubTimeString;
    

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

	public String getPubTimeString() {
		return pubTimeString;
	}

	public void setPubTimeString(String pubTimeString) {
		this.pubTimeString = pubTimeString;
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

}
