package org.fire.platform.modules.event.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.bean.FireEventBean;
import org.fire.platform.modules.event.dao.FireEventMapper;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.service.IFireEventService;
import org.fire.platform.modules.event.vo.FireEventFullVo;
import org.fire.platform.modules.event.vo.FireEventVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */

@Service
public class FireEventServiceImpl implements IFireEventService {

    @Autowired
	private FireEventMapper mapper;
 
	public int insert(FireEvent bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(FireEvent bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public FireEvent get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<FireEvent> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<FireEvent> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<FireEvent> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<FireEvent> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<FireEvent> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<FireEvent> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public List<FireEventVo> queryfireEvent(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectFireEvent(param);
	}


	@Override
	public PageInfo<FireEventBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<FireEventBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}

	@Override
	public List<FireEvent> queryFireEventByMap(Map<String, Object> map) {
		return mapper.queryFireEventByMap(map);
	}


	@Override
	public Long queryBeanByCaseNumber(String caseNumber) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByCaseNumber(caseNumber);
	}

	@Override
	public FireEventFullVo getVo(Long fireEventId) {
		return mapper.getVoByFireEventId(fireEventId);
	}

	@Override
	public List<FireEventBean> queryBeanByMap(Map<String, Object> map) {
		return mapper.selectBeanByMap(map);
	}
}
