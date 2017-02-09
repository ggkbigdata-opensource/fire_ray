package org.fire.platform.modules.report.service.impl;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultStatisBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fire.platform.modules.report.dao.CheckItemResultStatisMapper;
import org.fire.platform.modules.report.domain.CheckItemResultStatis;
import org.fire.platform.modules.report.service.ICheckItemResultStatisService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class CheckItemResultStatisServiceImpl implements ICheckItemResultStatisService {

    @Autowired
	private CheckItemResultStatisMapper mapper;
 
	public int insert(CheckItemResultStatis bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItemResultStatis bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItemResultStatis get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItemResultStatis> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItemResultStatis> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<CheckItemResultStatis> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemResultStatis> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<CheckItemResultStatis> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemResultStatis> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemResultStatis> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<CheckItemResultStatis> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemResultStatis> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

    @Override
    public void sumStatisByReportId(Long reportId) {
		mapper.sumStatisByReportId(reportId);
    }

	@Override
	public PageInfo<CheckItemResultStatisBean> queryStatisBeanByReportId(Integer page, Integer pageSize, Long reportId) {
		if(reportId == null){
			return null;
		}
		PageHelper.startPage(page, pageSize);
		List<CheckItemResultStatisBean> list = mapper.queryStatisBeanByReportId(reportId);
		return PageHelper.getPageInfo(list);
	}

    @Override
    public int batchInsert(List<CheckItemResultStatis> statisList) {
		if(statisList == null || statisList.isEmpty()){
			return 0;
		}
		return mapper.batchInsert(statisList);
    }

    @Override
    public int deleteByReportId(Long reportId) {
		if (reportId == null) {
			return 0;
		}
		Map<String,Object> params = Maps.newHashMap();
		params.put("reportId", reportId);
		return mapper.deleteByParam(params);
    }
}
