package org.fire.platform.modules.area.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-11-7 16:38:55
 */
 
@SuppressWarnings("serial")
public class FireStation extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private String committeesAddress;
		private String committeesDirector;
		private String committeesPhone;
		private String stationAddress;
		private String stationMaster;
		private String stationPhone;
		private String office;
		private String vehicle;
		private String equipment;
		private Double area;
		private String emergencyService;
		private Integer officeSum;
		private Integer vehicleSum;
		private Integer equipmentSum;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modTime;
		private String remark;
	public FireStation() {
		this.clear();
	}
	public FireStation(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		districtId=null;
		streetId=null;
		blockId=null;
		committeesAddress=null;
		committeesDirector=null;
		committeesPhone=null;
		stationAddress=null;
		stationMaster=null;
		stationPhone=null;
		office=null;
		vehicle=null;
		equipment=null;
		area=null;
		emergencyService=null;
		officeSum=null;
		vehicleSum=null;
		equipmentSum=null;
		modTime=null;
		remark=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
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
		public String getCommitteesAddress(){
			return committeesAddress;
		}
		public void setCommitteesAddress(String committeesAddress){
			this.committeesAddress=committeesAddress;
		}
		public String getCommitteesDirector(){
			return committeesDirector;
		}
		public void setCommitteesDirector(String committeesDirector){
			this.committeesDirector=committeesDirector;
		}
		public String getCommitteesPhone(){
			return committeesPhone;
		}
		public void setCommitteesPhone(String committeesPhone){
			this.committeesPhone=committeesPhone;
		}
		public String getStationAddress(){
			return stationAddress;
		}
		public void setStationAddress(String stationAddress){
			this.stationAddress=stationAddress;
		}
		public String getStationMaster(){
			return stationMaster;
		}
		public void setStationMaster(String stationMaster){
			this.stationMaster=stationMaster;
		}
		public String getStationPhone(){
			return stationPhone;
		}
		public void setStationPhone(String stationPhone){
			this.stationPhone=stationPhone;
		}
		public String getOffice(){
			return office;
		}
		public void setOffice(String office){
			this.office=office;
		}
		public String getVehicle(){
			return vehicle;
		}
		public void setVehicle(String vehicle){
			this.vehicle=vehicle;
		}
		public String getEquipment(){
			return equipment;
		}
		public void setEquipment(String equipment){
			this.equipment=equipment;
		}
		public Double getArea(){
			return area;
		}
		public void setArea(Double area){
			this.area=area;
		}
		public String getEmergencyService(){
			return emergencyService;
		}
		public void setEmergencyService(String emergencyService){
			this.emergencyService=emergencyService;
		}
		public Integer getOfficeSum(){
			return officeSum;
		}
		public void setOfficeSum(Integer officeSum){
			this.officeSum=officeSum;
		}
		public Integer getVehicleSum(){
			return vehicleSum;
		}
		public void setVehicleSum(Integer vehicleSum){
			this.vehicleSum=vehicleSum;
		}
		public Integer getEquipmentSum(){
			return equipmentSum;
		}
		public void setEquipmentSum(Integer equipmentSum){
			this.equipmentSum=equipmentSum;
		}
		public Date getModTime(){
			return modTime;
		}
		public void setModTime(Date modTime){
			this.modTime=modTime;
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
		builder.append("FireStation=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("committeesAddress="+committeesAddress+",");
		builder.append("committeesDirector="+committeesDirector+",");
		builder.append("committeesPhone="+committeesPhone+",");
		builder.append("stationAddress="+stationAddress+",");
		builder.append("stationMaster="+stationMaster+",");
		builder.append("stationPhone="+stationPhone+",");
		builder.append("office="+office+",");
		builder.append("vehicle="+vehicle+",");
		builder.append("equipment="+equipment+",");
		builder.append("area="+area+",");
		builder.append("emergencyService="+emergencyService+",");
		builder.append("officeSum="+officeSum+",");
		builder.append("vehicleSum="+vehicleSum+",");
		builder.append("equipmentSum="+equipmentSum+",");
		builder.append("modTime="+modTime+",");
		builder.append("remark="+remark+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
