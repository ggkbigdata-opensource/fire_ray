package org.fire.platform.modules.check.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:01
 */
 
@SuppressWarnings("serial")
public class CheckClass extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private Long standardId;
		private Double weight;
		private String code;
		private String remark;
		private Integer state;
	public CheckClass() {
		this.clear();
	}
	public CheckClass(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		standardId=null;
		weight=null;
		code=null;
		remark=null;
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
		public Long getStandardId(){
			return standardId;
		}
		public void setStandardId(Long standardId){
			this.standardId=standardId;
		}
		public Double getWeight(){
			return weight;
		}
		public void setWeight(Double weight){
			this.weight=weight;
		}
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
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
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CheckClass=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("standardId="+standardId+",");
		builder.append("weight="+weight+",");
		builder.append("code="+code+",");
		builder.append("remark="+remark+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
