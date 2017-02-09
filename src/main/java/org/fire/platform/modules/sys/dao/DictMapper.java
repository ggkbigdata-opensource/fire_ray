package org.fire.platform.modules.sys.dao;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.sys.domain.Dict;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:48
 */

public interface DictMapper extends Mapper<Dict> {
	
	public String getDictByTypeAndCode(String code,String type);
	
	public String selectByName(String name,String type);
	
	public String selectByTypeAndCode(String code,String type);

}

