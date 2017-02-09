package org.fire.platform.modules.check.vo;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.check.domain.CheckItemResultBak;
import org.fire.platform.modules.check.domain.CheckReport;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/10/27 027
 * Time: 14:44
 */
public class CheckReportDetailVo extends CheckReport{

    private String streetName;
    @DictTransferAnnotation(param="report_type")
    private String reportType;
    @DictTransferAnnotation(param="building_level")
    private String buildingLevel;
    @DictTransferAnnotation(param="building_class")
    private String buildingClass;
    @DictTransferAnnotation(param="risk_level")
    private String riskIndex;
    private List<CheckItemResultBak> itemLs;
    private List<CheckReportVo> relatReportList;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String getReportType() {
        return reportType;
    }

    @Override
    public void setReportType(String reportType) {
        this.reportType = reportType;
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

    public List<CheckItemResultBak> getItemLs() {
        return itemLs;
    }

    public void setItemLs(List<CheckItemResultBak> itemLs) {
        this.itemLs = itemLs;
    }

    public List<CheckReportVo> getRelatReportList() {
        return relatReportList;
    }

    public void setRelatReportList(List<CheckReportVo> relatReportList) {
        this.relatReportList = relatReportList;
    }

	public String getRiskIndex() {
		return riskIndex;
	}

	public void setRiskIndex(String riskIndex) {
		this.riskIndex = riskIndex;
	}
    
}
