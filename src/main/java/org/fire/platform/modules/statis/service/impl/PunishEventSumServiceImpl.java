package org.fire.platform.modules.statis.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.statis.bean.PunishEventSumBean;
import org.fire.platform.modules.statis.dao.PunishEventSumMapper;
import org.fire.platform.modules.statis.domain.PunishEventSum;
import org.fire.platform.modules.statis.service.IPunishEventSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

@Service
public class PunishEventSumServiceImpl implements IPunishEventSumService {

    @Autowired
	private PunishEventSumMapper mapper;
 
	public int insert(PunishEventSum bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(PunishEventSum bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public PunishEventSum get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<PunishEventSum> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<PunishEventSum> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEventSum> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<PunishEventSum> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<PunishEventSum> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEventSum> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<PunishEventSumBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<PunishEventSumBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	
	}

	@Override
	public List<AreaTypeDataVo> queryPunishSumStatis(Map<String, Object> params) {
		if(params == null){
			return new ArrayList<>();
		}
		return mapper.queryPunishSumStatis(params);
	}
	
	
}
