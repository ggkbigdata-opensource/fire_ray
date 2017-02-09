package org.fire.platform.modules.report.domain;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@SuppressWarnings("serial")
public class CheckItemDef extends BaseEntity {
    // Fields
    private Long id;
    private String code;
    private String parentCode;
    private String name;
    private String level;
    private String standard;
    private Long createUser;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date createTime;
    private Long modUser;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+08:00"
    )
    private Date modTime;

    public CheckItemDef() {
        this.clear();
    }

    public CheckItemDef(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        code = null;
        parentCode = null;
        name = null;
        level = null;
        standard = null;
        createUser = null;
        createTime = null;
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

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        builder.append("CheckItemDef=[");
        builder.append("id=" + id + ",");
        builder.append("code=" + code + ",");
        builder.append("parentCode=" + parentCode + ",");
        builder.append("name=" + name + ",");
        builder.append("level=" + level + ",");
        builder.append("standard=" + standard + ",");
        builder.append("createUser=" + createUser + ",");
        builder.append("createTime=" + createTime + ",");
        builder.append("modUser=" + modUser + ",");
        builder.append("modTime=" + modTime + ",");
        builder.append("]");
        return builder.toString();
    }


}
