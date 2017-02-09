package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 15:09:17
 */
 
@SuppressWarnings("serial")
public class Menu extends BaseEntity{
	// Fields
		private Long menuId;
		private String menuName;
		private String menuUrl;
		private Long parentMenuId;
	public Menu() {
		this.clear();
	}
	public Menu(Long menuId) {
		this();
		this.menuId=menuId;
	}
	
	public void clear() {
		menuId=null;
		menuName=null;
		menuUrl=null;
		parentMenuId=null;
	}

	// Getters/Setters
		public Long getMenuId(){
			return menuId;
		}
		public void setMenuId(Long menuId){
			this.menuId=menuId;
		}
		public String getMenuName(){
			return menuName;
		}
		public void setMenuName(String menuName){
			this.menuName=menuName;
		}
		public String getMenuUrl(){
			return menuUrl;
		}
		public void setMenuUrl(String menuUrl){
			this.menuUrl=menuUrl;
		}
		public Long getParentMenuId(){
			return parentMenuId;
		}
		public void setParentMenuId(Long parentMenuId){
			this.parentMenuId=parentMenuId;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu=[");
		builder.append("menuId="+menuId+",");
		builder.append("menuName="+menuName+",");
		builder.append("menuUrl="+menuUrl+",");
		builder.append("parentMenuId="+parentMenuId+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
