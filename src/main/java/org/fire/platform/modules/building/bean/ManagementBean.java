package org.fire.platform.modules.building.bean;

import org.fire.platform.modules.building.domain.Management;

/**
 * Created by Max on 2016/11/30.
 */
public class ManagementBean extends Management {

    String publishTimeString;

    public String getPublishTimeString() {
        return publishTimeString;
    }

    public void setPublishTimeString(String publishTimeString) {
        this.publishTimeString = publishTimeString;
    }
}
