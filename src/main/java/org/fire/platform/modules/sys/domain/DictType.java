package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:47
 */
 
@SuppressWarnings("serial")
public class DictType extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private Integer state;
	public DictType() {
		this.clear();
	}
	public DictType(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		state=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getName(){
			return name;
		}
		public void setName(String name){
			this.name=name;
		}
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
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
		builder.append("DictType=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
