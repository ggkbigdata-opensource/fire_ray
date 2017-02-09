package org.fire.platform.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.sys.domain.AppVersion;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-29 17:43:16
 */
 
public interface IAppVersionService extends IService<AppVersion> {
	
	List<AppVersion>  queryAPPVersionByMap(Map<String,Object> param);

}
