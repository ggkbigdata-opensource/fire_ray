package org.fire.platform.modules.building.vo;

import java.io.Serializable;

import org.fire.platform.common.serialize.DictTransferAnnotation;

public class BuildingVo implements Serializable{
	private Long buildingId;
	private String streetName;
	private String blockName;
	
	private String name;
	private String thumbImg;
	private String modelImg;
	private String address;
	
    
	private Double longitude;
	private Double latitude;
	
	@DictTransferAnnotation(param="building_class")
	private String buildingClass;
	@DictTransferAnnotation(param="building_type")
	private String buildingType;
	@DictTransferAnnotation(param="building_level")
	private String level;
	private String remark;
	
	private String fireManager;
	private String fireContact;
	private String fireContactPhone;
	
	@DictTransferAnnotation(param="construct_type")
	private String conType;
	@DictTransferAnnotation(param="construct_class")
	private String conClass;
	private Double height;
	private Double coverArea;
	private Integer floors;
	
	public Long getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbImg() {
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	public String getModelImg() {
		return modelImg;
	}
	public void setModelImg(String modelImg) {
		this.modelImg = modelImg;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public String getBuildingClass() {
		return buildingClass;
	}
	public void setBuildingClass(String buildingClass) {
		this.buildingClass = buildingClass;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFireManager() {
		return fireManager;
	}
	public void setFireManager(String fireManager) {
		this.fireManager = fireManager;
	}
	public String getFireContact() {
		return fireContact;
	}
	public void setFireContact(String fireContact) {
		this.fireContact = fireContact;
	}
	public String getFireContactPhone() {
		return fireContactPhone;
	}
	public void setFireContactPhone(String fireContactPhone) {
		this.fireContactPhone = fireContactPhone;
	}
	public String getConType() {
		return conType;
	}
	public void setConType(String conType) {
		this.conType = conType;
	}
	public String getConClass() {
		return conClass;
	}
	public void setConClass(String conClass) {
		this.conClass = conClass;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getCoverArea() {
		return coverArea;
	}
	public void setCoverArea(Double coverArea) {
		this.coverArea = coverArea;
	}
	public Integer getFloors() {
		return floors;
	}
	public void setFloors(Integer floors) {
		this.floors = floors;
	}
	

	
	

}
