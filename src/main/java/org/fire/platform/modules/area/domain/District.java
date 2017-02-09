package org.fire.platform.modules.area.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-28 15:10:31
 */
 
@SuppressWarnings("serial")
public class District extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private String remark;
		private String images;
		private String coverArea;
		private String population;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
	public District() {
		this.clear();
	}
	public District(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		remark=null;
		images=null;
		coverArea=null;
		population=null;
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
		public String getImages(){
			return images;
		}
		public void setImages(String images){
			this.images=images;
		}
		public String getCoverArea(){
			return coverArea;
		}
		public void setCoverArea(String coverArea){
			this.coverArea=coverArea;
		}
		public String getPopulation(){
			return population;
		}
		public void setPopulation(String population){
			this.population=population;
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
		builder.append("District=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("remark="+remark+",");
		builder.append("images="+images+",");
		builder.append("coverArea="+coverArea+",");
		builder.append("population="+population+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
