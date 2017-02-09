package org.fire.platform.modules.report.domain;

import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@SuppressWarnings("serial")
public class EquipmentEnrolment extends BaseEntity {
    // Fields
    private Long id;
    private Long reportId;
    private String typeCode;
    private String name;
    private String info;
    private String remark;

    public EquipmentEnrolment() {
        this.clear();
    }

    public EquipmentEnrolment(Long id) {
        this();
        this.id = id;
    }

    public void clear() {
        id = null;
        reportId = null;
        typeCode = null;
        name = null;
        info = null;
        remark = null;
    }

    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EquipmentEnrolment=[");
        builder.append("id=" + id + ",");
        builder.append("reportId=" + reportId + ",");
        builder.append("typeCode=" + typeCode + ",");
        builder.append("name=" + name + ",");
        builder.append("info=" + info + ",");
        builder.append("remark=" + remark + ",");
        builder.append("]");
        return builder.toString();
    }


}
