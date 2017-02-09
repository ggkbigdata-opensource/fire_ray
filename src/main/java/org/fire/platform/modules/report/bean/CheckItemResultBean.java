package org.fire.platform.modules.report.bean;

import org.fire.platform.modules.report.domain.CheckItemResult;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/30 030
 * Time: 15:00
 */
public class CheckItemResultBean extends CheckItemResult{

    private String code;
    private String parentCode;
    private String name;
    private String level;
    private String standard;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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
}
