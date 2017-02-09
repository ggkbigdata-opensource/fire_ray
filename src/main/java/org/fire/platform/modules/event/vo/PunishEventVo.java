package org.fire.platform.modules.event.vo;

import java.util.Date;

import org.fire.platform.common.serialize.DictTransferAnnotation;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PunishEventVo {
	
	private Long eventId;
	@DictTransferAnnotation(param="punish_type")
	private String punishType; //0行政处罚，1行政罚款，2，三停
	private String name;
	private String placeOwner; //责任人
	private String placeName;
	private String punishPerson; //处理人
	@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
	private Date punishTime; //处罚时间
	private String streetName;
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getPunishType() {
		return punishType;
	}
	public void setPunishType(String punishType) {
		this.punishType = punishType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlaceOwner() {
		return placeOwner;
	}
	public void setPlaceOwner(String placeOwner) {
		this.placeOwner = placeOwner;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public Date getPunishTime() {
		return punishTime;
	}
	public void setPunishTime(Date punishTime) {
		this.punishTime = punishTime;
	}
	public String getPunishPerson() {
		return punishPerson;
	}
	public void setPunishPerson(String punishPerson) {
		this.punishPerson = punishPerson;
	}
	
	

}
