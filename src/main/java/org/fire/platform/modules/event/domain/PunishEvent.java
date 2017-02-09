package org.fire.platform.modules.event.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

import java.util.Date;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2017-1-18 17:32:53
 */

@SuppressWarnings("serial")
public class PunishEvent extends BaseEntity {
    // Fields
    private Long id;
    private String punishNumber;
    private Long districtId;
    private Long streetId;
    private Long blockId;
    private Long buildingId;
    private String name;
    private String decisionNumber;
    private String punishPersonName;
    private String placeName;
    private String punishAddress;
    private String placeOwner;
    private String placeOwnerIdcard;
    private String basis;
    private String punishType;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date punishTime;
    private Double punishAmount;
    private String sealedParts;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date sealedStartTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date sealedEndTime;
    private String remark;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date createTime;

    public PunishEvent() {
        this.clear();
    }

    public PunishEvent(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        punishNumber = null;
        districtId = null;
        streetId = null;
        blockId = null;
        buildingId = null;
        name = null;
        decisionNumber = null;
        punishPersonName = null;
        placeName = null;
        punishAddress = null;
        placeOwner = null;
        placeOwnerIdcard = null;
        basis = null;
        punishType = null;
        punishTime = null;
        punishAmount = null;
        sealedParts = null;
        sealedStartTime = null;
        sealedEndTime = null;
        remark = null;
        createTime = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPunishNumber() {
        return punishNumber;
    }

    public void setPunishNumber(String punishNumber) {
        this.punishNumber = punishNumber;
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

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecisionNumber() {
        return decisionNumber;
    }

    public void setDecisionNumber(String decisionNumber) {
        this.decisionNumber = decisionNumber;
    }

    public String getPunishPersonName() {
        return punishPersonName;
    }

    public void setPunishPersonName(String punishPersonName) {
        this.punishPersonName = punishPersonName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPunishAddress() {
        return punishAddress;
    }

    public void setPunishAddress(String punishAddress) {
        this.punishAddress = punishAddress;
    }

    public String getPlaceOwner() {
        return placeOwner;
    }

    public void setPlaceOwner(String placeOwner) {
        this.placeOwner = placeOwner;
    }

    public String getPlaceOwnerIdcard() {
        return placeOwnerIdcard;
    }

    public void setPlaceOwnerIdcard(String placeOwnerIdcard) {
        this.placeOwnerIdcard = placeOwnerIdcard;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getPunishType() {
        return punishType;
    }

    public void setPunishType(String punishType) {
        this.punishType = punishType;
    }

    public Date getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(Date punishTime) {
        this.punishTime = punishTime;
    }

    public Double getPunishAmount() {
        return punishAmount;
    }

    public void setPunishAmount(Double punishAmount) {
        this.punishAmount = punishAmount;
    }

    public String getSealedParts() {
        return sealedParts;
    }

    public void setSealedParts(String sealedParts) {
        this.sealedParts = sealedParts;
    }

    public Date getSealedStartTime() {
        return sealedStartTime;
    }

    public void setSealedStartTime(Date sealedStartTime) {
        this.sealedStartTime = sealedStartTime;
    }

    public Date getSealedEndTime() {
        return sealedEndTime;
    }

    public void setSealedEndTime(Date sealedEndTime) {
        this.sealedEndTime = sealedEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PunishEvent=[");
        builder.append("id=" + id + ",");
        builder.append("punishNumber=" + punishNumber + ",");
        builder.append("districtId=" + districtId + ",");
        builder.append("streetId=" + streetId + ",");
        builder.append("blockId=" + blockId + ",");
        builder.append("buildingId=" + buildingId + ",");
        builder.append("name=" + name + ",");
        builder.append("decisionNumber=" + decisionNumber + ",");
        builder.append("punishPersonName=" + punishPersonName + ",");
        builder.append("placeName=" + placeName + ",");
        builder.append("punishAddress=" + punishAddress + ",");
        builder.append("placeOwner=" + placeOwner + ",");
        builder.append("placeOwnerIdcard=" + placeOwnerIdcard + ",");
        builder.append("basis=" + basis + ",");
        builder.append("punishType=" + punishType + ",");
        builder.append("punishTime=" + punishTime + ",");
        builder.append("punishAmount=" + punishAmount + ",");
        builder.append("sealedParts=" + sealedParts + ",");
        builder.append("sealedStartTime=" + sealedStartTime + ",");
        builder.append("sealedEndTime=" + sealedEndTime + ",");
        builder.append("remark=" + remark + ",");
        builder.append("createTime=" + createTime + ",");
        builder.append("]");
        return builder.toString();
    }


}
