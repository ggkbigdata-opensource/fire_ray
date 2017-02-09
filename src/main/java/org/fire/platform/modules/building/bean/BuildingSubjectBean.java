package org.fire.platform.modules.building.bean;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.building.domain.BuildingSubject;
import org.fire.platform.modules.building.domain.Management;

/**
 * Created by Max on 2016/11/24.
 */
public class BuildingSubjectBean extends BuildingSubject{

    String streetName;

    String blockName;

    String districtName;

    @DictTransferAnnotation(param = "building_class")
    String baseBuildingClass;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }


    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    @Override
    public String getBaseBuildingClass() {
        return baseBuildingClass;
    }

    @Override
    public void setBaseBuildingClass(String baseBuildingClass) {
        this.baseBuildingClass = baseBuildingClass;
    }

}
