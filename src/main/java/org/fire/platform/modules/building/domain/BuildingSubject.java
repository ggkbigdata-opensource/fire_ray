package org.fire.platform.modules.building.domain;

import java.util.*;
import org.fire.platform.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * No modifying
 * Company: Scho Co. Ltd
 * @author ZKT
 * @date 2016-11-22 11:20:25
 */

@SuppressWarnings("serial")
public class BuildingSubject extends BaseEntity{
    // Fields
        private Long id;
        private Long districtId;
        private Long streetId;
        private Long blockId;
        private String ownerUnitName;
        private String designUnit;
        private double totalArea;
        private double overgroundArea;
        private double undergroundArea;
        private String address;
        private String fireManager;
        private String contactName;
        private String contactPhone;
        private String supChargeUnitName;
        private String industrySupervisionDepart;
        private Double longitude;
        private Double latitude;
        private String useTime;
        private String baseBuildingClass;
        private String baseBuildingCategory;
        private Double conCoverArea;
        private Double conBuildArea;
        private Integer conBuildHight;
        private Integer conFloors;
        private Integer conUnderFloors;
        private String conClass;
        private String surfaceFunction;
        private String undergroundFunction;
        private Integer useUnitNum;
        private String baseCode;
        private String thumbImg;
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
        private String maintenanceUnit;
        private String thirdPartyFireDetection;
    public BuildingSubject() {
        this.clear();
    }
    public BuildingSubject(Long id) {
        this();

        this.id=id;
    }

    public void clear() {
        id=null;
        districtId=null;
        streetId=null;
        blockId=null;
        ownerUnitName=null;
        address=null;
        fireManager=null;
        contactName=null;
        contactPhone=null;
        supChargeUnitName=null;
        industrySupervisionDepart=null;
        longitude=null;
        latitude=null;
        useTime=null;
        baseBuildingClass=null;
        conCoverArea=null;
        conBuildArea=null;
        conBuildHight=null;
        conFloors=null;
        conUnderFloors=null;
        conClass=null;
        surfaceFunction=null;
        undergroundFunction=null;
        useUnitNum=null;
        baseCode=null;
        thumbImg=null;
        remark=null;
        modDate=null;
        createDate=null;
        userId=null;
        maintenanceUnit=null;
        thirdPartyFireDetection=null;
    }

    // Getters/Setters
        
   
    
