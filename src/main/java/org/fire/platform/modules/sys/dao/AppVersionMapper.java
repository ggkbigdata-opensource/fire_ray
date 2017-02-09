package org.fire.platform.modules.sys.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.sys.domain.AppVersion;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-29 17:43:16
 */

public interface AppVersionMapper extends Mapper<AppVersion> {
	
	List<AppVersion>  selectAPPVersionByMap(Map<String,Object> param);
 
}

