package org.fire.platform.modules.front.vo;

import java.util.List;

import org.fire.platform.common.base.CountValueData;
import org.fire.platform.modules.area.vo.AreaVo;

public class AreaDetailResult {
	AreaVo area;
	AreaTypeResultVo fireResult;
//	AreaTypeResultVo reportResult;
	AreaTypeResultVo punishResult;
	List<CountValueData> typeCount;
	public AreaVo getArea() {
		return area;
	}
	public void setArea(AreaVo area) {
		this.area = area;
	}
	public AreaTypeResultVo getFireResult() {
		return fireResult;
	}
	public void setFireResult(AreaTypeResultVo fireResult) {
		this.fireResult = fireResult;
	}
//	public AreaTypeResultVo getReportResult() {
//		return reportResult;
//	}
//	public void setReportResult(AreaTypeResultVo reportResult) {
//		this.reportResult = reportResult;
//	}
	public AreaTypeResultVo getPunishResult() {
		return punishResult;
	}
	public void setPunishResult(AreaTypeResultVo punishResult) {
		this.punishResult = punishResult;
	}
	public List<CountValueData> getTypeCount() {
		return typeCount;
	}
	public void setTypeCount(List<CountValueData> typeCount) {
		this.typeCount = typeCount;
	}
	
	
}
