package org.fire.platform.modules.report.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.report.bean.CheckItemResultBean;
import org.fire.platform.modules.report.bean.CheckItemUnqualifiedBean;
import org.fire.platform.modules.report.domain.CheckItemResultStatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fire.platform.modules.report.dao.CheckItemDefMapper;
import org.fire.platform.modules.report.domain.CheckItemDef;
import org.fire.platform.modules.report.service.ICheckItemDefService;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author ZKT
 * @date 2016-11-28 9:38:12
 */

@Service
public class CheckItemDefServiceImpl implements ICheckItemDefService {

    @Autowired
	private CheckItemDefMapper mapper;
 
	public int insert(CheckItemDef bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(CheckItemDef bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public CheckItemDef get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<CheckItemDef> queryAll(int page,int size){
	   return mapper.selectAll();
	}
	
	public PageInfo<CheckItemDef> queryPageByMap(int page, int size, Map param){
	   PageHelper.startPage(page,size);
	   List<CheckItemDef> list = mapper.selectByMap(param);
	   return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemDef> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public PageInfo<CheckItemDef> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemDef> list = mapper.selectAll();
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemDef> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

	@Override
	public PageInfo<CheckItemDef> queryPageByMap(Map<String, Object> map, int pageNo, int pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<CheckItemDef> list = mapper.selectByMap(map);
		return PageHelper.getPageInfo(list);
	}

	@Override
	public List<CheckItemDef> getCheckItem() {
		return mapper.getCheckItem();
	}

	@Override
	public List<CheckItemResultStatis> getStatisItem() {
		return mapper.getStatisItem();
	}

	@Override
	public List<CheckItemResultBean> getItemResult() {
		return mapper.getItemResult();
	}

}
