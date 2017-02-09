package org.fire.platform.modules.event.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.event.bean.PunishEventBean;
import org.fire.platform.modules.event.domain.PunishEvent;
import org.fire.platform.modules.event.vo.PunishEventVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */

public interface PunishEventMapper extends Mapper<PunishEvent> {
	
	List<PunishEventVo> selectPunishEvent(Map<String,Object> param);
	
	List<PunishEventBean> selectBeanByMap(Map<String, Object> map);
	
	Long selectBeanByPunishNumber(String punishNumber);

	Long selectBeanByDecisionNumber(String decisionNumber);

}

