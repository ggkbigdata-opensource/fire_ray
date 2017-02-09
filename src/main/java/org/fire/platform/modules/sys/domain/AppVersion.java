package org.fire.platform.modules.sys.domain;

import java.util.Date;

import org.fire.platform.common.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-29 17:43:16
 */
 
@SuppressWarnings("serial")
public class AppVersion extends BaseEntity{
	// Fields
		private Long id;
		private String appVersion;
		private String appDownUrl;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date appUpdateTime;
		private String mapVersion;
		private String mapDownUrl;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm:ss",
	        timezone = "GMT+08:00"
	    )
		private Date mapUpdateTime;
		private Integer state;
	public AppVersion() {
		this.clear();
	}
	public AppVersion(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		appVersion=null;
		appDownUrl=null;
		appUpdateTime=null;
		mapVersion=null;
		mapDownUrl=null;
		mapUpdateTime=null;
		state=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getAppVersion(){
			return appVersion;
		}
		public void setAppVersion(String appVersion){
			this.appVersion=appVersion;
		}
		public String getAppDownUrl(){
			return appDownUrl;
		}
		public void setAppDownUrl(String appDownUrl){
			this.appDownUrl=appDownUrl;
		}
		public Date getAppUpdateTime(){
			return appUpdateTime;
		}
		public void setAppUpdateTime(Date appUpdateTime){
			this.appUpdateTime=appUpdateTime;
		}
		public String getMapVersion(){
			return mapVersion;
		}
		public void setMapVersion(String mapVersion){
			this.mapVersion=mapVersion;
		}
		public String getMapDownUrl(){
			return mapDownUrl;
		}
		public void setMapDownUrl(String mapDownUrl){
			this.mapDownUrl=mapDownUrl;
		}
		public Date getMapUpdateTime(){
			return mapUpdateTime;
		}
		public void setMapUpdateTime(Date mapUpdateTime){
			this.mapUpdateTime=mapUpdateTime;
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
		builder.append("AppVersion=[");
		builder.append("id="+id+",");
		builder.append("appVersion="+appVersion+",");
		builder.append("appDownUrl="+appDownUrl+",");
		builder.append("appUpdateTime="+appUpdateTime+",");
		builder.append("mapVersion="+mapVersion+",");
		builder.append("mapDownUrl="+mapDownUrl+",");
		builder.append("mapUpdateTime="+mapUpdateTime+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
