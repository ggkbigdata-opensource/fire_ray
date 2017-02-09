package org.fire.platform.modules.sys.service;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.sys.domain.Dict;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:24:48
 */
 
public interface IDictService extends IService<Dict> {
	
	public String getDictByTypeAndCode(String code,String type);
	
	public String getDicCodeByName(String name,String type);
	
	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 * @author wuguimei
	 */
	public String getByTypeAndCode(String code,String type);

}
