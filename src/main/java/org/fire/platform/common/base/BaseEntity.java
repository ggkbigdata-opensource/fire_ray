/*
 * <p>Title:BaseEntity.java </p>
 * <p>Company: Scho</p>
 * @author wangxiaobing
 * @version 1.0
 * @time: 2015年6月12日
 */
package org.fire.platform.common.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author wangxiaobing
 */
public class BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 2744424845439566261L;

	@JsonIgnore
	String extraOrderColumns;
	@JsonIgnore
	String extraUpdateColumns;
	@JsonIgnore
	String extraCondition;
	/**
	 * @return the extraOrderColumns
	 */
	public String getExtraOrderColumns() {
		return extraOrderColumns;
	}
	/**
	 * @param extraOrderColumns the extraOrderColumns to set
	 */
	public void setExtraOrderColumns(String extraOrderColumns) {
		this.extraOrderColumns = extraOrderColumns;
	}
	/**
	 * @return the extraUpdateColumns
	 */
	public String getExtraUpdateColumns() {
		return extraUpdateColumns;
	}
	/**
	 * @param extraUpdateColumns the extraUpdateColumns to set
	 */
	public void setExtraUpdateColumns(String extraUpdateColumns) {
		this.extraUpdateColumns = extraUpdateColumns;
	}
	/**
	 * @return the extraCondition
	 */
	public String getExtraCondition() {
		return extraCondition;
	}
	/**
	 * @param extraCondition the extraCondition to set
	 */
	public void setExtraCondition(String extraCondition) {
		this.extraCondition = extraCondition;
	}
	

}
