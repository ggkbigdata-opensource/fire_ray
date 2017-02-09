package org.fire.platform.modules.report.domain;

import org.fire.platform.common.base.BaseEntity;

/**
 * No modifying
 * Company: Scho Co. Ltd
 *
 * @author ZKT
 * @date 2016-12-2 14:28:58
 */

@SuppressWarnings("serial")
public class EquipmentType extends BaseEntity {
    // Fields
    private Long id;
    private String code;
    private String name;

    public EquipmentType() {
        this.clear();
    }

    public EquipmentType(Long id) {
        this();

        this.id = id;
    }

    public void clear() {
        id = null;
        code = null;
        name = null;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EquipmentType=[");
        builder.append("id=" + id + ",");
        builder.append("code=" + code + ",");
        builder.append("name=" + name + ",");
        builder.append("]");
        return builder.toString();
    }


}
