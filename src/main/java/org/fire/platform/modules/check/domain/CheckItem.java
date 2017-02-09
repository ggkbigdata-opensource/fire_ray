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
public class CheckItem extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private Long classId;
		private String checkItemType;
		private String code;
		private String remark;
		private Integer state;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
	public CheckItem() {
		this.clear();
	}
	public CheckItem(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		classId=null;
		checkItemType=null;
		code=null;
		remark=null;
		state=null;
		modUser=null;
		modDate=null;
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
		public Long getClassId(){
			return classId;
		}
		public void setClassId(Long classId){
			this.classId=classId;
		}
		public String getCheckItemType(){
			return checkItemType;
		}
		public void setCheckItemType(String checkItemType){
			this.checkItemType=checkItemType;
		}
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
		public Integer getState(){
			return state;
		}
		public void setState(Integer state){
			this.state=state;
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
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckItem=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("classId="+classId+",");
		builder.append("checkItemType="+checkItemType+",");
		builder.append("code="+code+",");
		builder.append("remark="+remark+",");
		builder.append("state="+state+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
