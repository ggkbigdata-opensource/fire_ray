package org.fire.platform.modules.area.service.impl;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.StreetBean;
import org.fire.platform.modules.area.dao.StreetMapper;
import org.fire.platform.modules.area.domain.Street;
import org.fire.platform.modules.area.service.IStreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreetServiceImpl implements IStreetService{
	
	@Autowired
	private StreetMapper dao;

	@Override
	public int insert(Street bean) {
		// TODO Auto-generated method stub
		return dao.insert(bean);
	}

	@Override
	public int update(Street bean) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKey(bean);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Street get(Long id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Street> queryAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	@Override
	public PageInfo<Street> queryPage(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<Street> ls = dao.selectAll();
		return PageHelper.getPageInfo(ls);
	}

	@Override
	public List<Street> queryByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.selectByMap(map);
	}

	@Override
	public PageInfo<Street> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<Street> ls = dao.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	@Override
	public PageInfo<StreetBean> queryPageBeanByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<StreetBean> ls = dao.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return dao.selectByName(name);
	}
}
