package org.fire.platform.modules.sys.domain;

import org.fire.platform.common.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:48
 */
 
@SuppressWarnings("serial")
public class Dict extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private String typeCode;
		@JsonIgnore
		private Integer state;
	public Dict() {
		this.clear();
	}
	public Dict(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		typeCode=null;
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
		public String getTypeCode(){
			return typeCode;
		}
		public void setTypeCode(String typeCode){
			this.typeCode=typeCode;
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
		builder.append("Dict=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("typeCode="+typeCode+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
