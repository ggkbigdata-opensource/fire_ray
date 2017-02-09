package org.fire.platform.modules.area.domain;

import java.util.*;
import java.math.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author Administrator
 * @date 2016-9-30 14:55:36
 */
 
@SuppressWarnings("serial")
public class AreaMap extends BaseEntity{
	// Fields
		private Long id;
		private String areaCode;
		private String areaName;
		private String mapData;
		private String areaCenter;
	public AreaMap() {
		this.clear();
	}
	public AreaMap(Long id) {
		this();
		this.id=id;
	}
	
	public void clear() {
		id=null;
		areaCode=null;
		areaName=null;
		mapData=null;
		areaCenter=null;
	}

	// Getters/Setters
		public Long getId(){
			return id;
		}
		public void setId(Long id){
			this.id=id;
		}
		public String getAreaCode(){
			return areaCode;
		}
		public void setAreaCode(String areaCode){
			this.areaCode=areaCode;
		}
		public String getAreaName(){
			return areaName;
		}
		public void setAreaName(String areaName){
			this.areaName=areaName;
		}
		public String getMapData(){
			return mapData;
		}
		public void setMapData(String mapData){
			this.mapData=mapData;
		}
		public String getAreaCenter(){
			return areaCenter;
		}
		public void setAreaCenter(String areaCenter){
			this.areaCenter=areaCenter;
		}
	
	//toString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AreaMap=[");
		builder.append("id="+id+",");
		builder.append("areaCode="+areaCode+",");
		builder.append("areaName="+areaName+",");
		builder.append("mapData="+mapData+",");
		builder.append("areaCenter="+areaCenter+",");
		builder.append("]");
		return builder.toString();
	}
	
 
}
