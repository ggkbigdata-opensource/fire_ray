package org.fire.platform.modules.building.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:02
 */
 
@SuppressWarnings("serial")
public class BuildingPanorama extends BaseEntity{
	// Fields
		private Long id;
		private Long buildingId;
		private String name;
		private String thumbImg;
		private String url;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
		private Integer state;
	public BuildingPanorama() {
		this.clear();
	}
	public BuildingPanorama(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		buildingId=null;
		name=null;
		thumbImg=null;
		url=null;
		modUser=null;
		modDate=null;
		state=null;
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
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getThumbImg(){
			return thumbImg;
		}
		public void setThumbImg(String thumbImg){
			this.thumbImg=thumbImg;
		}
		public String getUrl(){
			return url;
		}
		public void setUrl(String url){
			this.url=url;
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
		public Integer getState(){
			return state;
		}
		public void setState(Integer state){
			this.state=state;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BuildingPanorama=[");
		builder.append("id="+id+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("name="+name+",");
		builder.append("thumbImg="+thumbImg+",");
		builder.append("url="+url+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
