package org.fire.platform.modules.area.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.bean.BlockBean;
import org.fire.platform.modules.area.domain.Block;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */

public interface BlockMapper extends Mapper<Block> {
	
	
	List<BlockBean> selectBeanByMap(Map<String, Object> map);
	
	Long selectByName(String name);
}

