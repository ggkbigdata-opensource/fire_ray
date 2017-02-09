package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemUnqualified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fire.platform.modules.report.dao.CheckItemUnqualifiedMapper;
import org.fire.platform.modules.report.service.ICheckItemUnqualifiedService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class CheckItemUnqualifiedServiceImpl implements ICheckItemUnqualifiedService {

    @Autowired
	private CheckItemUnqualifiedMapper mapper;
 
	public int insert(CheckItemUnqualified bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItemUnqualified bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItemUnqualified get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItemUnqualified> queryAll(int page, int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItemUnqualified> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<CheckItemUnqualified> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemUnqualified> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<CheckItemUnqualified> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemUnqualified> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);

	}

	@Override
	public List<CheckItemUnqualified> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<CheckItemUnqualified> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<CheckItemUnqualified> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

    @Override
    public int batchInsert(List<CheckItemUnqualified> unqualifiedList) {
		if(unqualifiedList == null || unqualifiedList.isEmpty()){
			return 0;
		}
        return mapper.batchInsert(unqualifiedList);
    }

	@Override
	public PageInfo<CheckItemUnqualifiedBean> getUnqualifiedBeanByReportId(Integer page, Integer pageSize, Long reportId) {
		PageHelper.startPage(page, pageSize);
		Map<String,Object> params = Maps.newHashMap();
		params.put("reportId", reportId);
		List<CheckItemUnqualifiedBean> list = mapper.selectBeanByMap(params);
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
