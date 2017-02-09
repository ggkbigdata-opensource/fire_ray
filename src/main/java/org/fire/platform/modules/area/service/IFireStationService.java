package org.fire.platform.modules.area.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.bean.FireStationBean;
import org.fire.platform.modules.area.domain.FireStation;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 17:37:30
 */
 
public interface IFireStationService extends IService<FireStation> {

	PageInfo<FireStationBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);

	Long queryBeanByName(String name);
}
