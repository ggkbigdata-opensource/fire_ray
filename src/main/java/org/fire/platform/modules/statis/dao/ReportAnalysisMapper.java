package org.fire.platform.modules.statis.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.statis.bean.ReportAnalysisBean;
import org.fire.platform.modules.statis.domain.ReportAnalysis;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-10-14 19:28:53
 */

public interface ReportAnalysisMapper extends Mapper<ReportAnalysis> {

	List<ReportAnalysisBean> selectBeanByMap(Map<String, Object> map);
}

