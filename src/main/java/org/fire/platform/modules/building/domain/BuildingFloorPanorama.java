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
public class BuildingFloorPanorama extends BaseEntity{
	// Fields
		private Long id;
		private Long floorId;
		private String name;
		private String image;
		private String urls;
		private Integer state;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
	public BuildingFloorPanorama() {
		this.clear();
	}
	public BuildingFloorPanorama(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		floorId=null;
		name=null;
		image=null;
		urls=null;
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
		public Long getFloorId(){
			return floorId;
		}
		public void setFloorId(Long floorId){
			this.floorId=floorId;
		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getImage(){
			return image;
		}
		public void setImage(String image){
			this.image=image;
		}
		public String getUrls(){
			return urls;
		}
		public void setUrls(String urls){
			this.urls=urls;
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
		builder.append("BuildingFloorPanorama=[");
		builder.append("id="+id+",");
		builder.append("floorId="+floorId+",");
		builder.append("name="+name+",");
		builder.append("image="+image+",");
		builder.append("urls="+urls+",");
		builder.append("state="+state+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
