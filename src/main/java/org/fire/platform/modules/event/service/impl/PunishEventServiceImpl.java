package org.fire.platform.modules.event.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.bean.PunishEventBean;
import org.fire.platform.modules.event.dao.PunishEventMapper;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.service.IPunishEventService;
import org.fire.platform.modules.event.vo.PunishEventVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */

@Service
public class PunishEventServiceImpl implements IPunishEventService {

    @Autowired
	private PunishEventMapper mapper;
 
	public int insert(PunishEvent bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(PunishEvent bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public PunishEvent get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<PunishEvent> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<PunishEvent> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEvent> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<PunishEvent> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<PunishEvent> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEvent> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public List<PunishEventVo> queryPunishEvent(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectPunishEvent(param);
	}


	@Override
	public PageInfo<PunishEventBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEventBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public Long queryBeanByPunishNumber(String punishNumber) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByPunishNumber(punishNumber);
	}


	@Override
	public Long queryBeanByDecisionNumber(String decisionNumber) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByDecisionNumber(decisionNumber);
	}

	@Override
	public List<PunishEventBean> queryBeanByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectBeanByMap(map);
	}
	
	
}
