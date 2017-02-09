package org.fire.platform.modules.event.bean;

import org.fire.platform.common.serialize.DictTransferAnnotation;
import org.fire.platform.modules.event.domain.FireEvent;

public class FireEventBean extends FireEvent{
	
	  	String streetName;
	    
	    String blockName;
	    
	    String districtName;

		String ownerUnitName;
	    

	    @DictTransferAnnotation(param = "fire_type")
	    String fireType;
	    
	    @DictTransferAnnotation(param = "fire_reason_type")
	    String fireReasonType;
	    
        //场所使用类型：place_use_type 
	    @DictTransferAnnotation(param = "place_use_type")
	    String placeUseType;
	    
        //地理位置 ：place_position_type
	    @DictTransferAnnotation(param = "place_position_type")
	    String placePositionType;
	    
        //场所空间类型：place_space_type
//	    String placeSpaceTypeName;
	    
         //工程性质  place_build_type
	    @DictTransferAnnotation(param = "place_build_type")
	    String placeBuildType;
	    
	    //消防手续 place_fire_type
	    @DictTransferAnnotation(param = "place_fire_type")
	    String  placeFireType;

		@DictTransferAnnotation(param = "fire_event_state")
		String  state;


		String occurTimeString;

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

		public String getOccurTimeString() {
			return occurTimeString;
		}

		public void setOccurTimeString(String occurTimeString) {
			this.occurTimeString = occurTimeString;
		}

		public String getFireType() {
			return fireType;
		}

		public void setFireType(String fireType) {
			this.fireType = fireType;
		}

		public String getFireReasonType() {
			return fireReasonType;
		}

		public void setFireReasonType(String fireReasonType) {
			this.fireReasonType = fireReasonType;
		}

		public String getPlaceUseType() {
			return placeUseType;
		}

		public void setPlaceUseType(String placeUseType) {
			this.placeUseType = placeUseType;
		}

		public String getPlacePositionType() {
			return placePositionType;
		}

		public void setPlacePositionType(String placePositionType) {
			this.placePositionType = placePositionType;
		}

		public String getPlaceBuildType() {
			return placeBuildType;
		}

		public void setPlaceBuildType(String placeBuildType) {
			this.placeBuildType = placeBuildType;
		}

		public String getPlaceFireType() {
			return placeFireType;
		}

		public void setPlaceFireType(String placeFireType) {
			this.placeFireType = placeFireType;
		}

		public String getOwnerUnitName() {
			return ownerUnitName;
		}

		public void setOwnerUnitName(String ownerUnitName) {
			this.ownerUnitName = ownerUnitName;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

}
