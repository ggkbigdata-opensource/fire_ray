package org.fire.platform.modules.check.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.bean.CheckReportBean;
import org.fire.platform.modules.check.dao.CheckReportMapper;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.service.ICheckReportService;
import org.fire.platform.modules.check.vo.CheckReportDetailVo;
import org.fire.platform.modules.check.vo.CheckReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

@Service
public class CheckReportServiceImpl implements ICheckReportService {

    @Autowired
	private CheckReportMapper mapper;
 
	public int insert(CheckReport bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckReport bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckReport get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckReport> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckReport> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReport> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<CheckReport> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<CheckReport> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReport> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public List<CheckReportVo> queryCheckReport(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectCheckReport(param);
	}


	@Override
	public PageInfo<CheckReportBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<CheckReportBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public CheckReport queryBeanByCode(String code) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByCode(code);
	}


	@Override
	public List<CheckReportBean> queryBeanByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByMap(map);
	}

    @Override
    public CheckReportDetailVo getDetail(Long reportId) {
		return mapper.getDetailVo(reportId);
    }


}
