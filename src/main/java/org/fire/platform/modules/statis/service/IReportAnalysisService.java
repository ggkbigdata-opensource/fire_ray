package org.fire.platform.modules.statis.service;

import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.statis.bean.ReportAnalysisBean;
import org.fire.platform.modules.statis.domain.ReportAnalysis;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-10-14 19:28:53
 */
 
public interface IReportAnalysisService extends IService<ReportAnalysis> {

	
	PageInfo<ReportAnalysisBean>  queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
}
