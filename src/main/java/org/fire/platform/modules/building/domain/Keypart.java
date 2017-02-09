package org.fire.platform.modules.building.domain;

import java.util.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author ZKT
 * @date 2016-11-22 10:10:38
 */
 
@SuppressWarnings("serial")
public class Keypart extends BaseEntity{
	// Fields
		private Long id;
		private Long buildingId;
		private Long managementId;
		private String keypartName;
		private String position;
		private Double area;
		private String fireEquipment;
		private Integer dutyNum;
		private Integer diplomaNum;
		private Integer firePumpNum;
		private Integer sprayPumpNum;
		private Integer pressurePumpNum;
		private Double airTankVolume;
		private Double storageArea;
		private String oilVolume;
		private String remark;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date createDate;
		private Long userId;
	public Keypart() {
		this.clear();
	}
	public Keypart(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		buildingId=null;
		managementId=null;
		keypartName=null;
		position=null;
		area=null;
		fireEquipment=null;
		dutyNum=null;
		diplomaNum=null;
		firePumpNum=null;
		sprayPumpNum=null;
		pressurePumpNum=null;
		airTankVolume=null;
		storageArea=null;
		oilVolume=null;
		remark=null;
		modDate=null;
		createDate=null;
		userId=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getBuildingId(){
			return buildingId;
		}
		public void setBuildingId(Long buildingId){
			this.buildingId=buildingId;
		}
		public Long getManagementId(){
			return managementId;
		}
		public void setManagementId(Long managementId){
			this.managementId=managementId;
		}
		public String getKeypartName(){
			return keypartName;
		}
		public void setKeypartName(String keypartName){
			this.keypartName=keypartName;
		}
		public String getPosition(){
			return position;
		}
		public void setPosition(String position){
			this.position=position;
		}
		public Double getArea(){
			return area;
		}
		public void setArea(Double area){
			this.area=area;
		}
		public String getFireEquipment(){
			return fireEquipment;
		}
		public void setFireEquipment(String fireEquipment){
			this.fireEquipment=fireEquipment;
		}
		public Integer getDutyNum(){
			return dutyNum;
		}
		public void setDutyNum(Integer dutyNum){
			this.dutyNum=dutyNum;
		}
		public Integer getDiplomaNum(){
			return diplomaNum;
		}
		public void setDiplomaNum(Integer diplomaNum){
			this.diplomaNum=diplomaNum;
		}
		public Integer getFirePumpNum(){
			return firePumpNum;
		}
		public void setFirePumpNum(Integer firePumpNum){
			this.firePumpNum=firePumpNum;
		}
		public Integer getSprayPumpNum(){
			return sprayPumpNum;
		}
		public void setSprayPumpNum(Integer sprayPumpNum){
			this.sprayPumpNum=sprayPumpNum;
		}
		public Integer getPressurePumpNum(){
			return pressurePumpNum;
		}
		public void setPressurePumpNum(Integer pressurePumpNum){
			this.pressurePumpNum=pressurePumpNum;
		}
		public Double getAirTankVolume(){
			return airTankVolume;
		}
		public void setAirTankVolume(Double airTankVolume){
			this.airTankVolume=airTankVolume;
		}
		public Double getStorageArea(){
			return storageArea;
		}
		public void setStorageArea(Double storageArea){
			this.storageArea=storageArea;
		}
		public String getOilVolume(){
			return oilVolume;
		}
		public void setOilVolume(String oilVolume){
			this.oilVolume=oilVolume;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
		public Date getModDate(){
			return modDate;
		}
		public void setModDate(Date modDate){
			this.modDate=modDate;
		}
		public Date getCreateDate(){
			return createDate;
		}
		public void setCreateDate(Date createDate){
			this.createDate=createDate;
		}
		public Long getUserId(){
			return userId;
		}
		public void setUserId(Long userId){
			this.userId=userId;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keypart=[");
		builder.append("id="+id+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("managementId="+managementId+",");
		builder.append("keypartName="+keypartName+",");
		builder.append("position="+position+",");
		builder.append("area="+area+",");
		builder.append("fireEquipment="+fireEquipment+",");
		builder.append("dutyNum="+dutyNum+",");
		builder.append("diplomaNum="+diplomaNum+",");
		builder.append("firePumpNum="+firePumpNum+",");
		builder.append("sprayPumpNum="+sprayPumpNum+",");
		builder.append("pressurePumpNum="+pressurePumpNum+",");
		builder.append("airTankVolume="+airTankVolume+",");
		builder.append("storageArea="+storageArea+",");
		builder.append("oilVolume="+oilVolume+",");
		builder.append("remark="+remark+",");
		builder.append("modDate="+modDate+",");
		builder.append("createDate="+createDate+",");
		builder.append("userId="+userId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
