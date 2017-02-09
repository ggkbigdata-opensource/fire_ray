package org.fire.platform.modules.area.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.dao.DistrictMapper;
import org.fire.platform.modules.area.domain.District;
import org.fire.platform.modules.area.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 18:52:34
 */

@Service
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
	private DistrictMapper mapper;
 
	public int insert(District bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(District bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public int batchDelete(List<Long> ids){
		return mapper.batchDeleteDistrict(ids);
	}
	
	public District get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<District> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<District> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<District> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<District> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<District> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<District> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}
	
	
}
