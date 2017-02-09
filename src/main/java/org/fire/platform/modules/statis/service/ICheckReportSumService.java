package org.fire.platform.modules.statis.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.CheckReportSumBean;
import org.fire.platform.modules.statis.domain.CheckReportSum;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */
 
public interface ICheckReportSumService extends IService<CheckReportSum> {

	
	PageInfo<CheckReportSumBean>  queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
}
