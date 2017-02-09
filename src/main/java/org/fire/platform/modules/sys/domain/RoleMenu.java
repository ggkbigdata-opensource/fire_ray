package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:09:18
 */
 
@SuppressWarnings("serial")
public class RoleMenu extends BaseEntity{
	// Fields
		private Long id;
		private Long roleId;
		private Long menuId;
	public RoleMenu() {
		this.clear();
	}
	public RoleMenu(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		roleId=null;
		menuId=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public Long getRoleId(){
			return roleId;
		}
		public void setRoleId(Long roleId){
			this.roleId=roleId;
		}
		public Long getMenuId(){
			return menuId;
		}
		public void setMenuId(Long menuId){
			this.menuId=menuId;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleMenu=[");
		builder.append("id="+id+",");
		builder.append("roleId="+roleId+",");
		builder.append("menuId="+menuId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
