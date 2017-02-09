package org.fire.platform.modules.data.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.modules.data.vo.AreaSumDataVo;
import org.fire.platform.modules.data.vo.SumDataVo;

public interface DataHandlerMapper {
	
	public List<SumDataVo> selectReportSum(Map<String,Object> param);
	
	public List<SumDataVo> selectFireEventSum(Map<String,Object> param);
	
	public List<SumDataVo> selectPunishEventSum(Map<String,Object> param);
	
	public void deleteReportSumByParam(Map<String,Object> param);
	
	public void deleteFireEventSumByParam(Map<String,Object> param);
	
	public void deletePunishEventSumByParam(Map<String,Object> param);
	
	public List<AreaSumDataVo> selectStreetFireEventSum(Map<String,Object> param);
	
	public List<AreaSumDataVo> selectStreetPunishEventSum(Map<String,Object> param);
	
	public List<AreaSumDataVo> selectStreetReportSum(Map<String,Object> param);

}
