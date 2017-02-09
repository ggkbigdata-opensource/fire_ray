package org.fire.platform.modules.check.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.check.bean.CheckReportBean;
import org.fire.platform.modules.check.domain.CheckReport;
import org.fire.platform.modules.check.vo.CheckReportDetailVo;
import org.fire.platform.modules.check.vo.CheckReportVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */

public interface CheckReportMapper extends Mapper<CheckReport> {
	
	public List<CheckReportVo> selectCheckReport(Map<String,Object> param);

	List<CheckReportBean> selectBeanByMap(Map<String, Object> map);
	
	CheckReport selectBeanByCode(String code);

    CheckReportDetailVo getDetailVo(Long reportId);
}

