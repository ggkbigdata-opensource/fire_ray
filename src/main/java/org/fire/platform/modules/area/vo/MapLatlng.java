package org.fire.platform.modules.area.vo;

import java.util.List;

public class MapLatlng {
	private String blockCode;
	private String name;
	private List<List<Latlng>> latlngList;
	private Latlng center;
	
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<List<Latlng>> getLatlngList() {
		return latlngList;
	}
	public void setLatlngList(List<List<Latlng>> latlngList) {
		this.latlngList = latlngList;
	}
	public Latlng getCenter() {
		return center;
	}
	public void setCenter(Latlng center) {
		this.center = center;
	}
	
	
	
	
	

}
