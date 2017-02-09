package org.fire.platform.modules.area.vo;

import org.fire.platform.common.serialize.DictTransferAnnotation;

import java.io.Serializable;

public class AreaVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long districtId;   //具体的行政区ID
	private Long streetId;     //具体的街道ID
	private Long areaId;       //社区ID，社区，街道，街区ID
	private Integer areaType;  //社区类型
	@DictTransferAnnotation(param = "block_type")
	private String typeName;   //社区类型名称
	private String areaName;   //社区名称
	private String image;    //社区图片
	private String remark; //社区介绍
	private String safeIndex; //安全指数
	private String coverArea; //覆盖面积
	private String population;//人口
	private AreaTrendVo areaTrend;//社区趋势统计信息
	private int concerned; //是否已关注 1 关注 ，0 为关注

	public AreaTrendVo getAreaTrend() {
		return areaTrend;
	}
	public void setAreaTrend(AreaTrendVo areaTrend) {
		this.areaTrend = areaTrend;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSafeIndex() {
		return safeIndex;
	}
	public void setSafeIndex(String safeIndex) {
		this.safeIndex = safeIndex;
	}
	public String getCoverArea() {
		return coverArea;
	}
	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
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

	public int getConcerned() {
		return concerned;
	}

	public void setConcerned(int concerned) {
		this.concerned = concerned;
	}
}
