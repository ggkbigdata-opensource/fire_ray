package org.fire.platform.modules.building.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BuildingEventVo {
	
	private Long id;
	private String name;
	@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
	private Date createTime;
	private Integer eventType; //0报告，1火情，2执法
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
	
	
	

}
