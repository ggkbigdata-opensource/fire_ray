package org.fire.platform.modules.report.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

import java.util.Date;


/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@SuppressWarnings("serial")
public class CheckReportInfo extends BaseEntity {
    // Fields
    private Long id;
    private Long districtId;
    private Long streetId;
    private Long blockId;
    private String code;
    private String type;
    private String projectCode;
    private String name;
    private String address;
    private String delegate;
    private String detectionUnit;
    private String duAddress;
    private String duTel;
    private String duFax;
    private String duZipCode;
    private String resultDesc;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date detectionTime;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date createTime;
    private Long createUser;
    private Long modUser;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date modTime;

    public CheckReportInfo() {
        this.clear();
    }

    public CheckReportInfo(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        districtId = null;
        streetId = null;
        blockId = null;
        code = null;
        type = null;
        projectCode = null;
        name = null;
        address = null;
        delegate = null;
        detectionUnit = null;
        duAddress = null;
        duTel = null;
        duFax = null;
        duZipCode = null;
        resultDesc = null;
        detectionTime = null;
        createTime = null;
        createUser = null;
        modUser = null;
        modTime = null;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public String getDetectionUnit() {
        return detectionUnit;
    }

    public void setDetectionUnit(String detectionUnit) {
        this.detectionUnit = detectionUnit;
    }

    public String getDuAddress() {
        return duAddress;
    }

    public void setDuAddress(String duAddress) {
        this.duAddress = duAddress;
    }

    public String getDuTel() {
        return duTel;
    }

    public void setDuTel(String duTel) {
        this.duTel = duTel;
    }

    public String getDuFax() {
        return duFax;
    }

    public void setDuFax(String duFax) {
        this.duFax = duFax;
    }

    public String getDuZipCode() {
        return duZipCode;
    }

    public void setDuZipCode(String duZipCode) {
        this.duZipCode = duZipCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Date getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(Date detectionTime) {
        this.detectionTime = detectionTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getModUser() {
        return modUser;
    }

    public void setModUser(Long modUser) {
        this.modUser = modUser;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckReportInfo=[");
        builder.append("id=" + id + ",");
        builder.append("districtId=" + districtId + ",");
        builder.append("streetId=" + streetId + ",");
        builder.append("blockId=" + blockId + ",");
        builder.append("code=" + code + ",");
        builder.append("type=" + type + ",");
        builder.append("projectCode=" + projectCode + ",");
        builder.append("name=" + name + ",");
        builder.append("address=" + address + ",");
        builder.append("delegate=" + delegate + ",");
        builder.append("detectionUnit=" + detectionUnit + ",");
        builder.append("duAddress=" + duAddress + ",");
        builder.append("duTel=" + duTel + ",");
        builder.append("duFax=" + duFax + ",");
        builder.append("duZipCode=" + duZipCode + ",");
        builder.append("resultDesc=" + resultDesc + ",");
        builder.append("detectionTime=" + detectionTime + ",");
        builder.append("createTime=" + createTime + ",");
        builder.append("createUser=" + createUser + ",");
        builder.append("modUser=" + modUser + ",");
        builder.append("modTime=" + modTime + ",");
        builder.append("]");
        return builder.toString();
    }


}
