package org.fire.platform.modules.check.service;

import java.util.List;

import org.fire.platform.common.base.IService;
import org.fire.platform.modules.check.bean.ResultSearchBean;
import org.fire.platform.modules.check.domain.CheckItemResultBak;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-18 18:05:34
 */
 
public interface ICheckItemResultService extends IService<CheckItemResultBak> {

	List<ResultSearchBean> getItemCodeByReportId(long reportId);

}
