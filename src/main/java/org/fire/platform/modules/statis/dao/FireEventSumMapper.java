package org.fire.platform.modules.statis.dao;

import java.util.List;
import java.util.Map;

import org.fire.platform.common.base.Mapper;
import org.fire.platform.modules.area.vo.AreaTypeDataVo;
import org.fire.platform.modules.statis.bean.FireEventSumBean;
import org.fire.platform.modules.statis.domain.FireEventSum;

/**
 * Company: Scho Techonlogy Co. Ltd
 * @author Administrator
 * @date 2016-9-19 20:38:13
 */

public interface FireEventSumMapper extends Mapper<FireEventSum> {

	List<FireEventSumBean> selectBeanByMap(Map<String, Object> map);

    List<AreaTypeDataVo> queryFireSumStatis(Map<String, Object> params);
}

