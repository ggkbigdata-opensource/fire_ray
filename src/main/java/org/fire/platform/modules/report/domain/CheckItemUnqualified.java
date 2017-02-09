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
public class CheckItemUnqualified extends BaseEntity {
    // Fields
    private Long id;
    private Long reportId;
    private Long itemId;
    private String code;
    private String position;

    public CheckItemUnqualified() {
        this.clear();
    }

    public CheckItemUnqualified(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        reportId = null;
        itemId = null;
        code = null;
        position = null;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckItemUnqualified=[");
        builder.append("id=" + id + ",");
        builder.append("reportId=" + reportId + ",");
        builder.append("itemId=" + itemId + ",");
        builder.append("code=" + code + ",");
        builder.append("position=" + position + ",");
        builder.append("]");
        return builder.toString();
    }


}
