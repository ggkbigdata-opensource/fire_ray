package org.fire.platform.modules.check.domain;

import java.util.*;

import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:01
 */
 
@SuppressWarnings("serial")
public class CheckItemResultBak extends BaseEntity{
	// Fields
		private Long id;
		private Long reportId;
		private Long itemId;
		private String itemSort;
		private String itemSortCode;
		private String itemType;
		private String itemTypeCode;
		private String name;
		private String level;
		private Integer checkType;
		private Integer isPass;
		private String result;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date checkTime;
		private String checker;
		private String imgs;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
	public CheckItemResultBak() {
		this.clear();
	}
	public CheckItemResultBak(Long id) {
		this();

		this.id=id;
	}
	
	public void clear() {
		id=null;
		reportId=null;
		itemId=null;
		itemSort=null;
		itemSortCode=null;
		itemType=null;
		itemTypeCode=null;
		name=null;
		level=null;
		checkType=null;
		isPass=null;
		result=null;
		checkTime=null;
		checker=null;
		imgs=null;
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
		public Long getReportId(){
			return reportId;
		}
		public void setReportId(Long reportId){
			this.reportId=reportId;
		}
		public Long getItemId(){
			return itemId;
		}
		public void setItemId(Long itemId){
			this.itemId=itemId;
		}
		public String getItemSort(){
			return itemSort;
		}
		public void setItemSort(String itemSort){
			this.itemSort=itemSort;
		}
		public String getItemSortCode(){
			return itemSortCode;
		}
		public void setItemSortCode(String itemSortCode){
			this.itemSortCode=itemSortCode;
		}
		public String getItemType(){
			return itemType;
		}
		public void setItemType(String itemType){
			this.itemType=itemType;
		}
		public String getItemTypeCode(){
			return itemTypeCode;
		}
		public void setItemTypeCode(String itemTypeCode){
			this.itemTypeCode=itemTypeCode;
		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getLevel(){
			return level;
		}
		public void setLevel(String level){
			this.level=level;
		}
		public Integer getCheckType(){
			return checkType;
		}
		public void setCheckType(Integer checkType){
			this.checkType=checkType;
		}
		public Integer getIsPass(){
			return isPass;
		}
		public void setIsPass(Integer isPass){
			this.isPass=isPass;
		}
		public String getResult(){
			return result;
		}
		public void setResult(String result){
			this.result=result;
		}
		public Date getCheckTime(){
			return checkTime;
		}
		public void setCheckTime(Date checkTime){
			this.checkTime=checkTime;
		}
		public String getChecker(){
			return checker;
		}
		public void setChecker(String checker){
			this.checker=checker;
		}
		public String getImgs(){
			return imgs;
		}
		public void setImgs(String imgs){
			this.imgs=imgs;
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
		builder.append("CheckItemResultBak=[");
		builder.append("id="+id+",");
		builder.append("reportId="+reportId+",");
		builder.append("itemId="+itemId+",");
		builder.append("itemSort="+itemSort+",");
		builder.append("itemSortCode="+itemSortCode+",");
		builder.append("itemType="+itemType+",");
		builder.append("itemTypeCode="+itemTypeCode+",");
		builder.append("name="+name+",");
		builder.append("level="+level+",");
		builder.append("checkType="+checkType+",");
		builder.append("isPass="+isPass+",");
		builder.append("result="+result+",");
		builder.append("checkTime="+checkTime+",");
		builder.append("checker="+checker+",");
		builder.append("imgs="+imgs+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
