package org.fire.platform.modules.event.vo;

import java.util.Date;

import org.fire.platform.common.serialize.DictTransferAnnotation;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FireEventVo {
	private String name; 
	private Long id;
	@DictTransferAnnotation(param="fire_type")
	private String fireType; //火灾类型
	private String placeName;
	@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
	private Date occurTime; //发生时间
	private String streetName; //街道名称
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFireType() {
		return fireType;
	}
	public void setFireType(String fireType) {
		this.fireType = fireType;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Date getOccurTime() {
		return occurTime;
	}
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
