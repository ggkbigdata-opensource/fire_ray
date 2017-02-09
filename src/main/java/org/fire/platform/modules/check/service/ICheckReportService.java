package org.fire.platform.modules.check.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.check.bean.CheckReportBean;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.vo.CheckReportDetailVo;
import org.fire.platform.modules.check.vo.CheckReportVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */
 
public interface ICheckReportService extends IService<CheckReport> {
	
	public List<CheckReportVo> queryCheckReport(Map<String,Object> param);

	PageInfo<CheckReportBean> queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
	
	CheckReport queryBeanByCode(String code);
	
	List<CheckReportBean> queryBeanByMap(Map<String, Object> map);

    CheckReportDetailVo getDetail(Long reportId);
}
