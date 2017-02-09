package org.fire.platform.modules.sys.domain;

import java.util.Date;

import org.fire.platform.common.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-19 10:00:28
 */
 
@SuppressWarnings("serial")
public class LoginLog extends BaseEntity{
	// Fields
		private Long id;
		private Long userId;
		private String username;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date loginTime;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date logoutTime;
		private Integer loginType;
		private String loginSource;
		private String ipAddress;
		private String imeiNum;
		private String mobile;
		private String token;
	public LoginLog() {
		this.clear();
	}
	public LoginLog(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		userId=null;
		username=null;
		loginTime=null;
		logoutTime=null;
		loginType=null;
		loginSource=null;
		ipAddress=null;
		imeiNum=null;
		mobile=null;
		token=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getUserId(){
			return userId;
		}
		public void setUserId(Long userId){
			this.userId=userId;
		}
		public String getUsername(){
			return username;
		}
		public void setUsername(String username){
			this.username=username;
		}
		public Date getLoginTime(){
			return loginTime;
		}
		public void setLoginTime(Date loginTime){
			this.loginTime=loginTime;
		}
		public Date getLogoutTime(){
			return logoutTime;
		}
		public void setLogoutTime(Date logoutTime){
			this.logoutTime=logoutTime;
		}
		public Integer getLoginType(){
			return loginType;
		}
		public void setLoginType(Integer loginType){
			this.loginType=loginType;
		}
		public String getLoginSource(){
			return loginSource;
		}
		public void setLoginSource(String loginSource){
			this.loginSource=loginSource;
		}
		public String getIpAddress(){
			return ipAddress;
		}
		public void setIpAddress(String ipAddress){
			this.ipAddress=ipAddress;
		}
		public String getImeiNum(){
			return imeiNum;
		}
		public void setImeiNum(String imeiNum){
			this.imeiNum=imeiNum;
		}
		public String getMobile(){
			return mobile;
		}
		public void setMobile(String mobile){
			this.mobile=mobile;
		}
		public String getToken(){
			return token;
		}
		public void setToken(String token){
			this.token=token;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginLog=[");
		builder.append("id="+id+",");
		builder.append("userId="+userId+",");
		builder.append("username="+username+",");
		builder.append("loginTime="+loginTime+",");
		builder.append("logoutTime="+logoutTime+",");
		builder.append("loginType="+loginType+",");
		builder.append("loginSource="+loginSource+",");
		builder.append("ipAddress="+ipAddress+",");
		builder.append("imeiNum="+imeiNum+",");
		builder.append("mobile="+mobile+",");
		builder.append("token="+token+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
