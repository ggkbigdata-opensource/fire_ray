package org.fire.platform.modules.area.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-19 12:43:23
 */
 
@SuppressWarnings("serial")
public class UserConcernArea extends BaseEntity{
	// Fields
		private Long id;
		private Long userId;
		private Integer areaType;
		private Long areaId;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date createDate;
	public UserConcernArea() {
		this.clear();
	}
	public UserConcernArea(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		userId=null;
		areaType=null;
		areaId=null;
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
		public Integer getAreaType(){
			return areaType;
		}
		public void setAreaType(Integer areaType){
			this.areaType=areaType;
		}
		public Long getAreaId(){
			return areaId;
		}
		public void setAreaId(Long areaId){
			this.areaId=areaId;
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
		builder.append("UserConcernArea=[");
		builder.append("id="+id+",");
		builder.append("userId="+userId+",");
		builder.append("areaType="+areaType+",");
		builder.append("areaId="+areaId+",");
		builder.append("createDate="+createDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
