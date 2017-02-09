package org.fire.platform.modules.statis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.fire.platform.modules.statis.bean.AreaStatisBean;
import org.fire.platform.modules.statis.bean.DataCountBean;
import org.fire.platform.modules.statis.bean.PieDataBean;
import org.fire.platform.modules.statis.bean.ReportStatisBean;
import org.fire.platform.modules.statis.dao.StatisMapper;
import org.fire.platform.modules.statis.service.IStatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisServiceImpl implements IStatisService{
	
	@Autowired
	StatisMapper statisMapper;

	@Override
	public List<AreaStatisBean> queryStreetStatisData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return statisMapper.selectStreetStatisData(param);
	}

	@Override
	public List<AreaStatisBean> queryBlockStatisData(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return statisMapper.selectBlockStatisData(param);
	}

	@Override
	public List<ReportStatisBean> queryReportItemSum(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return statisMapper.selectReportItemSum(param);
	}

	@Override
	public List<PieDataBean> queryFireReasonTypePercent(Map<String,Object> param) {
		// TODO Auto-generated method stub
		List<DataCountBean> list = statisMapper.selectFireReasonTypePercent(param);
		
		return handlePieData(list);
		
	}
	
	@Override
	public List<PieDataBean> queryFirePlaceTypePercent(
			Map<String, Object> param) {
		List<DataCountBean> list = statisMapper.selectPlaceTypePercent(param);
		
		return handlePieData(list);
	}
	
	@Override
	public List<PieDataBean> queryReportLevelPercent(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<DataCountBean> list = statisMapper.selectReportLevelPercent(param);
		
		return handlePieData(list);
	}

	
	@Override
	public List<PieDataBean> queryPlaceUseTypePercent(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<DataCountBean> list = statisMapper.selectPlaceUseTypePercent(param);
		
		return handlePieData(list);
	}

	private List<PieDataBean> handlePieData(List<DataCountBean> list) {
		List<PieDataBean> retList = new ArrayList<PieDataBean>();
		
		
		if(CollectionUtils.isEmpty(list)){
			return retList;
		}
		
		Integer total = 0;
		
		for( DataCountBean bean:list ){
			total += bean.getCount();
		}
		
		PieDataBean percent = null;
		
		for(DataCountBean bean:list){
			percent = new PieDataBean();
			percent.setName(bean.getType());
			percent.setCode(bean.getCode());
			percent.setY(bean.getCount().doubleValue()/total);
			retList.add(percent);
		}
		
		return retList;
	}


	
	
	


}
