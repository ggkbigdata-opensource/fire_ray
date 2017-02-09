package org.fire.platform.modules.report.bean;

import org.fire.platform.modules.report.domain.CheckItemUnqualified;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/1 001
 * Time: 10:33
 */
public class CheckItemUnqualifiedBean{
    private String code;
    private String name;
    private String level;
    private String standard;

    private List<CheckItemUnqualified> unqualifiedList;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public List<CheckItemUnqualified> getUnqualifiedList() {
        return unqualifiedList;
    }

    public void setUnqualifiedList(List<CheckItemUnqualified> unqualifiedList) {
        this.unqualifiedList = unqualifiedList;
    }
}
