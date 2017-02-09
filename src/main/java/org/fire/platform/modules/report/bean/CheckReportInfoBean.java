package org.fire.platform.modules.report.bean;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.report.domain.CheckReportInfo;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/11/28 028
 * Time: 16:18
 */
public class CheckReportInfoBean extends CheckReportInfo {

    private String streetName;
    private String blockName;

    private String districtName;

    @DictTransferAnnotation(param = "report_type")
    private String type;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
