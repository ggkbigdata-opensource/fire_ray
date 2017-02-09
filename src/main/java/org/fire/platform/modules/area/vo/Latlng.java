package org.fire.platform.modules.area.vo;

public class Latlng {
	private Double longitude;//经度
	private Double latitude;//维度
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public Latlng(){
		
	}
	
	public Latlng(Double longitude,Double latitude){
		this.longitude = longitude;
		this.latitude = latitude;
		
	}
	
	
}
