package org.fire.platform.modules.report.bean;

import org.fire.platform.modules.report.domain.EquipmentEnrolment;
import org.fire.platform.modules.report.domain.EquipmentType;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/12/5 005
 * Time: 20:32
 */
public class EquipmentTypeBean extends EquipmentType {
    private List<EquipmentEnrolment> equipmentList;

    public List<EquipmentEnrolment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentEnrolment> equipmentList) {
        this.equipmentList = equipmentList;
    }
}
