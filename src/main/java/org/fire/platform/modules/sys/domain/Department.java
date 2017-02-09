package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:09:17
 */
 
@SuppressWarnings("serial")
public class Department extends BaseEntity{
	// Fields
		private Long id;
		private String deptName;
		private String deptCode;
		private String deptType;
		private Long parentId;
		private String remark;
		private Integer state;
		private Long modUser;
		private String modDate;
	public Department() {
		this.clear();
	}
	public Department(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		deptName=null;
		deptCode=null;
		deptType=null;
		parentId=null;
		remark=null;
		state=null;
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
		public String getDeptName(){
			return deptName;
		}
		public void setDeptName(String deptName){
			this.deptName=deptName;
		}
		public String getDeptCode(){
			return deptCode;
		}
		public void setDeptCode(String deptCode){
			this.deptCode=deptCode;
		}
		public String getDeptType(){
			return deptType;
		}
		public void setDeptType(String deptType){
			this.deptType=deptType;
		}
		public Long getParentId(){
			return parentId;
		}
		public void setParentId(Long parentId){
			this.parentId=parentId;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
		public Integer getState(){
			return state;
		}
		public void setState(Integer state){
			this.state=state;
		}
		public Long getModUser(){
			return modUser;
		}
		public void setModUser(Long modUser){
			this.modUser=modUser;
		}
		public String getModDate(){
			return modDate;
		}
		public void setModDate(String modDate){
			this.modDate=modDate;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Department=[");
		builder.append("id="+id+",");
		builder.append("deptName="+deptName+",");
		builder.append("deptCode="+deptCode+",");
		builder.append("deptType="+deptType+",");
		builder.append("parentId="+parentId+",");
		builder.append("remark="+remark+",");
		builder.append("state="+state+",");
		builder.append("modUser="+modUser+",");
		builder.append("modDate="+modDate+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
