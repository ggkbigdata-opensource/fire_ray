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
public class FireSystem extends BaseEntity{
	// Fields
		private Long id;
		private Long buildingId;
		private Long managementId;
		private String systemName;
		private String systemConstituentName;
		private String modelSize;
		private Integer amount;
		private String manufacturer;
		private String position;
		private String useTime;
		private String useSituation;
		private Double volume;
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
	public FireSystem() {
		this.clear();
	}
	public FireSystem(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		buildingId=null;
		managementId=null;
		systemName=null;
		systemConstituentName=null;
		modelSize=null;
		amount=null;
		manufacturer=null;
		position=null;
		useTime=null;
		useSituation=null;
		volume=null;
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
		public String getSystemName(){
			return systemName;
		}
		public void setSystemName(String systemName){
			this.systemName=systemName;
		}
		public String getSystemConstituentName(){
			return systemConstituentName;
		}
		public void setSystemConstituentName(String systemConstituentName){
			this.systemConstituentName=systemConstituentName;
		}
		public String getModelSize(){
			return modelSize;
		}
		public void setModelSize(String modelSize){
			this.modelSize=modelSize;
		}
		public Integer getAmount(){
			return amount;
		}
		public void setAmount(Integer amount){
			this.amount=amount;
		}
		public String getManufacturer(){
			return manufacturer;
		}
		public void setManufacturer(String manufacturer){
			this.manufacturer=manufacturer;
		}
		public String getPosition(){
			return position;
		}
		public void setPosition(String position){
			this.position=position;
		}
		public String getUseTime(){
			return useTime;
		}
		public void setUseTime(String useTime){
			this.useTime=useTime;
		}
		public String getUseSituation(){
			return useSituation;
		}
		public void setUseSituation(String useSituation){
			this.useSituation=useSituation;
		}
		public Double getVolume(){
			return volume;
		}
		public void setVolume(Double volume){
			this.volume=volume;
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
		builder.append("FireSystem=[");
		builder.append("id="+id+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("managementId="+managementId+",");
		builder.append("systemName="+systemName+",");
		builder.append("systemConstituentName="+systemConstituentName+",");
		builder.append("modelSize="+modelSize+",");
		builder.append("amount="+amount+",");
		builder.append("manufacturer="+manufacturer+",");
		builder.append("position="+position+",");
		builder.append("useTime="+useTime+",");
		builder.append("useSituation="+useSituation+",");
		builder.append("volume="+volume+",");
		builder.append("remark="+remark+",");
		builder.append("modDate="+modDate+",");
		builder.append("createDate="+createDate+",");
		builder.append("userId="+userId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
