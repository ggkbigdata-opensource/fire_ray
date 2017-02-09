package org.fire.platform.modules.area.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 2016/12/21.
 */
public class AreaCompareStreetData {

    String streetName;

    List<AreaCompareData> areaCompareDatas = new ArrayList<AreaCompareData>();

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public List<AreaCompareData> getAreaCompareDatas() {
        return areaCompareDatas;
    }

    public void setAreaCompareDatas(List<AreaCompareData> areaCompareDatas) {
        this.areaCompareDatas = areaCompareDatas;
    }
}
