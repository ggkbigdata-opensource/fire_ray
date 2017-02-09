package org.fire.platform.modules.data.vo;

public class AreaSumDataVo {
	private Long districtId;
	private Long streetId;
	private Long blockId;
	private Integer fireCount;
	private Integer punishCount;
	private Integer reportCount;
	
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getStreetId() {
		return streetId;
	}
	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	public Integer getFireCount() {
		return fireCount;
	}
	public void setFireCount(Integer fireCount) {
		this.fireCount = fireCount;
	}
	public Integer getPunishCount() {
		return punishCount;
	}
	public void setPunishCount(Integer punishCount) {
		this.punishCount = punishCount;
	}
	public Integer getReportCount() {
		return reportCount;
	}
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}
	
	public int calcAreaSafeIndex(){
		int index = 1;
		if( fireCount != null && fireCount >= 10){
			index ++;
		}
		if( punishCount != null && punishCount >= 10 ){
			index ++;
		}
//		if( reportCount != null && reportCount > 10){
//			index ++;
//		}
		
		return index;
	}
	
	



}
