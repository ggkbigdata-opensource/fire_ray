package org.fire.platform.modules.area.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.BlockBean;
import org.fire.platform.modules.area.domain.Block;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */
 
public interface IBlockService extends IService<Block> {

	
	PageInfo<BlockBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
		
	Long queryBeanByName(String name);
}
