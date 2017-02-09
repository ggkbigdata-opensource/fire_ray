package org.fire.platform.modules.event.vo;

import org.fire.platform.modules.event.domain.FireEvent;

/**
 * Created by IntelliJ IDEA.
 * User: ZKT
 * Date: 2016/10/26 026
 * Time: 11:44
 */
public class FireEventFullVo extends FireEvent {
    private String streetName;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
}
