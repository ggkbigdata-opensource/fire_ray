package org.fire.platform.modules.area.vo;

import java.io.Serializable;

public class ValueDataVo implements Serializable{
	
	private String year;
	private Object data;

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
