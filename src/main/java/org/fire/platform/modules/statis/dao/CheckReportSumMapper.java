package org.fire.platform.modules.statis.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.statis.bean.CheckReportSumBean;
import org.fire.platform.modules.statis.domain.CheckReportSum;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

public interface CheckReportSumMapper extends Mapper<CheckReportSum> {

	List<CheckReportSumBean> selectBeanByMap(Map<String, Object> map);
}

