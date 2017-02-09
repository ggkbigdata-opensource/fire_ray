package org.fire.platform.modules.check.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:01
 */
 
@SuppressWarnings("serial")
public class CheckReport extends BaseEntity{
	// Fields
		private Long id;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private Long buildingId;
		private String placeName;
		private String name;
		private String code;
		private String reportImage;
		private String reportType;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date pubTime;
		private Long reporter;
		private String reporterPhone;
		private Integer isPass;
		private Integer unpassNum;
		private String riskIndex;
		private String remark;
		private String reportFileUrl;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
		private Double score;
	public CheckReport() {
		this.clear();
	}
	public CheckReport(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		districtId=null;
		streetId=null;
		blockId=null;
		buildingId=null;
		placeName=null;
		name=null;
		code=null;
		reportImage=null;
		reportType=null;
		pubTime=null;
		reporter=null;
		reporterPhone=null;
		isPass=null;
		unpassNum=null;
		riskIndex=null;
		remark=null;
		reportFileUrl=null;
		modUser=null;
		modDate=null;
		score=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getDistrictId(){
			return districtId;
		}
		public void setDistrictId(Long districtId){
			this.districtId=districtId;
		}
		public Long getStreetId(){
			return streetId;
		}
		public void setStreetId(Long streetId){
			this.streetId=streetId;
		}
		public Long getBlockId(){
			return blockId;
		}
		public void setBlockId(Long blockId){
			this.blockId=blockId;
		}
		public Long getBuildingId(){
			return buildingId;
		}
		public void setBuildingId(Long buildingId){
			this.buildingId=buildingId;
		}
		public String getPlaceName(){
			return placeName;
		}
		public void setPlaceName(String placeName){
			this.placeName=placeName;
		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
		}
		public String getReportImage(){
			return reportImage;
		}
		public void setReportImage(String reportImage){
			this.reportImage=reportImage;
		}
		public String getReportType(){
			return reportType;
		}
		public void setReportType(String reportType){
			this.reportType=reportType;
		}
		public Date getPubTime(){
			return pubTime;
		}
		public void setPubTime(Date pubTime){
			this.pubTime=pubTime;
		}
		public Long getReporter(){
			return reporter;
		}
		public void setReporter(Long reporter){
			this.reporter=reporter;
		}
		public String getReporterPhone(){
			return reporterPhone;
		}
		public void setReporterPhone(String reporterPhone){
			this.reporterPhone=reporterPhone;
		}
		public Integer getIsPass(){
			return isPass;
		}
		public void setIsPass(Integer isPass){
			this.isPass=isPass;
		}
		public Integer getUnpassNum(){
			return unpassNum;
		}
		public void setUnpassNum(Integer unpassNum){
			this.unpassNum=unpassNum;
		}
		public String getRiskIndex(){
			return riskIndex;
		}
		public void setRiskIndex(String riskIndex){
			this.riskIndex=riskIndex;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
		public String getReportFileUrl(){
			return reportFileUrl;
		}
		public void setReportFileUrl(String reportFileUrl){
			this.reportFileUrl=reportFileUrl;
		}
		public Long getModUser(){
			return modUser;
		}
		public void setModUser(Long modUser){
			this.modUser=modUser;
		}
		public Date getModDate(){
			return modDate;
		}
		public void setModDate(Date modDate){
			this.modDate=modDate;
		}
		public Double getScore(){
			return score;
		}
		public void setScore(Double score){
			this.score=score;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckReport=[");
		builder.append("id="+id+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("placeName="+placeName+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("reportImage="+reportImage+",");
		builder.append("reportType="+reportType+",");
		builder.append("pubTime="+pubTime+",");
		builder.append("reporter="+reporter+",");
		builder.append("reporterPhone="+reporterPhone+",");
		builder.append("isPass="+isPass+",");
		builder.append("unpassNum="+unpassNum+",");
		builder.append("riskIndex="+riskIndex+",");
		builder.append("remark="+remark+",");
		builder.append("reportFileUrl="+reportFileUrl+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("score="+score+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
