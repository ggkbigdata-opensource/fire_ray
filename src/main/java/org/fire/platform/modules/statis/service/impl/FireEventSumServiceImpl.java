package org.fire.platform.modules.statis.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.statis.bean.FireEventSumBean;
import org.fire.platform.modules.statis.dao.FireEventSumMapper;
import org.fire.platform.modules.statis.domain.FireEventSum;
import org.fire.platform.modules.statis.service.IFireEventSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

@Service
public class FireEventSumServiceImpl implements IFireEventSumService {

    @Autowired
	private FireEventSumMapper mapper;
 
	public int insert(FireEventSum bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(FireEventSum bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public FireEventSum get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<FireEventSum> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<FireEventSum> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<FireEventSum> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<FireEventSum> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<FireEventSum> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<FireEventSum> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public PageInfo<FireEventSumBean> queryPageBeanByMap(
			Map<String, Object> map, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<FireEventSumBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	
	}

    @Override
    public List<AreaTypeDataVo> queryFireSumStatis(Map<String, Object> params) {
		if(params == null){
			return new ArrayList<>();
		}
        return mapper.queryFireSumStatis(params);
    }


}
