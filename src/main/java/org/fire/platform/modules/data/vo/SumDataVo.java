package org.fire.platform.modules.data.vo;

public class SumDataVo {
	private Long districtId;
	private Long streetId;
	private Long blockId;
	private String year;
	private String month;
	private String type1;
	private String type2;
	private Integer count = 0;
	private Double fineTotal = 0.0;

	public Double getFineTotal() {
		return fineTotal;
	}

	public void setFineTotal(Double fineTotal) {
		this.fineTotal = fineTotal;
	}
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}


}
