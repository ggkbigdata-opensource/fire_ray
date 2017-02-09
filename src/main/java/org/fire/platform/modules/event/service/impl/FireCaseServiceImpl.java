package org.fire.platform.modules.event.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.dao.FireCaseMapper;
import org.fire.platform.modules.event.domain.FireCase;
import org.fire.platform.modules.event.service.IFireCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-28 15:40:12
 */

@Service
public class FireCaseServiceImpl implements IFireCaseService {

    @Autowired
	private FireCaseMapper mapper;
 
	public int insert(FireCase bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(FireCase bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public FireCase get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<FireCase> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<FireCase> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<FireCase> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<FireCase> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<FireCase> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<FireCase> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	
}
