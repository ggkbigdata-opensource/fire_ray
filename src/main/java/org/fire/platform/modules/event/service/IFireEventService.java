package org.fire.platform.modules.event.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.event.bean.FireEventBean;
import org.fire.platform.modules.event.domain.FireEvent;
import org.fire.platform.modules.event.vo.FireEventFullVo;
import org.fire.platform.modules.event.vo.FireEventVo;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 13:57:00
 */
 
public interface IFireEventService extends IService<FireEvent> {
    List<FireEventVo> queryfireEvent(Map<String,Object> param);
    
    PageInfo<FireEventBean>  queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);

    List<FireEvent> queryFireEventByMap(Map<String,Object> map);
    
    Long queryBeanByCaseNumber(String caseNumber);

	List<FireEventBean> queryBeanByMap(Map<String, Object> map);

	FireEventFullVo getVo(Long fireEventId);
}
