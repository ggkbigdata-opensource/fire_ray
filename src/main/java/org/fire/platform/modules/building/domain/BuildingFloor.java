package org.fire.platform.modules.building.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-10-31 19:08:02
 */
 
@SuppressWarnings("serial")
public class BuildingFloor extends BaseEntity{
	// Fields
		private Long id;
		private String name;
		private String code;
		private Long buildingId;
		private String remark;
		private Integer state;
	public BuildingFloor() {
		this.clear();
	}
	public BuildingFloor(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		name=null;
		code=null;
		buildingId=null;
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
		public String getCode(){
			return code;
		}
		public void setCode(String code){
			this.code=code;
		}
		public Long getBuildingId(){
			return buildingId;
		}
		public void setBuildingId(Long buildingId){
			this.buildingId=buildingId;
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
		builder.append("BuildingFloor=[");
		builder.append("id="+id+",");
		builder.append("name="+name+",");
		builder.append("code="+code+",");
		builder.append("buildingId="+buildingId+",");
		builder.append("remark="+remark+",");
		builder.append("state="+state+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
