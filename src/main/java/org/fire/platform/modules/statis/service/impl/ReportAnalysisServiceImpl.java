package org.fire.platform.modules.statis.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.ReportAnalysisBean;
import org.fire.platform.modules.statis.dao.ReportAnalysisMapper;
import org.fire.platform.modules.statis.domain.ReportAnalysis;
import org.fire.platform.modules.statis.service.IReportAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-10-14 19:28:53
 */

@Service
public class ReportAnalysisServiceImpl implements IReportAnalysisService {

    @Autowired
	private ReportAnalysisMapper mapper;
 
	public int insert(ReportAnalysis bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(ReportAnalysis bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public ReportAnalysis get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<ReportAnalysis> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<ReportAnalysis> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<ReportAnalysis> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<ReportAnalysis> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<ReportAnalysis> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<ReportAnalysis> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<ReportAnalysisBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<ReportAnalysisBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
