package org.fire.platform.modules.area.bean;

import java.util.List;

public class Geometry {
	String type;
	List<List<List<Double>>> coordinates;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<List<List<Double>>> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<List<List<Double>>> coordinates) {
		this.coordinates = coordinates;
	}
	
	

}
