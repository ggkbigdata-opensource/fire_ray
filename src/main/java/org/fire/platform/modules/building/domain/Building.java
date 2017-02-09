package org.fire.platform.modules.building.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:02
 */
 
@SuppressWarnings("serial")
public class Building extends BaseEntity{
	// Fields
		private Long id;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private Double longitude;
		private Double latitude;
		private String geoHashCode;
		private String thumbImg;
		private String modelImg;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date finishTime;
		private Integer state;
		private String baseName;
		private String baseCode;
		private String baseBuildingClass;
		private String baseLevel;
		private String baseAddress;
		private String baseRemark;
		private String baseMark;
		private String fireManager;
		private String fireManagerPhone;
		private String fireContact;
		private String fireContactPhone;
		private Double conBuildArea;
		private String conType;
		private Integer conBuildHight;
		private Double conCoverArea;
		private Integer conFloors;
		private Integer conUnderFloors;
	public Building() {
		this.clear();
	}
	public Building(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		districtId=null;
		streetId=null;
		blockId=null;
		longitude=null;
		latitude=null;
		geoHashCode=null;
		thumbImg=null;
		modelImg=null;
		finishTime=null;
		state=null;
		baseName=null;
		baseCode=null;
		baseBuildingClass=null;
		baseLevel=null;
		baseAddress=null;
		baseRemark=null;
		baseMark=null;
		fireManager=null;
		fireManagerPhone=null;
		fireContact=null;
		fireContactPhone=null;
		conBuildArea=null;
		conType=null;
		conBuildHight=null;
		conCoverArea=null;
		conFloors=null;
		conUnderFloors=null;
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
		public Double getLongitude(){
			return longitude;
		}
		public void setLongitude(Double longitude){
			this.longitude=longitude;
		}
		public Double getLatitude(){
			return latitude;
		}
		public void setLatitude(Double latitude){
			this.latitude=latitude;
		}
		public String getGeoHashCode(){
			return geoHashCode;
		}
		public void setGeoHashCode(String geoHashCode){
			this.geoHashCode=geoHashCode;
		}
		public String getThumbImg(){
			return thumbImg;
		}
		public void setThumbImg(String thumbImg){
			this.thumbImg=thumbImg;
		}
		public String getModelImg(){
			return modelImg;
		}
		public void setModelImg(String modelImg){
			this.modelImg=modelImg;
		}
		public Date getFinishTime(){
			return finishTime;
		}
		public void setFinishTime(Date finishTime){
			this.finishTime=finishTime;
		}
		public Integer getState(){
			return state;
		}
		public void setState(Integer state){
			this.state=state;
		}
		public String getBaseName(){
			return baseName;
		}
		public void setBaseName(String baseName){
			this.baseName=baseName;
		}
		public String getBaseCode(){
			return baseCode;
		}
		public void setBaseCode(String baseCode){
			this.baseCode=baseCode;
		}
		public String getBaseBuildingClass(){
			return baseBuildingClass;
		}
		public void setBaseBuildingClass(String baseBuildingClass){
			this.baseBuildingClass=baseBuildingClass;
		}
		public String getBaseLevel(){
			return baseLevel;
		}
		public void setBaseLevel(String baseLevel){
			this.baseLevel=baseLevel;
		}
		public String getBaseAddress(){
			return baseAddress;
		}
		public void setBaseAddress(String baseAddress){
			this.baseAddress=baseAddress;
		}
		public String getBaseRemark(){
			return baseRemark;
		}
		public void setBaseRemark(String baseRemark){
			this.baseRemark=baseRemark;
		}
		public String getBaseMark(){
			return baseMark;
		}
		public void setBaseMark(String baseMark){
			this.baseMark=baseMark;
		}
		public String getFireManager(){
			return fireManager;
		}
		public void setFireManager(String fireManager){
			this.fireManager=fireManager;
		}
		public String getFireManagerPhone(){
			return fireManagerPhone;
		}
		public void setFireManagerPhone(String fireManagerPhone){
			this.fireManagerPhone=fireManagerPhone;
		}
		public String getFireContact(){
			return fireContact;
		}
		public void setFireContact(String fireContact){
			this.fireContact=fireContact;
		}
		public String getFireContactPhone(){
			return fireContactPhone;
		}
		public void setFireContactPhone(String fireContactPhone){
			this.fireContactPhone=fireContactPhone;
		}
		public Double getConBuildArea(){
			return conBuildArea;
		}
		public void setConBuildArea(Double conBuildArea){
			this.conBuildArea=conBuildArea;
		}
		public String getConType(){
			return conType;
		}
		public void setConType(String conType){
			this.conType=conType;
		}
		public Integer getConBuildHight(){
			return conBuildHight;
		}
		public void setConBuildHight(Integer conBuildHight){
			this.conBuildHight=conBuildHight;
		}
		public Double getConCoverArea(){
			return conCoverArea;
		}
		public void setConCoverArea(Double conCoverArea){
			this.conCoverArea=conCoverArea;
		}
		public Integer getConFloors(){
			return conFloors;
		}
		public void setConFloors(Integer conFloors){
			this.conFloors=conFloors;
		}
		public Integer getConUnderFloors(){
			return conUnderFloors;
		}
		public void setConUnderFloors(Integer conUnderFloors){
			this.conUnderFloors=conUnderFloors;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Building=[");
		builder.append("id="+id+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("longitude="+longitude+",");
		builder.append("latitude="+latitude+",");
		builder.append("geoHashCode="+geoHashCode+",");
		builder.append("thumbImg="+thumbImg+",");
		builder.append("modelImg="+modelImg+",");
		builder.append("finishTime="+finishTime+",");
		builder.append("state="+state+",");
		builder.append("baseName="+baseName+",");
		builder.append("baseCode="+baseCode+",");
		builder.append("baseBuildingClass="+baseBuildingClass+",");
		builder.append("baseLevel="+baseLevel+",");
		builder.append("baseAddress="+baseAddress+",");
		builder.append("baseRemark="+baseRemark+",");
		builder.append("baseMark="+baseMark+",");
		builder.append("fireManager="+fireManager+",");
		builder.append("fireManagerPhone="+fireManagerPhone+",");
		builder.append("fireContact="+fireContact+",");
		builder.append("fireContactPhone="+fireContactPhone+",");
		builder.append("conBuildArea="+conBuildArea+",");
		builder.append("conType="+conType+",");
		builder.append("conBuildHight="+conBuildHight+",");
		builder.append("conCoverArea="+conCoverArea+",");
		builder.append("conFloors="+conFloors+",");
		builder.append("conUnderFloors="+conUnderFloors+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
