package org.fire.platform.modules.building.domain;

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
public class BuildingPlace extends BaseEntity{
	// Fields
		private Long id;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private Long buildingId;
		private String name;
		private String placeType;
		private String address;
		private Double coverArea;
		private Integer capacity;
		private Integer fireManNum;
		private Integer isFireQualified;
		private String fireContact;
		private String firePhone;
		private String remark;
	public BuildingPlace() {
		this.clear();
	}
	public BuildingPlace(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		districtId=null;
		streetId=null;
		blockId=null;
		buildingId=null;
		name=null;
		placeType=null;
		address=null;
		coverArea=null;
		capacity=null;
		fireManNum=null;
		isFireQualified=null;
		fireContact=null;
		firePhone=null;
		remark=null;
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
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getPlaceType(){
			return placeType;
		}
		public void setPlaceType(String placeType){
			this.placeType=placeType;
		}
		public String getAddress(){
			return address;
		}
		public void setAddress(String address){
			this.address=address;
		}
		public Double getCoverArea(){
			return coverArea;
		}
		public void setCoverArea(Double coverArea){
			this.coverArea=coverArea;
		}
		public Integer getCapacity(){
			return capacity;
		}
		public void setCapacity(Integer capacity){
			this.capacity=capacity;
		}
		public Integer getFireManNum(){
			return fireManNum;
		}
		public void setFireManNum(Integer fireManNum){
			this.fireManNum=fireManNum;
		}
		public Integer getIsFireQualified(){
			return isFireQualified;
		}
		public void setIsFireQualified(Integer isFireQualified){
			this.isFireQualified=isFireQualified;
		}
		public String getFireContact(){
			return fireContact;
		}
		public void setFireContact(String fireContact){
			this.fireContact=fireContact;
		}
		public String getFirePhone(){
			return firePhone;
		}
		public void setFirePhone(String firePhone){
			this.firePhone=firePhone;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuildingPlace=[");
		builder.append("id="+id+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("name="+name+",");
		builder.append("placeType="+placeType+",");
		builder.append("address="+address+",");
		builder.append("coverArea="+coverArea+",");
		builder.append("capacity="+capacity+",");
		builder.append("fireManNum="+fireManNum+",");
		builder.append("isFireQualified="+isFireQualified+",");
		builder.append("fireContact="+fireContact+",");
		builder.append("firePhone="+firePhone+",");
		builder.append("remark="+remark+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
