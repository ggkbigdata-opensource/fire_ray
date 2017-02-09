package org.fire.platform.modules.event.domain;

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
public class FireCase extends BaseEntity{
	// Fields
		private Long id;
		private String province;
		private String city;
		private String district;
		private String title;
		private String content;
		private String address;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date occurTime;
		private String placeName;
		private String fireType;
		private String description;
		private String fireReasonType;
		private String fireReason;
		private Double loss;
		private Integer deadNum;
		private Integer hurtNum;
		private Integer disasterNum;
	public FireCase() {
		this.clear();
	}
	public FireCase(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		province=null;
		city=null;
		district=null;
		title=null;
		content=null;
		address=null;
		occurTime=null;
		placeName=null;
		fireType=null;
		description=null;
		fireReasonType=null;
		fireReason=null;
		loss=null;
		deadNum=null;
		hurtNum=null;
		disasterNum=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getProvince(){
			return province;
		}
		public void setProvince(String province){
			this.province=province;
		}
		public String getCity(){
			return city;
		}
		public void setCity(String city){
			this.city=city;
		}
		public String getDistrict(){
			return district;
		}
		public void setDistrict(String district){
			this.district=district;
		}
		public String getTitle(){
			return title;
		}
		public void setTitle(String title){
			this.title=title;
		}
		public String getContent(){
			return content;
		}
		public void setContent(String content){
			this.content=content;
		}
		public String getAddress(){
			return address;
		}
		public void setAddress(String address){
			this.address=address;
		}
		public Date getOccurTime(){
			return occurTime;
		}
		public void setOccurTime(Date occurTime){
			this.occurTime=occurTime;
		}
		public String getPlaceName(){
			return placeName;
		}
		public void setPlaceName(String placeName){
			this.placeName=placeName;
		}
		public String getFireType(){
			return fireType;
		}
		public void setFireType(String fireType){
			this.fireType=fireType;
		}
		public String getDescription(){
			return description;
		}
		public void setDescription(String description){
			this.description=description;
		}
		public String getFireReasonType(){
			return fireReasonType;
		}
		public void setFireReasonType(String fireReasonType){
			this.fireReasonType=fireReasonType;
		}
		public String getFireReason(){
			return fireReason;
		}
		public void setFireReason(String fireReason){
			this.fireReason=fireReason;
		}
		public Double getLoss(){
			return loss;
		}
		public void setLoss(Double loss){
			this.loss=loss;
		}
		public Integer getDeadNum(){
			return deadNum;
		}
		public void setDeadNum(Integer deadNum){
			this.deadNum=deadNum;
		}
		public Integer getHurtNum(){
			return hurtNum;
		}
		public void setHurtNum(Integer hurtNum){
			this.hurtNum=hurtNum;
		}
		public Integer getDisasterNum(){
			return disasterNum;
		}
		public void setDisasterNum(Integer disasterNum){
			this.disasterNum=disasterNum;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FireCase=[");
		builder.append("id="+id+",");
		builder.append("province="+province+",");
		builder.append("city="+city+",");
		builder.append("district="+district+",");
		builder.append("title="+title+",");
		builder.append("content="+content+",");
		builder.append("address="+address+",");
		builder.append("occurTime="+occurTime+",");
		builder.append("placeName="+placeName+",");
		builder.append("fireType="+fireType+",");
		builder.append("description="+description+",");
		builder.append("fireReasonType="+fireReasonType+",");
		builder.append("fireReason="+fireReason+",");
		builder.append("loss="+loss+",");
		builder.append("deadNum="+deadNum+",");
		builder.append("hurtNum="+hurtNum+",");
		builder.append("disasterNum="+disasterNum+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
