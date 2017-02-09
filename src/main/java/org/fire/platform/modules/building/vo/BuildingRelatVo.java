package org.fire.platform.modules.building.vo;

import java.util.List;

import org.fire.platform.modules.area.domain.FireStation;
import org.fire.platform.modules.event.vo.FireEventVo;
import org.fire.platform.modules.event.vo.PunishEventVo;

public class BuildingRelatVo {
	//附近消防站
	private List<FireStation> localfireStationList;
	//最近火警事件信息
	private List<FireEventVo> fireEventList;
	//最近处罚事件信息
	private List<PunishEventVo> punishEventList;
	
	public List<FireStation> getLocalfireStationList() {
		return localfireStationList;
	}
	public void setLocalfireStationList(List<FireStation> localfireStationList) {
		this.localfireStationList = localfireStationList;
	}
	public List<FireEventVo> getFireEventList() {
		return fireEventList;
	}
	public void setFireEventList(List<FireEventVo> fireEventList) {
		this.fireEventList = fireEventList;
	}
	public List<PunishEventVo> getPunishEventList() {
		return punishEventList;
	}
	public void setPunishEventList(List<PunishEventVo> punishEventList) {
		this.punishEventList = punishEventList;
	}
	

}