    public double getTotalArea() {
        return totalArea;
    }
    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }
    public double getOvergroundArea() {
        return overgroundArea;
    }
    public void setOvergroundArea(double overgroundArea) {
        this.overgroundArea = overgroundArea;
    }
    public double getUndergroundArea() {
        return undergroundArea;
    }
    public void setUndergroundArea(double undergroundArea) {
        this.undergroundArea = undergroundArea;
    }
    
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

    public String getOwnerUnitName() {
        return ownerUnitName;
    }

    public void setOwnerUnitName(String ownerUnitName) {
        this.ownerUnitName = ownerUnitName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFireManager() {
        return fireManager;
    }

    public void setFireManager(String fireManager) {
        this.fireManager = fireManager;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getSupChargeUnitName() {
        return supChargeUnitName;
    }

    public void setSupChargeUnitName(String supChargeUnitName) {
        this.supChargeUnitName = supChargeUnitName;
    }

    public String getIndustrySupervisionDepart() {
        return industrySupervisionDepart;
    }

    public void setIndustrySupervisionDepart(String industrySupervisionDepart) {
        this.industrySupervisionDepart = industrySupervisionDepart;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getBaseBuildingClass() {
        return baseBuildingClass;
    }

    public void setBaseBuildingClass(String baseBuildingClass) {
        this.baseBuildingClass = baseBuildingClass;
    }
    
    public String getBaseBuildingCategory() {
        return baseBuildingCategory;
    }
    public void setBaseBuildingCategory(String baseBuildingCategory) {
        this.baseBuildingCategory = baseBuildingCategory;
    }
    public Double getConCoverArea() {
        return conCoverArea;
    }

    public void setConCoverArea(Double conCoverArea) {
        this.conCoverArea = conCoverArea;
    }

    public Double getConBuildArea() {
        return conBuildArea;
    }

    public void setConBuildArea(Double conBuildArea) {
        this.conBuildArea = conBuildArea;
    }

    public Integer getConBuildHight() {
        return conBuildHight;
    }

    public void setConBuildHight(Integer conBuildHight) {
        this.conBuildHight = conBuildHight;
    }

    public Integer getConFloors() {
        return conFloors;
    }

    public void setConFloors(Integer conFloors) {
        this.conFloors = conFloors;
    }

    public Integer getConUnderFloors() {
        return conUnderFloors;
    }

    public void setConUnderFloors(Integer conUnderFloors) {
        this.conUnderFloors = conUnderFloors;
    }

    public String getConClass() {
        return conClass;
    }

    public void setConClass(String conClass) {
        this.conClass = conClass;
    }

    public String getSurfaceFunction() {
        return surfaceFunction;
    }

    public void setSurfaceFunction(String surfaceFunction) {
        this.surfaceFunction = surfaceFunction;
    }

    public String getUndergroundFunction() {
        return undergroundFunction;
    }

    public void setUndergroundFunction(String undergroundFunction) {
        this.undergroundFunction = undergroundFunction;
    }

    public Integer getUseUnitNum() {
        return useUnitNum;
    }

    public void setUseUnitNum(Integer useUnitNum) {
        this.useUnitNum = useUnitNum;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
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

    public String getMaintenanceUnit() {
        return maintenanceUnit;
    }

    public void setMaintenanceUnit(String maintenanceUnit) {
        this.maintenanceUnit = maintenanceUnit;
    }

    public String getThirdPartyFireDetection() {
        return thirdPartyFireDetection;
    }

    public void setThirdPartyFireDetection(String thirdPartyFireDetection) {
        this.thirdPartyFireDetection = thirdPartyFireDetection;
    }

    public String getDesignUnit() {
        return designUnit;
    }

    public void setDesignUnit(String designUnit) {
        this.designUnit = designUnit;
    }

    // toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BuildingSubject=[");
        builder.append("id=" + id + ",");
        builder.append("districtId=" + districtId + ",");
        builder.append("streetId=" + streetId + ",");
        builder.append("blockId=" + blockId + ",");
        builder.append("ownerUnitName=" + ownerUnitName + ",");
        builder.append("address=" + address + ",");
        builder.append("fireManager=" + fireManager + ",");
        builder.append("contactName=" + contactName + ",");
        builder.append("contactPhone=" + contactPhone + ",");
        builder.append("supChargeUnitName=" + supChargeUnitName + ",");
        builder.append(
                "industrySupervisionDepart=" + industrySupervisionDepart + ",");
        builder.append("longitude=" + longitude + ",");
        builder.append("latitude=" + latitude + ",");
        builder.append("useTime=" + useTime + ",");
        builder.append("baseBuildingClass=" + baseBuildingClass + ",");
        builder.append("conCoverArea=" + conCoverArea + ",");
        builder.append("conBuildArea=" + conBuildArea + ",");
        builder.append("conBuildHight=" + conBuildHight + ",");
        builder.append("conFloors=" + conFloors + ",");
        builder.append("conUnderFloors=" + conUnderFloors + ",");
        builder.append("conClass=" + conClass + ",");
        builder.append("surfaceFunction=" + surfaceFunction + ",");
        builder.append("undergroundFunction=" + undergroundFunction + ",");
        builder.append("useUnitNum=" + useUnitNum + ",");
        builder.append("baseCode=" + baseCode + ",");
        builder.append("thumbImg=" + thumbImg + ",");
        builder.append("remark=" + remark + ",");
        builder.append("modDate=" + modDate + ",");
        builder.append("createDate=" + createDate + ",");
        builder.append("userId=" + userId + ",");
        builder.append("maintenanceUnit=" + maintenanceUnit + ",");
        builder.append(
                "thirdPartyFireDetection=" + thirdPartyFireDetection + ",");
        builder.append("]");
        return builder.toString();
    }

}
