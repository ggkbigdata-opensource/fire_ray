package org.fire.platform.modules.area.service.impl;


import java.util.List;
import java.util.Map;

import org.fire.platform.common.page.PageHelper;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.BlockBean;
import org.fire.platform.modules.area.dao.BlockMapper;
import org.fire.platform.modules.area.domain.Block;
import org.fire.platform.modules.area.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

@Service
public class BlockServiceImpl implements IBlockService {

    @Autowired
	private BlockMapper mapper;
 
	public int insert(Block bean) {
		return mapper.insert(bean);
	}
	
	
	public int update(Block bean) {
	   return mapper.updateByPrimaryKey(bean);
	}
	
	public int delete(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	
	public Block get(Long id){
	    return mapper.selectByPrimaryKey(id);
	}
	
	public List<Block> queryAll(){
	   return mapper.selectAll();
	}
	
	public PageInfo<Block> queryPage(int pageNo, int pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Block> ls = mapper.selectAll();
		return PageHelper.getPageInfo(ls);
	}
	
	public List<Block> queryByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}
	
	public PageInfo<Block> queryPageByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		
		PageHelper.startPage(pageNo, pageSize);
		List<Block> ls = mapper.selectByMap(map);
		return PageHelper.getPageInfo(ls);
	}
	
	@Override
	public PageInfo<BlockBean> queryPageBeanByMap(Map<String, Object> map, int pageNo,
			int pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, pageSize);
		List<BlockBean> ls = mapper.selectBeanByMap(map);
		return PageHelper.getPageInfo(ls);
	}


	@Override
	public Long queryBeanByName(String name) {
		// TODO Auto-generated method stub
		return mapper.selectByName(name);
	}
}
