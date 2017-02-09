package org.fire.platform.modules.statis.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-26 19:26:09
 */
 
@SuppressWarnings("serial")
public class PunishEventSum extends BaseEntity{
	// Fields
		private Long id;
		private String year;
		private String month;
		private Long districtId;
		private Long streetId;
		private Long blockId;
		private Integer detainedNum;
		private Integer fineNum;
		private Double fineTotal;
		private Integer threeStopNum;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date createDate;
		private Long createUser;
		private Integer closeDownNum;
	public PunishEventSum() {
		this.clear();
	}
	public PunishEventSum(Long id) {
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
		detainedNum=null;
		fineNum=null;
		fineTotal=null;
		threeStopNum=null;
		createDate=null;
		createUser=null;
		closeDownNum=null;
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
		public Integer getDetainedNum(){
			return detainedNum;
		}
		public void setDetainedNum(Integer detainedNum){
			this.detainedNum=detainedNum;
		}
		public Integer getFineNum(){
			return fineNum;
		}
		public void setFineNum(Integer fineNum){
			this.fineNum=fineNum;
		}
		public Double getFineTotal(){
			return fineTotal;
		}
		public void setFineTotal(Double fineTotal){
			this.fineTotal=fineTotal;
		}
		public Integer getThreeStopNum(){
			return threeStopNum;
		}
		public void setThreeStopNum(Integer threeStopNum){
			this.threeStopNum=threeStopNum;
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
		public Integer getCloseDownNum(){
			return closeDownNum;
		}
		public void setCloseDownNum(Integer closeDownNum){
			this.closeDownNum=closeDownNum;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PunishEventSum=[");
		builder.append("id="+id+",");
		builder.append("year="+year+",");
		builder.append("month="+month+",");
		builder.append("districtId="+districtId+",");
		builder.append("streetId="+streetId+",");
		builder.append("blockId="+blockId+",");
		builder.append("detainedNum="+detainedNum+",");
		builder.append("fineNum="+fineNum+",");
		builder.append("fineTotal="+fineTotal+",");
		builder.append("threeStopNum="+threeStopNum+",");
		builder.append("createDate="+createDate+",");
		builder.append("createUser="+createUser+",");
		builder.append("closeDownNum="+closeDownNum+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
