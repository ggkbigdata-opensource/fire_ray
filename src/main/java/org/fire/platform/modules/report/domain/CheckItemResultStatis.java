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
public class CheckItemResultStatis extends BaseEntity {
    // Fields
    private Long id;
    private Long reportId;
    private String code;
    private String level;
    private Integer checkNum;
    private Integer unqualifiedNum;

    public CheckItemResultStatis() {
        this.clear();
    }

    public CheckItemResultStatis(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        reportId = null;
        code = null;
        level = null;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        builder.append("CheckItemResultStatis=[");
        builder.append("id=" + id + ",");
        builder.append("reportId=" + reportId + ",");
        builder.append("code=" + code + ",");
        builder.append("level=" + level + ",");
        builder.append("checkNum=" + checkNum + ",");
        builder.append("unqualifiedNum=" + unqualifiedNum + ",");
        builder.append("]");
        return builder.toString();
    }


}
