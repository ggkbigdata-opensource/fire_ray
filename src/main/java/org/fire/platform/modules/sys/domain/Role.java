package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:09:18
 */
 
@SuppressWarnings("serial")
public class Role extends BaseEntity{
	// Fields
		private Long roleId;
		private String roleName;
		private String remark;
	public Role() {
		this.clear();
	}
	public Role(Long roleId) {
		this();
		this.roleId=roleId;
	}
	
	public void clear() {
		roleId=null;
		roleName=null;
		remark=null;
	}

	// Getters/Setters
		public Long getRoleId(){
			return roleId;
		}
		public void setRoleId(Long roleId){
			this.roleId=roleId;
		}
		public String getRoleName(){
			return roleName;
		}
		public void setRoleName(String roleName){
			this.roleName=roleName;
		}
		public String getRemark(){
			return remark;
		}
		public void setRemark(String remark){
			this.remark=remark;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role=[");
		builder.append("roleId="+roleId+",");
		builder.append("roleName="+roleName+",");
		builder.append("remark="+remark+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
