package org.fire.platform.modules.event.domain;

import java.util.*;
import java.math.*;

import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author Administrator
 * @date 2016-12-6 11:09:59
 */

@SuppressWarnings("serial")
public class FireEvent extends BaseEntity {
    // Fields
    private Long id;
    private Long districtId;
    private Long streetId;
    private Long blockId;
    private Long buildingId;
    private String name;
    private String placeName;
    private String placeUseType;
    private String placePositionType;
    private String placeSpaceType;
    private String placeConcentrateType;
    private String placeFireType;
    private String placeBuildType;
    private String fireType;
    private String description;
    private String firePosition;
    private String fireObject;
    private String fireReasonType;
    private String fireReason;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date occurTime;
    private Double loss;
    private String caseNumber;
    private Integer deadNum;
    private Integer hurtNum;
    private String state;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date createDate;
    private Integer selfSave;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm",
            timezone = "GMT+08:00"
    )
    private Date modDate;
    private String cityArea;
    private String enterpriseNature;
    private String punishCaseNumber;
    private Integer fireStation;
    private String handleDepart;
    private String sceneDesc;
    private String dutyPart;

    public FireEvent() {
        this.clear();
    }

    public FireEvent(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        districtId = null;
        streetId = null;
        blockId = null;
        buildingId = null;
        name = null;
        placeName = null;
        placeUseType = null;
        placePositionType = null;
        placeSpaceType = null;
        placeConcentrateType = null;
        placeFireType = null;
        placeBuildType = null;
        fireType = null;
        description = null;
        firePosition = null;
        fireObject = null;
        fireReasonType = null;
        fireReason = null;
        occurTime = null;
        loss = null;
        caseNumber = null;
        deadNum = null;
        hurtNum = null;
        state = null;
        createDate = null;
        selfSave = null;
        modDate = null;
        cityArea = null;
        enterpriseNature = null;
        punishCaseNumber = null;
        fireStation = null;
        handleDepart = null;
        sceneDesc = null;
        dutyPart = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceUseType() {
        return placeUseType;
    }

    public void setPlaceUseType(String placeUseType) {
        this.placeUseType = placeUseType;
    }

    public String getPlacePositionType() {
        return placePositionType;
    }

    public void setPlacePositionType(String placePositionType) {
        this.placePositionType = placePositionType;
    }

    public String getPlaceSpaceType() {
        return placeSpaceType;
    }

    public void setPlaceSpaceType(String placeSpaceType) {
        this.placeSpaceType = placeSpaceType;
    }

    public String getPlaceConcentrateType() {
        return placeConcentrateType;
    }

    public void setPlaceConcentrateType(String placeConcentrateType) {
        this.placeConcentrateType = placeConcentrateType;
    }

    public String getPlaceFireType() {
        return placeFireType;
    }

    public void setPlaceFireType(String placeFireType) {
        this.placeFireType = placeFireType;
    }

    public String getPlaceBuildType() {
        return placeBuildType;
    }

    public void setPlaceBuildType(String placeBuildType) {
        this.placeBuildType = placeBuildType;
    }

    public String getFireType() {
        return fireType;
    }

    public void setFireType(String fireType) {
        this.fireType = fireType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirePosition() {
        return firePosition;
    }

    public void setFirePosition(String firePosition) {
        this.firePosition = firePosition;
    }

    public String getFireObject() {
        return fireObject;
    }

    public void setFireObject(String fireObject) {
        this.fireObject = fireObject;
    }

    public String getFireReasonType() {
        return fireReasonType;
    }

    public void setFireReasonType(String fireReasonType) {
        this.fireReasonType = fireReasonType;
    }

    public String getFireReason() {
        return fireReason;
    }

    public void setFireReason(String fireReason) {
        this.fireReason = fireReason;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public Double getLoss() {
        return loss;
    }

    public void setLoss(Double loss) {
        this.loss = loss;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Integer getDeadNum() {
        return deadNum;
    }

    public void setDeadNum(Integer deadNum) {
        this.deadNum = deadNum;
    }

    public Integer getHurtNum() {
        return hurtNum;
    }

    public void setHurtNum(Integer hurtNum) {
        this.hurtNum = hurtNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSelfSave() {
        return selfSave;
    }

    public void setSelfSave(Integer selfSave) {
        this.selfSave = selfSave;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(String enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public String getPunishCaseNumber() {
        return punishCaseNumber;
    }

    public void setPunishCaseNumber(String punishCaseNumber) {
        this.punishCaseNumber = punishCaseNumber;
    }

    public Integer getFireStation() {
        return fireStation;
    }

    public void setFireStation(Integer fireStation) {
        this.fireStation = fireStation;
    }

    public String getHandleDepart() {
        return handleDepart;
    }

    public void setHandleDepart(String handleDepart) {
        this.handleDepart = handleDepart;
    }

    public String getSceneDesc() {
        return sceneDesc;
    }

    public void setSceneDesc(String sceneDesc) {
        this.sceneDesc = sceneDesc;
    }

    public String getDutyPart() {
        return dutyPart;
    }

    public void setDutyPart(String dutyPart) {
        this.dutyPart = dutyPart;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FireEvent=[");
        builder.append("id=" + id + ",");
        builder.append("districtId=" + districtId + ",");
        builder.append("streetId=" + streetId + ",");
        builder.append("blockId=" + blockId + ",");
        builder.append("buildingId=" + buildingId + ",");
        builder.append("name=" + name + ",");
        builder.append("placeName=" + placeName + ",");
        builder.append("placeUseType=" + placeUseType + ",");
        builder.append("placePositionType=" + placePositionType + ",");
        builder.append("placeSpaceType=" + placeSpaceType + ",");
        builder.append("placeConcentrateType=" + placeConcentrateType + ",");
        builder.append("placeFireType=" + placeFireType + ",");
        builder.append("placeBuildType=" + placeBuildType + ",");
        builder.append("fireType=" + fireType + ",");
        builder.append("description=" + description + ",");
        builder.append("firePosition=" + firePosition + ",");
        builder.append("fireObject=" + fireObject + ",");
        builder.append("fireReasonType=" + fireReasonType + ",");
        builder.append("fireReason=" + fireReason + ",");
        builder.append("occurTime=" + occurTime + ",");
        builder.append("loss=" + loss + ",");
        builder.append("caseNumber=" + caseNumber + ",");
        builder.append("deadNum=" + deadNum + ",");
        builder.append("hurtNum=" + hurtNum + ",");
        builder.append("state=" + state + ",");
        builder.append("createDate=" + createDate + ",");
        builder.append("selfSave=" + selfSave + ",");
        builder.append("modDate=" + modDate + ",");
        builder.append("cityArea=" + cityArea + ",");
        builder.append("enterpriseNature=" + enterpriseNature + ",");
        builder.append("punishCaseNumber=" + punishCaseNumber + ",");
        builder.append("fireStation=" + fireStation + ",");
        builder.append("handleDepart=" + handleDepart + ",");
        builder.append("sceneDesc=" + sceneDesc + ",");
        builder.append("dutyPart=" + dutyPart + ",");
        builder.append("]");
        return builder.toString();
    }


}
