package org.fire.platform.modules.building.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

import java.util.Date;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2016-12-26 16:28:43
 */

@SuppressWarnings("serial")
public class BuildingFunction extends BaseEntity {
    // Fields
    private Long id;
    private Long buildingId;
    private String buildingFloor;
    private String function;
    private Integer isSpecificLocation;
    private String funBusinessName;
    private Double funBuildArea;
    private String remark;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date modDate;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date createDate;
    private Long userId;

    public BuildingFunction() {
        this.clear();
    }

    public BuildingFunction(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        buildingId = null;
        buildingFloor = null;
        function = null;
        isSpecificLocation = null;
        funBusinessName = null;
        funBuildArea = null;
        remark = null;
        modDate = null;
        createDate = null;
        userId = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(String buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getIsSpecificLocation() {
        return isSpecificLocation;
    }

    public void setIsSpecificLocation(Integer isSpecificLocation) {
        this.isSpecificLocation = isSpecificLocation;
    }

    public String getFunBusinessName() {
        return funBusinessName;
    }

    public void setFunBusinessName(String funBusinessName) {
        this.funBusinessName = funBusinessName;
    }

    public Double getFunBuildArea() {
        return funBuildArea;
    }

    public void setFunBuildArea(Double funBuildArea) {
        this.funBuildArea = funBuildArea;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BuildingFunction=[");
        builder.append("id=" + id + ",");
        builder.append("buildingId=" + buildingId + ",");
        builder.append("buildingFloor=" + buildingFloor + ",");
        builder.append("function=" + function + ",");
        builder.append("isSpecificLocation=" + isSpecificLocation + ",");
        builder.append("funBusinessName=" + funBusinessName + ",");
        builder.append("funBuildArea=" + funBuildArea + ",");
        builder.append("remark=" + remark + ",");
        builder.append("modDate=" + modDate + ",");
        builder.append("createDate=" + createDate + ",");
        builder.append("userId=" + userId + ",");
        builder.append("]");
        return builder.toString();
    }


}
