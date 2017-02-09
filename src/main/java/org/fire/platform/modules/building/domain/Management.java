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
public class Management extends BaseEntity{
	// Fields
		private Long id;
		private Long buildingId;
		private String managerUnitName;
		private String managerAddress;
		private String chargePerson;
		private String contactName;
		private String contactPhone;
		private String supChargeUnitName;
		private String industrySupervisionDepart;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date publishTime;
		private Double registeredMoney;
		private Integer employeesNum;
		private Integer fireWitnessNum;
		private String baseCode;
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
	public Management() {
		this.clear();
	}
	public Management(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		buildingId=null;
		managerUnitName=null;
		managerAddress=null;
		chargePerson=null;
		contactName=null;
		contactPhone=null;
		supChargeUnitName=null;
		industrySupervisionDepart=null;
		publishTime=null;
		registeredMoney=null;
		employeesNum=null;
		fireWitnessNum=null;
		baseCode=null;
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
		public String getManagerUnitName(){
			return managerUnitName;
		}
		public void setManagerUnitName(String managerUnitName){
			this.managerUnitName=managerUnitName;
		}
		public String getManagerAddress(){
			return managerAddress;
		}
		public void setManagerAddress(String managerAddress){
			this.managerAddress=managerAddress;
		}
		public String getChargePerson(){
			return chargePerson;
		}
		public void setChargePerson(String chargePerson){
			this.chargePerson=chargePerson;
		}
		public String getContactName(){
			return contactName;
		}
		public void setContactName(String contactName){
			this.contactName=contactName;
		}
		public String getContactPhone(){
			return contactPhone;
		}
		public void setContactPhone(String contactPhone){
			this.contactPhone=contactPhone;
		}
		public String getSupChargeUnitName(){
			return supChargeUnitName;
		}
		public void setSupChargeUnitName(String supChargeUnitName){
			this.supChargeUnitName=supChargeUnitName;
		}
		public String getIndustrySupervisionDepart(){
			return industrySupervisionDepart;
		}
		public void setIndustrySupervisionDepart(String industrySupervisionDepart){
			this.industrySupervisionDepart=industrySupervisionDepart;
		}
		public Date getPublishTime(){
			return publishTime;
		}
		public void setPublishTime(Date publishTime){
			this.publishTime=publishTime;
		}
		public Double getRegisteredMoney(){
			return registeredMoney;
		}
		public void setRegisteredMoney(Double registeredMoney){
			this.registeredMoney=registeredMoney;
		}
		public Integer getEmployeesNum(){
			return employeesNum;
		}
		public void setEmployeesNum(Integer employeesNum){
			this.employeesNum=employeesNum;
		}
		public Integer getFireWitnessNum(){
			return fireWitnessNum;
		}
		public void setFireWitnessNum(Integer fireWitnessNum){
			this.fireWitnessNum=fireWitnessNum;
		}
		public String getBaseCode(){
			return baseCode;
		}
		public void setBaseCode(String baseCode){
			this.baseCode=baseCode;
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
		builder.append("Management=[");
		builder.append("id="+id+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("managerUnitName="+managerUnitName+",");
		builder.append("managerAddress="+managerAddress+",");
		builder.append("chargePerson="+chargePerson+",");
		builder.append("contactName="+contactName+",");
		builder.append("contactPhone="+contactPhone+",");
		builder.append("supChargeUnitName="+supChargeUnitName+",");
		builder.append("industrySupervisionDepart="+industrySupervisionDepart+",");
		builder.append("publishTime="+publishTime+",");
		builder.append("registeredMoney="+registeredMoney+",");
		builder.append("employeesNum="+employeesNum+",");
		builder.append("fireWitnessNum="+fireWitnessNum+",");
		builder.append("baseCode="+baseCode+",");
		builder.append("remark="+remark+",");
		builder.append("modDate="+modDate+",");
		builder.append("createDate="+createDate+",");
		builder.append("userId="+userId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
