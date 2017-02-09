package org.fire.platform.modules.statis.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.modules.statis.bean.AreaStatisBean;
import org.fire.platform.modules.statis.bean.DataCountBean;
import org.fire.platform.modules.statis.bean.ReportStatisBean;

public interface StatisMapper {
	
	public List<AreaStatisBean> selectStreetStatisData(Map<String,Object> param);
	
	
	public List<AreaStatisBean> selectBlockStatisData(Map<String,Object> param);
	
	public List<ReportStatisBean> selectReportItemSum(Map<String,Object> param);
	
	public List<DataCountBean> selectFireReasonTypePercent(Map<String,Object> param);
	
	public List<DataCountBean> selectPlaceUseTypePercent(Map<String,Object> param);
	
	public List<DataCountBean> selectPlaceTypePercent(Map<String,Object> param);
	
	public List<DataCountBean> selectReportLevelPercent(Map<String,Object> param);
}
