package org.fire.platform.modules.event.bean;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.event.domain.PunishEvent;

public class PunishEventBean extends PunishEvent{
	
	String districtName;
	
  	String streetName;
    
    String blockName;

	String ownerUnitName;
    
    @DictTransferAnnotation(param = "punish_type")
    String punishType;
    
    String  punishTimeString;

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

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getPunishTimeString() {
		return punishTimeString;
	}

	public void setPunishTimeString(String punishTimeString) {
		this.punishTimeString = punishTimeString;
	}

	public String getOwnerUnitName() {
		return ownerUnitName;
	}

	public void setOwnerUnitName(String ownerUnitName) {
		this.ownerUnitName = ownerUnitName;
	}


}
