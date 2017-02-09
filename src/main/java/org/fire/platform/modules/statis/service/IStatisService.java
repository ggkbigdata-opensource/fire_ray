package org.fire.platform.modules.statis.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.modules.statis.bean.AreaStatisBean;
import org.fire.platform.modules.statis.bean.PieDataBean;
import org.fire.platform.modules.statis.bean.ReportStatisBean;

public interface IStatisService {
	
	public List<AreaStatisBean> queryStreetStatisData(Map<String,Object> param);
	
	
	public List<AreaStatisBean> queryBlockStatisData(Map<String,Object> param);
	
	
	public List<ReportStatisBean> queryReportItemSum(Map<String,Object> param);
	
	public List<PieDataBean> queryFireReasonTypePercent(Map<String,Object> param);
	
	public List<PieDataBean> queryFirePlaceTypePercent(Map<String,Object> param);
	
	public List<PieDataBean> queryReportLevelPercent(Map<String,Object> param);
	
	public List<PieDataBean> queryPlaceUseTypePercent(Map<String,Object> param);

}
