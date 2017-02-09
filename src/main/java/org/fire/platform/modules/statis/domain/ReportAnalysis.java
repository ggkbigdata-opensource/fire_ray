package org.fire.platform.modules.statis.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-17 19:53:50
 */
 
@SuppressWarnings("serial")
public class ReportAnalysis extends BaseEntity{
	// Fields
		private Long id;
		private Long reportId;
		private Double totalUnqualified;
		private String riskLevel;
		private Integer facilitiesUnqualified;
		private Double facilitiesUnqualifiedRatio;
		private Integer evacuateUnqualified;
		private Double evacuateUnqualifiedRatio;
		private Integer manageUnqualified;
		private Double manageUnqualifiedRatio;
		private Integer dangerUnqualified;
		private Double dangerUnqualifiedRatio;
		private Integer importantUnqualified;
		private Double importantUnqualifiedRatio;
		private Integer checkUnqualified;
		private Double checkUnqualifiedRatio;
		private Long creator;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date createTime;
	public ReportAnalysis() {
		this.clear();
	}
	public ReportAnalysis(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		reportId=null;
		totalUnqualified=null;
		riskLevel=null;
		facilitiesUnqualified=null;
		facilitiesUnqualifiedRatio=null;
		evacuateUnqualified=null;
		evacuateUnqualifiedRatio=null;
		manageUnqualified=null;
		manageUnqualifiedRatio=null;
		dangerUnqualified=null;
		dangerUnqualifiedRatio=null;
		importantUnqualified=null;
		importantUnqualifiedRatio=null;
		checkUnqualified=null;
		checkUnqualifiedRatio=null;
		creator=null;
		createTime=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getReportId(){
			return reportId;
		}
		public void setReportId(Long reportId){
			this.reportId=reportId;
		}
		public Double getTotalUnqualified(){
			return totalUnqualified;
		}
		public void setTotalUnqualified(Double totalUnqualified){
			this.totalUnqualified=totalUnqualified;
		}
		public String getRiskLevel(){
			return riskLevel;
		}
		public void setRiskLevel(String riskLevel){
			this.riskLevel=riskLevel;
		}
		public Integer getFacilitiesUnqualified(){
			return facilitiesUnqualified;
		}
		public void setFacilitiesUnqualified(Integer facilitiesUnqualified){
			this.facilitiesUnqualified=facilitiesUnqualified;
		}
		public Double getFacilitiesUnqualifiedRatio(){
			return facilitiesUnqualifiedRatio;
		}
		public void setFacilitiesUnqualifiedRatio(Double facilitiesUnqualifiedRatio){
			this.facilitiesUnqualifiedRatio=facilitiesUnqualifiedRatio;
		}
		public Integer getEvacuateUnqualified(){
			return evacuateUnqualified;
		}
		public void setEvacuateUnqualified(Integer evacuateUnqualified){
			this.evacuateUnqualified=evacuateUnqualified;
		}
		public Double getEvacuateUnqualifiedRatio(){
			return evacuateUnqualifiedRatio;
		}
		public void setEvacuateUnqualifiedRatio(Double evacuateUnqualifiedRatio){
			this.evacuateUnqualifiedRatio=evacuateUnqualifiedRatio;
		}
		public Integer getManageUnqualified(){
			return manageUnqualified;
		}
		public void setManageUnqualified(Integer manageUnqualified){
			this.manageUnqualified=manageUnqualified;
		}
		public Double getManageUnqualifiedRatio(){
			return manageUnqualifiedRatio;
		}
		public void setManageUnqualifiedRatio(Double manageUnqualifiedRatio){
			this.manageUnqualifiedRatio=manageUnqualifiedRatio;
		}
		public Integer getDangerUnqualified(){
			return dangerUnqualified;
		}
		public void setDangerUnqualified(Integer dangerUnqualified){
			this.dangerUnqualified=dangerUnqualified;
		}
		public Double getDangerUnqualifiedRatio(){
			return dangerUnqualifiedRatio;
		}
		public void setDangerUnqualifiedRatio(Double dangerUnqualifiedRatio){
			this.dangerUnqualifiedRatio=dangerUnqualifiedRatio;
		}
		public Integer getImportantUnqualified(){
			return importantUnqualified;
		}
		public void setImportantUnqualified(Integer importantUnqualified){
			this.importantUnqualified=importantUnqualified;
		}
		public Double getImportantUnqualifiedRatio(){
			return importantUnqualifiedRatio;
		}
		public void setImportantUnqualifiedRatio(Double importantUnqualifiedRatio){
			this.importantUnqualifiedRatio=importantUnqualifiedRatio;
		}
		public Integer getCheckUnqualified(){
			return checkUnqualified;
		}
		public void setCheckUnqualified(Integer checkUnqualified){
			this.checkUnqualified=checkUnqualified;
		}
		public Double getCheckUnqualifiedRatio(){
			return checkUnqualifiedRatio;
		}
		public void setCheckUnqualifiedRatio(Double checkUnqualifiedRatio){
			this.checkUnqualifiedRatio=checkUnqualifiedRatio;
		}
		public Long getCreator(){
			return creator;
		}
		public void setCreator(Long creator){
			this.creator=creator;
		}
		public Date getCreateTime(){
			return createTime;
		}
		public void setCreateTime(Date createTime){
			this.createTime=createTime;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReportAnalysis=[");
		builder.append("id="+id+",");
		builder.append("reportId="+reportId+",");
		builder.append("totalUnqualified="+totalUnqualified+",");
		builder.append("riskLevel="+riskLevel+",");
		builder.append("facilitiesUnqualified="+facilitiesUnqualified+",");
		builder.append("facilitiesUnqualifiedRatio="+facilitiesUnqualifiedRatio+",");
		builder.append("evacuateUnqualified="+evacuateUnqualified+",");
		builder.append("evacuateUnqualifiedRatio="+evacuateUnqualifiedRatio+",");
		builder.append("manageUnqualified="+manageUnqualified+",");
		builder.append("manageUnqualifiedRatio="+manageUnqualifiedRatio+",");
		builder.append("dangerUnqualified="+dangerUnqualified+",");
		builder.append("dangerUnqualifiedRatio="+dangerUnqualifiedRatio+",");
		builder.append("importantUnqualified="+importantUnqualified+",");
		builder.append("importantUnqualifiedRatio="+importantUnqualifiedRatio+",");
		builder.append("checkUnqualified="+checkUnqualified+",");
		builder.append("checkUnqualifiedRatio="+checkUnqualifiedRatio+",");
		builder.append("creator="+creator+",");
		builder.append("createTime="+createTime+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
