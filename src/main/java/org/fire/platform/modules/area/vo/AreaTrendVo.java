package org.fire.platform.modules.area.vo;

import java.util.List;

public class AreaTrendVo {
	private String title="消防数据走势";//图表名称
	//private List<AreaDataVo> safeIndexDatas; //安全指数
//	private List<AreaDataVo> checkReportDatas; //报告数据
	private List<AreaDataVo> fireEventDatas; //火情数据
	private List<AreaDataVo> punishEventDatas; //执法数据
	/**
	public List<AreaDataVo> getSafeIndexDatas() {
		return safeIndexDatas;
	}
	public void setSafeIndexDatas(List<AreaDataVo> safeIndexDatas) {
		this.safeIndexDatas = safeIndexDatas;
	}
	**/
//	public List<AreaDataVo> getCheckReportDatas() {
//		return checkReportDatas;
//	}
//	public void setCheckReportDatas(List<AreaDataVo> checkReportDatas) {
//		this.checkReportDatas = checkReportDatas;
//	}
	public List<AreaDataVo> getFireEventDatas() {
		return fireEventDatas;
	}
	public void setFireEventDatas(List<AreaDataVo> fireEventDatas) {
		this.fireEventDatas = fireEventDatas;
	}
	public List<AreaDataVo> getPunishEventDatas() {
		return punishEventDatas;
	}
	public void setPunishEventDatas(List<AreaDataVo> punishEventDatas) {
		this.punishEventDatas = punishEventDatas;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	

}
