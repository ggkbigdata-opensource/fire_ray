package org.fire.platform.modules.sys.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.sys.dao.DictMapper;
import org.fire.platform.modules.sys.domain.Dict;
import org.fire.platform.modules.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:48
 */

@Service
public class DictServiceImpl implements IDictService {

    @Autowired
	private DictMapper mapper;
 
	public int insert(Dict bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Dict bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Dict get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Dict> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Dict> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Dict> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Dict> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<Dict> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Dict> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	@Caching(evict = {
			@CacheEvict(value = "weekCache", key = "'getDictByTypeAndCode_' + #code + '_' + #type")
	})
	public String getDictByTypeAndCode(String code, String type) {
		// TODO Auto-generated method stub
		return mapper.getDictByTypeAndCode(type, code);
	}


	@Override
	public String getDicCodeByName(String name,String type) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name,type);
	}


	@Override
	public String getByTypeAndCode(String code, String type) {
		// TODO Auto-generated method stub
		return mapper.selectByTypeAndCode(code, type);
	}
	
	
}
