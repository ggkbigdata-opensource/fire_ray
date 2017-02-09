package org.fire.platform.modules.statis.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.CheckReportSumBean;
import org.fire.platform.modules.statis.dao.CheckReportSumMapper;
import org.fire.platform.modules.statis.domain.CheckReportSum;
import org.fire.platform.modules.statis.service.ICheckReportSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

@Service
public class CheckReportSumServiceImpl implements ICheckReportSumService {

    @Autowired
	private CheckReportSumMapper mapper;
 
	public int insert(CheckReportSum bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckReportSum bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckReportSum get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckReportSum> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckReportSum> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReportSum> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<CheckReportSum> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<CheckReportSum> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReportSum> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
	@Override
	public PageInfo<CheckReportSumBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReportSumBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	
	}
	
}
