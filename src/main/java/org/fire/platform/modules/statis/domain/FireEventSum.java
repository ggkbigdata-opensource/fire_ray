package org.fire.platform.modules.statis.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-20 19:09:29
 */
 
@SuppressWarnings("serial")
public class FireEventSum extends BaseEntity{
	// Fields
		private Long id;
		private String year;
		private String month;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private Integer originalFireNum;
		private Integer confirmFireNum;
		private Integer smokeFireNum;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date createDate;
		private Long createUser;
	public FireEventSum() {
		this.clear();
	}
	public FireEventSum(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		year=null;
		month=null;
		districtId=null;
		streetId=null;
		blockId=null;
		originalFireNum=null;
		confirmFireNum=null;
		smokeFireNum=null;
		createDate=null;
		createUser=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getYear(){
			return year;
		}
		public void setYear(String year){
			this.year=year;
		}
		public String getMonth(){
			return month;
		}
		public void setMonth(String month){
			this.month=month;
		}
		public Long getDistrictId(){
			return districtId;
		}
		public void setDistrictId(Long districtId){
			this.districtId=districtId;
		}
		public Long getStreetId(){
			return streetId;
		}
		public void setStreetId(Long streetId){
			this.streetId=streetId;
		}
		public Long getBlockId(){
			return blockId;
		}
		public void setBlockId(Long blockId){
			this.blockId=blockId;
		}
		public Integer getOriginalFireNum(){
			return originalFireNum;
		}
		public void setOriginalFireNum(Integer originalFireNum){
			this.originalFireNum=originalFireNum;
		}
		public Integer getConfirmFireNum(){
			return confirmFireNum;
		}
		public void setConfirmFireNum(Integer confirmFireNum){
			this.confirmFireNum=confirmFireNum;
		}
		public Integer getSmokeFireNum(){
			return smokeFireNum;
		}
		public void setSmokeFireNum(Integer smokeFireNum){
			this.smokeFireNum=smokeFireNum;
		}
		public Date getCreateDate(){
			return createDate;
		}
		public void setCreateDate(Date createDate){
			this.createDate=createDate;
		}
		public Long getCreateUser(){
			return createUser;
		}
		public void setCreateUser(Long createUser){
			this.createUser=createUser;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FireEventSum=[");
		builder.append("id="+id+",");
		builder.append("year="+year+",");
		builder.append("month="+month+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("originalFireNum="+originalFireNum+",");
		builder.append("confirmFireNum="+confirmFireNum+",");
		builder.append("smokeFireNum="+smokeFireNum+",");
		builder.append("createDate="+createDate+",");
		builder.append("createUser="+createUser+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
