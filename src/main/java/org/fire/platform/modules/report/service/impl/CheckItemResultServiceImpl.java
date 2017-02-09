package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fire.platform.modules.report.dao.CheckItemResultMapper;
import org.fire.platform.modules.report.domain.CheckItemResult;
import org.fire.platform.modules.report.service.ICheckItemResultService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class CheckItemResultServiceImpl implements ICheckItemResultService {

    @Autowired
	private CheckItemResultMapper mapper;
 
	public int insert(CheckItemResult bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItemResult bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItemResult get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItemResult> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItemResult> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<CheckItemResult> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemResult> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<CheckItemResult> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItemResult> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemResult> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<CheckItemResult> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemResult> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

	@Override
	public int batchInsert(Long reportId , List<CheckItemResult> resultList) {
		if(resultList == null || resultList.isEmpty() || reportId == null){
			return 0;
		}
		Map<String,Object> params = Maps.newHashMap();
		params.put("reportId", reportId);
		params.put("resultList", resultList);
		return mapper.batchInsertResult(params);
	}

    @Override
    public PageInfo<CheckItemResultBean> queryResultBeanByReportId(Integer page, Integer pageSize ,Long reportId) {
		if(reportId == null){
			return null;
		}
		PageHelper.startPage(page,pageSize);
		List<CheckItemResultBean> list = mapper.selectResultBeanByReportId(reportId);
        return PageHelper.getPageInfo(list);
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
