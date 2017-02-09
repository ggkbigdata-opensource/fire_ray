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
public class CheckItemResult extends BaseEntity {
    // Fields
    private Long id;
    private Long reportId;
    private Long itemId;
    private Integer checkNum;
    private Integer unqualifiedNum;

    public CheckItemResult() {
        this.clear();
    }

    public CheckItemResult(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        reportId = null;
        itemId = null;
        checkNum = null;
        unqualifiedNum = null;
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

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    public Integer getUnqualifiedNum() {
        return unqualifiedNum;
    }

    public void setUnqualifiedNum(Integer unqualifiedNum) {
        this.unqualifiedNum = unqualifiedNum;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CheckItemResultBak=[");
        builder.append("id=" + id + ",");
        builder.append("reportId=" + reportId + ",");
        builder.append("itemId=" + itemId + ",");
        builder.append("checkNum=" + checkNum + ",");
        builder.append("unqualifiedNum=" + unqualifiedNum + ",");
        builder.append("]");
        return builder.toString();
    }


}
