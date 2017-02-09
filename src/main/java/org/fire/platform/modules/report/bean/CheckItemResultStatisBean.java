package org.fire.platform.modules.report.bean;

import org.fire.platform.modules.report.domain.CheckItemResultStatis;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 15:54
 */
public class CheckItemResultStatisBean {

    private Long reportId;
    private String code;
    private String name;
    private List<CheckItemResultStatis> statisList;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CheckItemResultStatis> getStatisList() {
        return statisList;
    }

    public void setStatisList(List<CheckItemResultStatis> statisList) {
        this.statisList = statisList;
    }
}
