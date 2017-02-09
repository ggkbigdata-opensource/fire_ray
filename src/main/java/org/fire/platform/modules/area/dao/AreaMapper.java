package org.fire.platform.modules.area.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.modules.area.vo.AreaDataVo;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.area.vo.AreaVo;
import org.fire.platform.modules.area.vo.SimpleAreaVo;
import org.fire.platform.modules.front.vo.AreaRiskLevelVo;

public interface AreaMapper {
	
	public List<AreaVo> selectConcernArea(Long userId);
	
	/**
	 * String areaName,String blockType
	 * @param param
	 * @return
	 */
	public List<AreaVo> selectArea(Map<String,Object> param);
	
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<AreaVo> selectDistrict(Map<String,Object> param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<AreaVo> selectStreet(Map<String,Object> param);
	
	/**
	 * String areaName,Long streetId,String blockType
	 * @param param
	 * @return
	 */
	public List<AreaVo> selectBlock(Map<String,Object> param);
	
	/**
	 * 选择社区
	 * @return
	 */
	public List<SimpleAreaVo> selectDistrictSimple(Map<String,Object> param);
	
	/**
	 * 
	 * @param districtId
	 * @return
	 */
	public List<SimpleAreaVo> selectStreetSimple(Map<String,Object> param);
	/**
	 * String areaName,Long districtId,String bolockType
	 * @param param
	 * @return
	 */
	public List<AreaVo> selectStreetAndBlock(Map<String,Object> param);
	
	public List<AreaDataVo> selectConfirmFireSum(Map<String,Object> param);
	
	public List<AreaDataVo> selectUnPassCheckReportSum(Map<String,Object> param);
	
	public List<AreaDataVo> selectPunishSum(Map<String,Object> param);
	
	
	public AreaTypeDataVo selectFireTypeSum(Map<String,Object> param);
	
	public AreaTypeDataVo selectPunishTypeSum(Map<String,Object> param);
	
	public AreaTypeDataVo selectCheckReportTypeSum(Map<String,Object> param);
	
	public List<AreaRiskLevelVo> selectAreaRiskLevel(Map<String,Object> param);

	public List<AreaTypeDataVo> selectFireTypeSumGroup(Map<String,Object> param);
	
	

}
