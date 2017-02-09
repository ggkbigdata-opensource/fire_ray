package org.fire.platform.modules.building.domain;

import java.util.Date;

import org.fire.platform.common.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-19 11:42:03
 */
 
@SuppressWarnings("serial")
public class UserConcernBuilding extends BaseEntity{
	// Fields
		private Long id;
		private Long userId;
		private Long buildingId;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date createDate;
	public UserConcernBuilding() {
		this.clear();
	}
	public UserConcernBuilding(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		userId=null;
		buildingId=null;
		createDate=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getUserId(){
			return userId;
		}
		public void setUserId(Long userId){
			this.userId=userId;
		}
		public Long getBuildingId(){
			return buildingId;
		}
		public void setBuildingId(Long buildingId){
			this.buildingId=buildingId;
		}
		public Date getCreateDate(){
			return createDate;
		}
		public void setCreateDate(Date createDate){
			this.createDate=createDate;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserConcernBuilding=[");
		builder.append("id="+id+",");
		builder.append("userId="+userId+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("createDate="+createDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
