package org.fire.platform.modules.sys.domain;

import java.util.Date;

import org.fire.platform.common.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-29 16:55:06
 */
 
@SuppressWarnings("serial")
public class User extends BaseEntity{
	// Fields
		private Long uid;
		private String username;
		private String password;
		private Long deptId;
		private String realName;
		private Integer gender;
		private String mobile;
		private String email;
		private String job;
		private Long roleId;
		private Integer state;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date lastLoginTime;
		private String token;
		private Integer loginType;
		private Long modUser;
		@JsonFormat(
	        pattern = "yyyy-MM-dd HH:mm",
	        timezone = "GMT+08:00"
	    )
		private Date modDate;
		private String remark;
		private String imeiNum;
		private Long districtId;
	public User() {
		this.clear();
	}
	public User(Long uid) {
		this();
		this.uid=uid;
	}
	
	public void clear() {
		uid=null;
		username=null;
		password=null;
		deptId=null;
		realName=null;
		gender=null;
		mobile=null;
		email=null;
		job=null;
		roleId=null;
		state=null;
		lastLoginTime=null;
		token=null;
		loginType=null;
		modUser=null;
		modDate=null;
		remark=null;
		imeiNum=null;
		districtId=null;
	}

	// Getters/Setters
		public Long getUid(){
			return uid;
		}
		public void setUid(Long uid){
			this.uid=uid;
		}
		public String getUsername(){
			return username;
		}
		public void setUsername(String username){
			this.username=username;
		}
		public String getPassword(){
			return password;
		}
		public void setPassword(String password){
			this.password=password;
		}
		public Long getDeptId(){
			return deptId;
		}
		public void setDeptId(Long deptId){
			this.deptId=deptId;
		}
		public String getRealName(){
			return realName;
		}
		public void setRealName(String realName){
			this.realName=realName;
		}
		public Integer getGender(){
			return gender;
		}
		public void setGender(Integer gender){
			this.gender=gender;
		}
		public String getMobile(){
			return mobile;
		}
		public void setMobile(String mobile){
			this.mobile=mobile;
		}
		public String getEmail(){
			return email;
		}
		public void setEmail(String email){
			this.email=email;
		}
		public String getJob(){
			return job;
		}
		public void setJob(String job){
			this.job=job;
		}
		public Long getRoleId(){
			return roleId;
		}
		public void setRoleId(Long roleId){
			this.roleId=roleId;
		}
		public Integer getState(){
			return state;
		}
		public void setState(Integer state){
			this.state=state;
		}
		public Date getLastLoginTime(){
			return lastLoginTime;
		}
		public void setLastLoginTime(Date lastLoginTime){
			this.lastLoginTime=lastLoginTime;
		}
		public String getToken(){
			return token;
		}
		public void setToken(String token){
			this.token=token;
		}
		public Integer getLoginType(){
			return loginType;
		}
		public void setLoginType(Integer loginType){
			this.loginType=loginType;
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
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
		public String getImeiNum(){
			return imeiNum;
		}
		public void setImeiNum(String imeiNum){
			this.imeiNum=imeiNum;
		}
		public Long getDistrictId(){
			return districtId;
		}
		public void setDistrictId(Long districtId){
			this.districtId=districtId;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User=[");
		builder.append("uid="+uid+",");
		builder.append("username="+username+",");
		builder.append("password="+password+",");
		builder.append("deptId="+deptId+",");
		builder.append("realName="+realName+",");
		builder.append("gender="+gender+",");
		builder.append("mobile="+mobile+",");
		builder.append("email="+email+",");
		builder.append("job="+job+",");
		builder.append("roleId="+roleId+",");
		builder.append("state="+state+",");
		builder.append("lastLoginTime="+lastLoginTime+",");
		builder.append("token="+token+",");
		builder.append("loginType="+loginType+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("remark="+remark+",");
		builder.append("imeiNum="+imeiNum+",");
		builder.append("districtId="+districtId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
