package org.fire.platform.modules.event.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.event.bean.FireEventBean;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.vo.FireEventFullVo;
import org.fire.platform.modules.event.vo.FireEventVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */

public interface FireEventMapper extends Mapper<FireEvent> {
	
	List<FireEventVo> selectFireEvent(Map<String,Object> param);

	List<FireEventBean> selectBeanByMap(Map<String, Object> map);

    List<FireEvent> queryFireEventByMap(Map<String, Object> map);
    
    Long selectBeanByCaseNumber(String caseNumber);

    FireEventFullVo getVoByFireEventId(Long fireEventId);
}

