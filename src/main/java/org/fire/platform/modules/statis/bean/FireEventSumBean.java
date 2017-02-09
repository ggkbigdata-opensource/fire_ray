package org.fire.platform.modules.statis.bean;

import org.fire.platform.modules.statis.domain.FireEventSum;

public class FireEventSumBean extends FireEventSum{
	
	  	String streetName;
	    
	    String blockName;
	    
	    String districtName;
	    
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
}
