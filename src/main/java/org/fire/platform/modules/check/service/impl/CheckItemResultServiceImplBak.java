package org.fire.platform.modules.check.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.bean.ResultSearchBean;
import org.fire.platform.modules.check.dao.CheckItemResultMapperBak;
import org.fire.platform.modules.check.domain.CheckItemResultBak;
import org.fire.platform.modules.check.service.ICheckItemResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Service
public class CheckItemResultServiceImplBak implements ICheckItemResultService {

    @Autowired
	private CheckItemResultMapperBak mapper;
 
	public int insert(CheckItemResultBak bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItemResultBak bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItemResultBak get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItemResultBak> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItemResultBak> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItemResultBak> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<CheckItemResultBak> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	@Override
	public List<ResultSearchBean> getItemCodeByReportId(long reportId) {
		return mapper.getItemCodeByReportId(reportId);
	}


	public PageInfo<CheckItemResultBak> queryPageByMap(Map<String, Object> map, int pageNo,
                                                       int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItemResultBak> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
