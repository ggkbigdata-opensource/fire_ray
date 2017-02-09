package org.fire.platform.modules.event.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.bean.PunishEventBean;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.vo.PunishEventVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */
 
public interface IPunishEventService extends IService<PunishEvent> {
	
	public List<PunishEventVo> queryPunishEvent(Map<String,Object> param);
	
	public PageInfo<PunishEventBean>  queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);
	
	public Long queryBeanByPunishNumber(String punishNumber);

	public Long queryBeanByDecisionNumber(String decisionNumber);

	public List<PunishEventBean> queryBeanByMap(Map<String, Object> map);
}
