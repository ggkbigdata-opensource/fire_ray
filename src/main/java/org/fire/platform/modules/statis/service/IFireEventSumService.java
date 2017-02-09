package org.fire.platform.modules.statis.service;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.IService;
import org.fire.platform.common.page.PageInfo;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.statis.bean.FireEventSumBean;
import org.fire.platform.modules.statis.domain.FireEventSum;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */
 
public interface IFireEventSumService extends IService<FireEventSum> {

    PageInfo<FireEventSumBean>  queryPageBeanByMap(Map<String, Object> map,
			int pageNo, int pageSize);

    List<AreaTypeDataVo> queryFireSumStatis(Map<String,Object> params);
}
