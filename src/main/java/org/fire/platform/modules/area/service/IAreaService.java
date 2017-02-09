package org.fire.platform.modules.area.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.vo.*;
import org.fire.platform.modules.front.vo.AreaDetailResult;
import org.fire.platform.modules.front.vo.AreaRiskLevelVo;

public interface IAreaService {
	
	

	/**
	 * 根据用户Id查询用户关注的社区列表
	 * @param userId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<AreaVo> queryConcernArea(Long userId,Integer pageNo,Integer pageSize);
	
	
	public PageInfo<SimpleAreaVo> queryDistrict(String name,Integer pageNo,Integer pageSize);
	
	public PageInfo<SimpleAreaVo> queryStreet(Long districtId,String name,Integer pageNo,Integer pageSize);
	
	/**
	 * 搜索社区
	 * @param areaName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageInfo<AreaVo> queryArea(String areaName, Long districtId,Long streetId,String blockType, Integer pageNo,
			Integer pageSize);
	
	
	/**
	 * 查看社区趋势报告
	 * @param areaId
	 * @param aeraType
	 * @return
	 */
	public AreaTrendVo queryTrendReport(Long areaId,Integer areaType);
	
	/**
	 * 根据map查看趋势图
	 * @param param
	 * @return
	 */
	public AreaTrendVo queryTrendReport(Map<String,Object> param);
	
	/**
	 * 根据社区ID和类型查询火情对比数据
	 * @param areaId
	 * @param areaType
	 * @return
	 */
	public List<AreaTypeDataVo> queryFireCompareData(Long areaId, Integer areaType);
	
	/**
	 * 根据社区ID和类型查询报告对比数据
	 * @param areaId
	 * @param areaType
	 * @return
	 */
	public List<AreaTypeDataVo> queryCheckReportCompareData(Long areaId, Integer areaType);
	
	/**
	 * 根据社区ID和类型执法对比数据
	 * @param areaId
	 * @param areaType
	 * @return
	 */
	public List<AreaTypeDataVo> queryPunishCompareData(Long areaId, Integer areaType);
	
	
	/**
	 * 
	 * @param areaId
	 * @param areaType
	 * @param userId
	 * @return
	 */
	public AreaDetailResult queryAreaDetail(Long areaId, Integer areaType, String code, Long userId);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	public List<AreaRiskLevelVo> queryAreaRiskLevel(Map<String,Object> param);



	public List<AreaCompareStreetData> queryFireDataGroup(Long areaId, Integer areaType, String monthBegin, String monthEnd);
}
