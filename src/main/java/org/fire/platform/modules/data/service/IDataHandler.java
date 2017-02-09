package org.fire.platform.modules.data.service;

import java.util.Map;

public interface IDataHandler {
	
	public void sumCheckReportData(Map<String,Object> param);
	
	public void sumFireEventData(Map<String,Object> param);
	
	public void sumPunishEventData(Map<String,Object> param);
	
	public String sumReportAnalysis(Long reportId);
	
	public void calcStreetSafeIndex(Long districtId);
	
	public void calcBlockSafeIndex(Long streetId);

}
