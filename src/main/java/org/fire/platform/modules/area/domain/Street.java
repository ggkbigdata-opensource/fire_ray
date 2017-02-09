package org.fire.platform.modules.area.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-18 19:56:50
 */
 
@SuppressWarnings("serial")
public class Street extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private Long districtId;
		private String remark;
		private String images;
		private Double longitude;
		private Double latitude;
		private String coverArea;
		private String population;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
		private Integer latestRiskIndex;
	public Street() {
		this.clear();
	}
	public Street(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		districtId=null;
		remark=null;
		images=null;
		longitude=null;
		latitude=null;
		coverArea=null;
		population=null;
		modUser=null;
		modDate=null;
		latestRiskIndex=null;
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
		public Double getLongitude(){
			return longitude;
		}
		public void setLongitude(Double longitude){
			this.longitude=longitude;
		}
		public Double getLatitude(){
			return latitude;
		}
		public void setLatitude(Double latitude){
			this.latitude=latitude;
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
		public Integer getLatestRiskIndex(){
			return latestRiskIndex;
		}
		public void setLatestRiskIndex(Integer latestRiskIndex){
			this.latestRiskIndex=latestRiskIndex;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Street=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("districtId="+districtId+",");
		builder.append("remark="+remark+",");
		builder.append("images="+images+",");
		builder.append("longitude="+longitude+",");
		builder.append("latitude="+latitude+",");
		builder.append("coverArea="+coverArea+",");
		builder.append("population="+population+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("latestRiskIndex="+latestRiskIndex+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
