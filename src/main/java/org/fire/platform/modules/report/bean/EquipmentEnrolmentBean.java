package org.fire.platform.modules.report.bean;

import org.fire.platform.modules.report.domain.EquipmentEnrolment;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/5 005
 * Time: 13:26
 */
public class EquipmentEnrolmentBean extends EquipmentEnrolment {

    private String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
